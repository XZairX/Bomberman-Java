package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.TILE_RADIUS;

import static game.GameMain.listObjects;

public class Fire extends GameObject {
    private static final Color FIRE_COLOUR = Color.YELLOW;
    private static final int FIRE_DELAY = 500;

    private int range = 3;

    public Fire(int x, int y, int radius) {
        super(x, y, radius);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                hit();
            }
        };
        timer.schedule(timerTask, FIRE_DELAY);
    }

    /*private void test() {
        for (int i = 0; i < range; i++) {
            spawnFire(x + 20, y);
        }
        hit();
    }*/

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
        other.hit();
    }

    @Override
    public void hit() {
        super.hit();
    }

    @Override
    protected void draw(Graphics2D g) {
        /*
        // Debug Bounding Box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */
        g.setColor(FIRE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
        g.setColor(FIRE_COLOUR);
        g.fillRect(x, y, diameter, diameter);
    }

    public static void spawnFire(int x, int y) {
        //listObjects.add(new Fire(x + 1, y + 1, TILE_RADIUS - 1));
        listObjects.add(new Fire(x, y, TILE_RADIUS));
    }
}
