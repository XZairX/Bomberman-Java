package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class DangerousFire extends FireObject {
    private static final Color DANGEROUSFIRE_COLOUR = Color.RED;

    public DangerousFire(int x, int y, int range) {
        super(x, y, range);
        this.FIRE_COLOUR = DANGEROUSFIRE_COLOUR;
        hasExtendedFireDelay = true;
    }

    private DangerousFire(int x, int y) {
        super(x, y);
        this.FIRE_COLOUR = DANGEROUSFIRE_COLOUR;
        hasExtendedFireDelay = true;
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

    @Override
    public void emitFire(int x, int y, int range) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int max;

                if (range < 2) {
                    max = 1;
                } else if (range >= 2 && range < 5) {
                    max = 2;
                } else if (range >= 5 && range < 8) {
                    max = 3;
                } else {
                    max = 4;
                }

                List<DangerousFire> fireObject = new ArrayList<>();
                for (int i = 1; i <= max; i++) {
                    // North + NE
                    fireObject.add(new DangerousFire(x, y - (diameter * i)));
                    fireObject.add(new DangerousFire(x + (diameter * i), y - (diameter * i)));
                    // East + SE
                    fireObject.add(new DangerousFire(x + (diameter * i), y));
                    fireObject.add(new DangerousFire(x + (diameter * i), y + (diameter * i)));
                    // West + SW
                    fireObject.add(new DangerousFire(x - (diameter * i), y));
                    fireObject.add(new DangerousFire(x - (diameter * i), y + diameter * i));
                    // South + NW
                    fireObject.add(new DangerousFire(x, y + (diameter * i)));
                    fireObject.add(new DangerousFire(x - (diameter * i), y - (diameter * i)));

                    switch (i) {
                        case 2:
                            // North
                            fireObject.add(new DangerousFire(x - diameter, y - (diameter * i)));
                            fireObject.add(new DangerousFire(x + diameter, y - (diameter * i)));

                            // East
                            fireObject.add(new DangerousFire(x + (diameter * i), y - diameter));
                            fireObject.add(new DangerousFire(x + (diameter * i), y + diameter));

                            // South
                            fireObject.add(new DangerousFire(x + diameter, y + (diameter * i)));
                            fireObject.add(new DangerousFire(x - diameter, y + diameter * i));

                            // West
                            fireObject.add(new DangerousFire(x - (diameter * i), y + diameter));
                            fireObject.add(new DangerousFire(x - (diameter * i), y - diameter));
                            break;

                        case 3:
                            // North
                            fireObject.add(new DangerousFire(x - (diameter * (i - 1)), y - (diameter * i)));
                            fireObject.add(new DangerousFire(x - diameter, y - (diameter * i)));
                            fireObject.add(new DangerousFire(x + diameter, y - (diameter * i)));
                            fireObject.add(new DangerousFire(x + (diameter * (i - 1)), y - (diameter * i)));

                            // East
                            fireObject.add(new DangerousFire(x + (diameter * i), y - (diameter * (i - 1))));
                            fireObject.add(new DangerousFire(x + (diameter * i), y - diameter));
                            fireObject.add(new DangerousFire(x + (diameter * i), y + diameter));
                            fireObject.add(new DangerousFire(x + (diameter * i), y + (diameter * (i - 1))));

                            // South
                            fireObject.add(new DangerousFire(x + (diameter * (i - 1)), y + (diameter * i)));
                            fireObject.add(new DangerousFire(x + diameter, y + (diameter * i)));
                            fireObject.add(new DangerousFire(x - diameter, y + diameter * i));
                            fireObject.add(new DangerousFire(x - (diameter * (i - 1)), y + diameter * i));

                            // West
                            fireObject.add(new DangerousFire(x - (diameter * i), y + (diameter * (i - 1))));
                            fireObject.add(new DangerousFire(x - (diameter * i), y + diameter));
                            fireObject.add(new DangerousFire(x - (diameter * i), y - diameter));
                            fireObject.add(new DangerousFire(x - (diameter * i), y - (diameter * (i - 1))));
                            break;

                        case 4:
                            // North
                            fireObject.add(new DangerousFire(x - (diameter * (i - 1)), y - (diameter * i)));
                            fireObject.add(new DangerousFire(x - (diameter * (i - 2)), y - (diameter * i)));
                            fireObject.add(new DangerousFire(x - diameter, y - (diameter * i)));
                            fireObject.add(new DangerousFire(x + diameter, y - (diameter * i)));
                            fireObject.add(new DangerousFire(x + (diameter * (i - 2)), y - (diameter * i)));
                            fireObject.add(new DangerousFire(x + (diameter * (i - 1)), y - (diameter * i)));

                            // East
                            fireObject.add(new DangerousFire(x + (diameter * i), y - (diameter * (i - 1))));
                            fireObject.add(new DangerousFire(x + (diameter * i), y - (diameter * (i - 2))));
                            fireObject.add(new DangerousFire(x + (diameter * i), y - diameter));
                            fireObject.add(new DangerousFire(x + (diameter * i), y + diameter));
                            fireObject.add(new DangerousFire(x + (diameter * i), y + (diameter * (i - 2))));
                            fireObject.add(new DangerousFire(x + (diameter * i), y + (diameter * (i - 1))));

                            // South
                            fireObject.add(new DangerousFire(x + (diameter * (i - 1)), y + (diameter * i)));
                            fireObject.add(new DangerousFire(x + (diameter * (i - 2)), y + (diameter * i)));
                            fireObject.add(new DangerousFire(x + diameter, y + (diameter * i)));
                            fireObject.add(new DangerousFire(x - diameter, y + diameter * i));
                            fireObject.add(new DangerousFire(x - (diameter * (i - 2)), y + diameter * i));
                            fireObject.add(new DangerousFire(x - (diameter * (i - 1)), y + diameter * i));

                            // West
                            fireObject.add(new DangerousFire(x - (diameter * i), y + (diameter * (i - 1))));
                            fireObject.add(new DangerousFire(x - (diameter * i), y + (diameter * (i - 2))));
                            fireObject.add(new DangerousFire(x - (diameter * i), y + diameter));
                            fireObject.add(new DangerousFire(x - (diameter * i), y - diameter));
                            fireObject.add(new DangerousFire(x - (diameter * i), y - (diameter * (i - 2))));
                            fireObject.add(new DangerousFire(x - (diameter * i), y - (diameter * (i - 1))));
                            break;
                    }
                }

                for (TileObject tile : GameMain.getListTileObject()) {
                    for (DangerousFire fire : fireObject) {
                        if (fire.isColliding(tile)) {
                            GameMain.addAliveGameObject(fire);
                        }
                    }
                }
            }
        });
        thread.start();
    }
}
