package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;
    private static final int BOMB_DELAY = 2500;
    private final int range;

    // For debugging
    private int secondsToExplode = 3;

    private boolean isDropped;
    private boolean isCollisionActive;

    public Bomb(int x, int y, int range) {
        super(x, y);
        this.x = Math.round(x / TILE_DIAMETER) * TILE_DIAMETER;
        this.y = Math.round(y / TILE_DIAMETER) * TILE_DIAMETER;
        this.range = range;

        for (BlockTile tile : GameMain.getListBlockTile()) {
            if (tile.x == this.x && tile.y == this.y) {
                if (tile.isAvailable()) {
                    tile.toggleAvailability();
                    isDropped = true;
                    break;
                } else {
                    hit();
                    break;
                }
            }
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDead) {
                    try {
                        Thread.sleep(BOMB_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (!isDead) {
                        hit();
                    }
                }
            }
        });
        thread.start();

        Thread threadDebug = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDead) {
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

        Thread threadDebugCollision = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDead) {
                    System.out.println(getIsCollisionActive());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //threadDebugCollision.start();
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
        if (isDropped) {
            for (BlockTile tile : GameMain.getListBlockTile()) {
                if (tile.x == x && tile.y == y) {
                    if (!tile.isAvailable()) {
                        tile.toggleAvailability();
                        break;
                    }
                }
            }
            GameMain.addAliveGameObject(new Fire(x, y, range));
        }
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

    public boolean getIsCollisionActive() {
        return isCollisionActive;
    }

    public void setIsCollisionActive() {
        isCollisionActive = true;
    }
}
