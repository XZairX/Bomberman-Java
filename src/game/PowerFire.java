package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

public class PowerFire extends FireObject {
    private static final Color POWERFIRE_COLOUR = Color.ORANGE;
    private static final int POWERFIRE_DELAY = 100;
    private static final int FIRE_RECURSION_DELAY = 10;
    private static final int POWERFIRE_RANGE = 16;

    public PowerFire(int x, int y, int range) {
        super(x, y);
        this.FIRE_DELAY = POWERFIRE_DELAY;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                emitPowerFire(x, y, POWERFIRE_RANGE);
            }
        });
        thread.start();
    }

    private PowerFire(int x, int y) {
        super(x, y);
        this.FIRE_DELAY = POWERFIRE_DELAY;
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
        g.setColor(POWERFIRE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
        g.setColor(POWERFIRE_COLOUR);
        g.fillRect(x, y, diameter, diameter);
    }

    private void emitPowerFire(int x, int y, int range) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < range + 1; i++) {
                    try {
                        Thread.sleep(FIRE_RECURSION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    PowerFire fireLeft = new PowerFire(x - (diameter * i), y);
                    PowerFire fireRight = new PowerFire(x + (diameter * i), y);
                    PowerFire fireUp = new PowerFire (x, y - (diameter * i));
                    PowerFire fireDown = new PowerFire (x, y + (diameter * i));

                    List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
                    for (GameObject object : listObject) {
                        if (object.getClass() == BlockHard.class || object.getClass() == BlockSoft.class
                                || object.getClass() == BlockItem.class) {

                            // Check if collided
                            if (!isFireLeftHit) {
                                if (fireLeft.isColliding(object)) {
                                    GameMain.addAliveGameObject(fireLeft);
                                    isFireLeftHit = true;
                                }
                            }

                            if (!isFireRightHit) {
                                if (fireRight.isColliding(object)) {
                                    GameMain.addAliveGameObject(fireRight);
                                    isFireRightHit = true;
                                }
                            }

                            if (!isFireUpHit) {
                                if (fireUp.isColliding(object)) {
                                    GameMain.addAliveGameObject(fireUp);
                                    isFireUpHit = true;
                                }
                            }

                            if (!isFireDownHit) {
                                if (fireDown.isColliding(object)) {
                                    GameMain.addAliveGameObject(fireDown);
                                    isFireDownHit = true;
                                }
                            }
                        }
                    }

                    // If not collided
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