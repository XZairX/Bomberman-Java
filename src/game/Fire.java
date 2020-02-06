package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Fire extends GameObject {
    private static final Color FIRE_COLOUR = Color.YELLOW;
    private static final int FIRE_DELAY = 500;
    private static final int FIRE_RECURSION_DELAY = 10;

    public Fire(int x, int y) {
        super(x, y);

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
        } else if (other.getClass() == BlockSoft.class) {
            other.hit();
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

    // Possibly convert into an object which spawns only 1 range fires recursively
    public void spawnFire(int x, int y, int rangeMax) {
        // Bomb currently does this, will need to fix after moving to constructor
        //spawnFire(x, y);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isLeftHit = false;
                boolean isRightHit = false;
                boolean isUpHit = false;
                boolean isDownHit = false;

                for (int i = 0; i < rangeMax + 1; i++) {

                    try {
                        Thread.sleep(FIRE_RECURSION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int leftX = x - (diameter * i);
                    int rightX = x + (diameter * i);

                    List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
                    for (GameObject object : listObject) {
                        if (!isLeftHit) {
                            if (object.getClass() == BlockHard.class && object.x == leftX && object.y == y) {
                                isLeftHit = true;
                            }
                        }

                        if (!isRightHit) {
                            if (object.getClass() == BlockHard.class && object.x == rightX && object.y == y) {
                                isRightHit = true;
                            }
                        }
                    }

                    if (!isLeftHit) {
                        spawnFire(leftX, y);
                    }
                    if (!isRightHit) {
                        spawnFire(rightX, y);
                    }

                    if (isLeftHit && isRightHit) {
                        break;
                    }
                }
            }
        });
        thread.start();
    }

    private void spawnFire(int x, int y) {
        GameMain.addAliveGameObject(new Fire(x, y));
    }
}
