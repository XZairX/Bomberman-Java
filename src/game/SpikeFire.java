package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

public class SpikeFire extends FireObject {
    private static final Color SPIKEFIRE_COLOUR = Color.BLUE;

    public SpikeFire(int x, int y, int range) {
        super(x, y, range);
        this.FIRE_COLOUR = SPIKEFIRE_COLOUR;
    }

    private SpikeFire(int x, int y) {
        super(x, y);
        this.FIRE_COLOUR = SPIKEFIRE_COLOUR;
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
                for (int i = 0; i <= range; i++) {
                    try {
                        Thread.sleep(FIRE_RECURSION_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    SpikeFire fireLeft = new SpikeFire(x - (diameter * i), y);
                    SpikeFire fireRight = new SpikeFire(x + (diameter * i), y);
                    SpikeFire fireUp = new SpikeFire(x, y - (diameter * i));
                    SpikeFire fireDown = new SpikeFire(x, y + (diameter * i));

                    List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
                    for (GameObject object : listObject) {
                        if (object.getClass() == BlockHard.class || object instanceof BombObject) {

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
