package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public abstract class BombObject extends GameObject {
    private final int range;
    // For debugging
    private int secondsToExplode = 3;

    private boolean isDropped;
    private boolean isCollisionActive;

    // Required to be protected for Remote Bombs
    protected final int BOMB_DELAY = 2500;

    protected enum Type {
        NORMAL, POWER, SPIKE, DANGEROUS, REMOTE
    }

    protected BombObject(int x, int y, int range) {
        super(x, y);
        this.x = Math.round(x / diameter) * diameter;
        this.y = Math.round(y / diameter) * diameter;
        this.range = range;

        for (BlockTile tile : GameMain.getListBlockTile()) {
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
        thread.start();

        Thread threadDebugExplosion = new Thread(new Runnable() {
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
        threadDebugExplosion.start();

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
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(secondsToExplode + 1), x + 8, y + 14);
    }

    protected void dropFire(Type type) {
        if (isDropped) {
            for (BlockTile tile : GameMain.getListBlockTile()) {
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
