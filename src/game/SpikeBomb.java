package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SpikeBomb extends BombObject {
    private static final Color SPIKE_BOMB_COLOUR = Color.MAGENTA;

    public SpikeBomb(int x, int y, int range) {
        super(x, y, range);
        this.BOMB_COLOUR = SPIKE_BOMB_COLOUR;
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
        dropFire(Type.SPIKE);
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
