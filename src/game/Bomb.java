package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bomb extends BombObject {
    public Bomb(int x, int y, int range) {
        super(x, y, range);
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
        dropFire(Type.NORMAL);
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
