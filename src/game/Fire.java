package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Fire extends FireObject {
    public Fire(int x, int y, int range) {
        super(x, y, range);
    }

    private Fire(int x, int y) {
        super(x, y);
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
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

    @Override
    public void emitFire(int x, int y, int range) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < range + 1; i++) {
                    try {
                        Thread.sleep(FIRE_RECURSION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Fire fireLeft = new Fire(x - (diameter * i), y);
                    Fire fireRight = new Fire(x + (diameter * i), y);
                    Fire fireUp = new Fire(x, y - (diameter * i));
                    Fire fireDown = new Fire(x, y + (diameter * i));

                    List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
                    for (GameObject object : listObject) {
                        if (object instanceof BlockObject || object instanceof BombObject) {

                            // Check if collided
                            if (!isFireLeftHit) {
                                if (fireLeft.isColliding(object)) {
                                    collisionHandling(object);
                                    isFireLeftHit = true;
                                }
                            }

                            if (!isFireRightHit) {
                                if (fireRight.isColliding(object)) {
                                    collisionHandling(object);
                                    isFireRightHit = true;
                                }
                            }

                            if (!isFireUpHit) {
                                if (fireUp.isColliding(object)) {
                                    collisionHandling(object);
                                    isFireUpHit = true;
                                }
                            }

                            if (!isFireDownHit) {
                                if (fireDown.isColliding(object)) {
                                    collisionHandling(object);
                                    isFireDownHit = true;
                                }
                            }
                        }
                    }

                    // IF not collided
                    if (!isFireLeftHit) {
                        GameMain.addAliveGameObject(fireLeft);
                    }
                    if (!isFireRightHit) {
                        GameMain.addAliveGameObject(fireRight);
                    }
                    if (!isFireUpHit) {
                        GameMain.addAliveGameObject(fireUp);
                    }
                    if (!isFireDownHit) {
                        GameMain.addAliveGameObject(fireDown);
                    }

                    if (isFireLeftHit && isFireRightHit && isFireUpHit && isFireDownHit) {
                        break;
                    }
                }
            }
        });
        thread.start();
    }
}
