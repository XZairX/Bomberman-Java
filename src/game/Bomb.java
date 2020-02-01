package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;
    private static final int BOMB_DELAY = 2500;
    private final int fire;

    // For debugging
    private int secondsToExplode = 3;

    private boolean isCollisionActive;

    public Bomb(int x, int y, int radius, int fire) {
        super(x, y, radius);
        this.fire = fire;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(BOMB_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hit();
            }
        });
        thread.start();

        Thread threadDebug = new Thread(new Runnable() {
            @Override
            public void run() {
                while (secondsToExplode > 0) {
                    try {
                        secondsToExplode--;
                        Thread.sleep(BOMB_DELAY / 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadDebug.start();
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
        for (BlockTile tile : listBlockTile) {
            if (tile.x == x && tile.y == y) {
                tile.toggleAvailability();
                break;
            }
        }
        //Fire.spawnFire(x, y, fire);
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

    public static void spawnBomb(int x, int y, int fire) {
        x = Math.round(x / TILE_DIAMETER) * TILE_DIAMETER;
        y = Math.round(y / TILE_DIAMETER) * TILE_DIAMETER;
        for (BlockTile tile : listBlockTile) {
            if (tile.x == x && tile.y == y) {
                if (tile.isAvailable()) {
                    listObjects.add(new Bomb(x, y, TILE_RADIUS, fire));
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
