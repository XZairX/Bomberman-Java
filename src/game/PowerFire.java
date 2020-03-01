package game;

import java.awt.Rectangle;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

public class PowerFire extends FireObject {
    private static final int POWERFIRE_DELAY = 1000;
    private static final int POWERFIRE_RANGE = 16;

    public PowerFire(int x, int y, int range) {
        super(x, y, range);
        this.FIRE_DELAY = POWERFIRE_DELAY;
    }

    private PowerFire(int x, int y) {
        super(x, y);
        this.FIRE_DELAY = POWERFIRE_DELAY;
    }

    @Override
    public void initialise() {
        super.initialise();
    }

    @Override
    public boolean isNotInitialised() {
        return super.isNotInitialised();
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

    public void emitFire(int x, int y, int range) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= POWERFIRE_RANGE; i++) {
                    try {
                        Thread.sleep(FIRE_RECURSION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    PowerFire fireLeft = new PowerFire(x - (diameter * i), y);
                    PowerFire fireRight = new PowerFire(x + (diameter * i), y);
                    PowerFire fireUp = new PowerFire(x, y - (diameter * i));
                    PowerFire fireDown = new PowerFire(x, y + (diameter * i));

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
