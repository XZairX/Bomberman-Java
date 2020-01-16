package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;
    private static final int BOMB_DELAY = 3000;

    // For debugging
    private int secondsToExplode = 3;

    private boolean isRunning = false;

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
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    public void hit() {
        secondsToExplode = 0;
        isRunning = false;
        dead = true;

        Iterator<GameObject> iterator = listObjects.iterator();
        while (iterator.hasNext()) {
            GameObject object = iterator.next();
            if (object.x == x && object.y == y) {
                iterator.remove();
                System.out.println("Removed bomb");
                break;
            }
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
                    System.out.println("Bomb placed");
                    listObjects.add(new Bomb(x, y, TILE_RADIUS));
                    tile.toggleAvailability();
                } else {
                    System.out.println("Bomb could not be placed");
                }
                break;
            }
        }
    }
}
