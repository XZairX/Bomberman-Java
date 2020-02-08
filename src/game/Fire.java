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

    private int leftX, rightX, upY, downY;
    private boolean isLeftHit, isRightHit, isUpHit, isDownHit;

    public Fire(int x, int y, int range) {
        super(x, y);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                emitFire(x, y, range);

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

    private Fire(int x, int y) {
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
    private void emitFire(int x, int y, int range) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < range + 1; i++) {
                    try {
                        Thread.sleep(FIRE_RECURSION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    leftX = x - (diameter * i);
                    rightX = x + (diameter * i);
                    upY = y - (diameter * i);
                    downY = y + (diameter * i);

                    List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
                    for (GameObject object : listObject) {
                        if (object.getClass() == BlockHard.class || object.getClass() == BlockSoft.class) {

                            // Check if collided
                            if (!isLeftHit) {
                                if (object.x == leftX && object.y == y) {
                                    GameMain.addAliveGameObject(new Fire(leftX, y));
                                    isLeftHit = true;
                                }
                            }

                            if (!isRightHit) {
                                if (object.x == rightX && object.y == y) {
                                    GameMain.addAliveGameObject(new Fire(rightX, y));
                                    isRightHit = true;
                                }
                            }

                            if (!isUpHit) {
                                if (object.x == x && object.y == upY) {
                                    GameMain.addAliveGameObject(new Fire(x, upY));
                                    isUpHit = true;
                                }
                            }

                            if (!isDownHit) {
                                if (object.x == x && object.y == downY) {
                                    GameMain.addAliveGameObject(new Fire(x, downY));
                                    isDownHit = true;
                                }
                            }
                        }
                    }

                    // If not collided
                    if (!isLeftHit) {
                        GameMain.addAliveGameObject(new Fire(leftX, y));
                    }
                    if (!isRightHit) {
                        GameMain.addAliveGameObject(new Fire(rightX, y));
                    }
                    if (!isUpHit) {
                        GameMain.addAliveGameObject(new Fire(x, upY));
                    }
                    if (!isDownHit) {
                        GameMain.addAliveGameObject(new Fire(x, downY));
                    }

                    if (isLeftHit && isRightHit && isUpHit && isDownHit) {
                        break;
                    }
                }
            }
        });
        thread.start();
    }
}
