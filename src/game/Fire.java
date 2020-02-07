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

    // Multiple Fire constructors with enums for directional references (x, y, LEFT), (x, y, RIGHT), etc.
    // Extra Fire constructor with differing behaviour based on enum

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
        spawnFire(x, y);

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
                    int upY = y - (diameter * i);
                    int downY = y + (diameter * i);

                    List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
                    for (GameObject object : listObject) {
                        if (object.getClass() == BlockHard.class || object.getClass() == BlockSoft.class) {

                            if (!isLeftHit) {
                                if (object.x == leftX && object.y == y) {
                                    spawnFire(leftX, y);
                                    isLeftHit = true;
                                }
                            }

                            if (!isRightHit) {
                                if (object.x == rightX && object.y == y) {
                                    spawnFire(rightX, y);
                                    isRightHit = true;
                                }
                            }

                            if (!isUpHit) {
                                if (object.x == x && object.y == upY) {
                                    spawnFire(x, upY);
                                    isUpHit = true;
                                }
                            }

                            if (!isDownHit) {
                                if (object.x == x && object.y == downY) {
                                    spawnFire(x, downY);
                                    isDownHit = true;
                                }
                            }
                        }
                    }

                    if (!isLeftHit) {
                        spawnFire(leftX, y);
                    }
                    if (!isRightHit) {
                        spawnFire(rightX, y);
                    }
                    if (!isUpHit) {
                        spawnFire(x, upY);
                    }
                    if (!isDownHit) {
                        spawnFire(x, downY);
                    }

                    if (isLeftHit && isRightHit && isUpHit && isDownHit) {
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
