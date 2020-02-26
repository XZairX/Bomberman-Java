package game;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class RemoteBomb extends BombObject {
    private static final int REMOTE_BOMB_DETONATION_DELAY = 500;

    public RemoteBomb(int x, int y, int range) {
        super(x, y, range);
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
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                hit();
            }
        };
        timer.schedule(timerTask, REMOTE_BOMB_DETONATION_DELAY);
    }

    public boolean isNotHit() {
        return !isHit;
    }
}
