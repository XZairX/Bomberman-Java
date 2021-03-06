package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class DangerousBomb extends BombObject {
    private static final Color DANGEROUSBOMB_COLOUR = Color.RED;

    public DangerousBomb(int x, int y, int range, int bombID) {
        super(x, y, range, bombID);
        this.BOMB_COLOUR = DANGEROUSBOMB_COLOUR;
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
        dropFire(Type.DANGEROUS);
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
