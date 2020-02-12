package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class BombPower extends BombObject {
    private static final Color POWERBOMB_COLOUR = Color.RED;

    public BombPower(int x, int y, int range) {
        super(x, y, range);
        this.range = 16;
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
        g.setColor(POWERBOMB_COLOUR);
        g.fillOval(x, y, diameter, diameter);

        super.draw(g);
    }
}
