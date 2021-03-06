package game;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public class RemoteBomb extends BombObject {
    private static final int REMOTEBOMB_DETONATION_DELAY = 1000;

    public RemoteBomb(int x, int y, int range, int bombID) {
        super(x, y, range, bombID);
    }

    @Override
    public void initialise() {
        super.initialise();
    }

    @Override
    public boolean isNotInitialised() {
        return super.isNotInitialised();
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    public boolean isColliding(GameObject other) {
        return super.isColliding(other);
    }

    @Override
    public void collisionHandling(GameObject other) {
        super.collisionHandling(other);
    }

    @Override
    public void hit() {
        dropFire(Type.REMOTE);
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        if (isHit) {
            g.setColor(BOMB_COLOUR);
            g.fillOval(x, y, diameter, diameter);
        } else {
            super.draw(g);
        }
    }

    public void detonate() {
        isHit = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(REMOTEBOMB_DETONATION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hit();
            }
        });
        thread.start();
    }

    public boolean isNotHit() {
        return !isHit;
    }
}
