package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;
    private static final int BOMB_DELAY = 2500;

    // For debugging
    private int secondsToExplode = 3;

    private boolean isCollisionActive;

    public Bomb(int x, int y, int radius) {
        super(x, y, radius);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (BlockTile tile : listBlockTile) {
                    if (tile.x == x && tile.y == y) {
                        tile.toggleAvailability();
                        break;
                    }
                }
                hit();
                timer.cancel();
            }
        };

        // Debug to show time to detonation
        TimerTask timerTaskDebug = new TimerTask() {
            @Override
            public void run() {
                if (secondsToExplode > 0) {
                    secondsToExplode--;
                }
            }
        };

        // Correct version
        timer.schedule(timerTask, BOMB_DELAY);

        // Debug version
        timer.schedule(timerTaskDebug, 0, BOMB_DELAY / 3);
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
        super.collisionHandling(other);
    }

    @Override
    public void hit() {
        Fire.spawnFire(x, y);
        super.hit();
    }

    @Override
    protected void draw(Graphics2D g) {
        /*
        // Debug Bounding Box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */
        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, diameter, diameter);

        // Debug Detonation Time
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(secondsToExplode + 1), x + TILE_RADIUS, y + TILE_RADIUS);
    }

    public static void spawnBomb(int x, int y) {
        x = Math.round(x / TILE_DIAMETER) * TILE_DIAMETER;
        y = Math.round(y / TILE_DIAMETER) * TILE_DIAMETER;
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

    public boolean getIsCollisionActive() {
        return isCollisionActive;
    }

    public void setIsCollisionActive() {
        isCollisionActive = true;
    }
}
