package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class FireObject extends GameObject {
    private int range;
    private boolean canEmitFire;

    protected static final int FIRE_RECURSION_DELAY = 10;

    protected Color FIRE_COLOUR = Color.YELLOW;
    protected int FIRE_DELAY = 500;
    protected boolean isFireLeftHit, isFireRightHit, isFireUpHit, isFireDownHit;

    protected FireObject(int x, int y) {
        super(x, y);
        isFireObject = true;
    }

    protected FireObject(int x, int y, int range) {
        super(x, y);
        this.range = range;
        isFireObject = true;
        canEmitFire = true;
    }

    protected void initialise() {
        super.initialise();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (canEmitFire) {
                    emitFire(x, y, range);
                }

                while (!isDead) {
                    try {
                        Thread.sleep(FIRE_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hit();
                }
            }
        });
        thread.start();
    }

    @Override
    protected boolean isNotInitialised() {
        return super.isNotInitialised();
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
        if (other.getClass() == BlockHard.class) {
            hit();

        } else if (other.getClass() == BlockSoft.class) {
            if (((BlockSoft)other).isNotHit()) {
                other.hit();
            }
            hit();

        } else if (other.getClass() == BlockItem.class) {
            other.hit();
            if (this.getClass() != SpikeFire.class) {
                hit();
            }

        } else if (other instanceof BombObject) {
            other.hit();
            hit();

        } else if (!(other instanceof FireObject)) {
            other.hit();
        }
    }

    @Override
    protected void hit() {
        super.hit();
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(FIRE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
        g.setColor(FIRE_COLOUR);
        g.fillRect(x, y, diameter, diameter);
    }

    protected abstract void emitFire(int x, int y, int range);
}
