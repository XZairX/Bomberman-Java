package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;
    private static final int BOMB_DELAY = 2400;

    // For debugging
    private int secondsToExplode = 3;

    private boolean isRunning = false;
    private boolean hasActiveCollision = false;

    public Bomb(int x, int y, double radius) {
        super(x, y, radius);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                isRunning = true;
                if (secondsToExplode > 0) {
                    secondsToExplode--;
                } else {
                    for (BlockTile tile : listBlockTile) {
                        if (tile.x == x && tile.y == y) {
                            tile.toggleAvailability();
                            break;
                        }
                    }
                    hit();
                    timer.cancel();
                }
            }
        };
        // Correct version
        //timer.schedule(timerTask, BOMB_DELAY);

        // Debug version
        timer.schedule(timerTask, 0, 800);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    protected boolean isColliding(GameObject other) {
        return (this.getBounds().intersects(other.getBounds()));
    }

    @Override
    protected void collisionHandling(GameObject other) {
        // If Bomb is not colliding
        // ActiveCollision = TRUE
        if (other.getClass() == Player.class) {}
    }

    @Override
    public void hit() {
        secondsToExplode = 0;
        isRunning = false;
        dead = true;

        // List being modified at runtime so iterator not thread-safe
        // Might make a copy of list and reference it
        List<GameObject> remove = new ArrayList<>();
        ListIterator<GameObject> iterator = listObjects.listIterator();
        while (iterator.hasNext()) {
            GameObject object = iterator.next();
            if (object.x == x && object.y == y) {
                remove.add(object);
                //iterator.remove();
                break;
            }
        }

        synchronized (GameMain.class) {
            listObjects.removeAll(remove);
            System.out.println("removed");
        }
    }

    @Override
    protected void draw(Graphics2D g) {
        /*
        // Debug Bounding Box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */
        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, TILE_DIAMETER, TILE_DIAMETER);

        // Debug Detonation Time
        g.setColor(Color.WHITE);
        if (isRunning) {
            g.drawString(Integer.toString(secondsToExplode + 1), x + TILE_RADIUS, y + TILE_RADIUS);
        }
    }

    public static void spawnBomb(int x, int y) {
        x = Math.round(x / 20) * 20;
        y = Math.round(y / 20) * 20;
        for (BlockTile tile : listBlockTile) {
            if (tile.x == x && tile.y == y) {
                if (tile.isAvailable()) {
                    listObjects.add(new Bomb(x, y, TILE_RADIUS));
                    tile.toggleAvailability();
                }
                break;
            }
        }
    }

    // If Bomb is not colliding
    // ActiveCollision = TRUE

    public boolean getHasActiveCollision() {
        return hasActiveCollision;
    }

    public void setHasActiveCollision() {
        hasActiveCollision = true;
    }
}
