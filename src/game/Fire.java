package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listObjects;

public class Fire extends GameObject {
    private static final Color FIRE_COLOUR = Color.YELLOW;
    private static final int FIRE_DELAY = 500;

    public Fire(int x, int y, int radius) {
        super(x, y, radius);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(FIRE_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hit();
            }
        });
        thread.start();
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
        } else {
            other.hit();
            System.out.println("Fire hit " + other.getClass());
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

    // Possibly convert into an object which spawns only 1 range fires recursively
    public static void spawnFire(int x, int y, int range) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (range > 0) {
                    spawnFire(x - (TILE_DIAMETER * (range - 9)), y);
                    spawnFire(x + (TILE_DIAMETER * (range - 9)), y);
                    spawnFire(x, y - (TILE_DIAMETER * (range - 9)));
                    spawnFire(x, y + (TILE_DIAMETER * (range - 9)));

                    spawnFire(x, y, range - 1);
                }
            }
        });
        thread.start();
    }
}
