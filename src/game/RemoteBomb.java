package game;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public class RemoteBomb extends BombObject {
    private static final int REMOTEBOMB_DETONATION_DELAY = 500;

    public RemoteBomb(int x, int y, int range, int bombID) {
        super(x, y, range, bombID);
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
        super.draw(g);
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
