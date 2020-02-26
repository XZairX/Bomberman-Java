package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public abstract class BombObject extends GameObject {
    private static final Color DEBUG_DETONATION_COLOUR = Color.WHITE;
    private static final int BOMB_DELAY = 2500;

    private final int range;
    private int debugSecondsToExplode = 3;

    private boolean isDropped;
    private boolean isCollisionActive;

    protected Color BOMB_COLOUR = Color.BLACK;

    protected enum Type {
        NORMAL, POWER, SPIKE, DANGEROUS, REMOTE
    }

    protected BombObject(int x, int y, int range) {
        super(x, y);
        this.x = Math.round(x / diameter) * diameter;
        this.y = Math.round(y / diameter) * diameter;
        this.range = range;

        for (TileObject tile : GameMain.getListTileObject()) {
            if (isColliding(tile)) {
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

        if (this.getClass() != RemoteBomb.class) {
            thread.start();
        }

        Thread threadDebugExplosion = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isDead) {
                    try {
                        debugSecondsToExplode--;
                        Thread.sleep(BOMB_DELAY / 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (this.getClass() != RemoteBomb.class) {
            threadDebugExplosion.start();
        } else {
            debugSecondsToExplode = -1;
        }

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
    protected Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    protected boolean isColliding(GameObject other) {
        return super.isColliding(other);
    }

    @Override
    protected void collisionHandling(GameObject other) {
        super.collisionHandling(other);
    }

    @Override
    protected void hit() {
        super.hit();
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, diameter, diameter);
        g.setColor(DEBUG_DETONATION_COLOUR);
        g.drawString(Integer.toString(debugSecondsToExplode + 1), x + 8, y + 14);

    }

    protected void dropFire(Type type) {
        if (isDropped) {
            for (TileObject tile : GameMain.getListTileObject()) {
                if (isColliding(tile)) {
                    if (!tile.isAvailable()) {
                        tile.toggleAvailability();
                        break;
                    }
                }
            }

            switch (type) {
                case NORMAL:
                    GameMain.addAliveGameObject(new Fire(x, y, range));
                    break;
                case POWER:
                    GameMain.addAliveGameObject(new PowerFire(x, y, range));
                    break;
                case SPIKE:
                    GameMain.addAliveGameObject(new SpikeFire(x, y, range));
                    break;
                case DANGEROUS:
                    GameMain.addAliveGameObject(new DangerousFire(x, y, range));
                    break;
                case REMOTE:
                    GameMain.addAliveGameObject(new Fire(x, y, range));
                    break;
            }
        }
    }

    protected boolean getIsCollisionActive() {
        return isCollisionActive;
    }

    protected void setIsCollisionActive() {
        isCollisionActive = true;
    }
}
