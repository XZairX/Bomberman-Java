package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;
import static game.GameMain.listObjects;

public class Fire extends GameObject {
    private static final Color FIRE_COLOUR = Color.YELLOW;
    private static final int FIRE_DELAY = 500;
    private static final int FIRE_RECURSION_DELAY = 10;

    public Fire(int x, int y, int radius) {
        super(x, y, radius);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
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
        } else if (other.getClass() != Fire.class) {
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

    public void spawnFire(int x, int y) {
        GameMain.addAliveGameObject(new Fire(x, y, TILE_RADIUS));
    }

    private void spawnFireLeft(int x, int y) {
        List<GameObject> listLeft = new ArrayList<>();
        listLeft.addAll(listObjects);
        for (GameObject object : listLeft) {
            if (object.getClass() == BlockHard.class && object.x == x && object.y == y) {
                hit();
                break;
            }
        }

        if (!isDead) {
            spawnFire(x, y);
        }
    }

    // Possibly convert into an object which spawns only 1 range fires recursively
    public void spawnFire(int x, int y, int rangeCurrent, int rangeMax) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(FIRE_RECURSION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (rangeCurrent < rangeMax) {
                    //spawnFire(x - (TILE_DIAMETER * (rangeCurrent + 1)), y);
                    spawnFireLeft(x - (TILE_DIAMETER * (rangeCurrent + 1)), y);

                    spawnFire(x + (TILE_DIAMETER * (rangeCurrent + 1)), y);
                    spawnFire(x, y - (TILE_DIAMETER * (rangeCurrent + 1)));
                    spawnFire(x, y + (TILE_DIAMETER * (rangeCurrent + 1)));

                    spawnFire(x, y, rangeCurrent + 1, rangeMax);
                }
            }
        });
        thread.start();
    }
}
