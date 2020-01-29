package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listObjects;

public class Fire extends GameObject {
    private static final Color FIRE_COLOUR = Color.YELLOW;
    private static final int FIRE_DELAY = 500;

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
        if (other.getClass() == BlockHard.class) {
            hit();
        }
        if (other.getClass() == Bomb.class) {
            System.out.println("sfjkljsldk");
        } else {
            System.out.println("jkd");
            other.hit();
        }
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

    private static void spawnFire(int x, int y) {
        listObjects.add(new Fire(x, y, TILE_RADIUS));
    }

    public static void spawnFire(int x, int y, int range) {
        spawnFire(x, y);
        for (int i = 0; i < range; i++) {
            // Left
            spawnFire(x - (TILE_DIAMETER * (i + 1)), y);
            // Right
            spawnFire(x + (TILE_DIAMETER * (i + 1)), y);
            // Up
            spawnFire(x, y - (TILE_DIAMETER * (i + 1)));
            // Down
            spawnFire(x, y + (TILE_DIAMETER * (i + 1)));
        }
    }
}
