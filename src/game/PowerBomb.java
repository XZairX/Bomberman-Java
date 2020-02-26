package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class PowerBomb extends BombObject {
    public PowerBomb(int x, int y, int range, int bombID) {
        super(x, y, range, bombID);
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
        dropFire(Type.POWER);
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("P", this.x + 1, this.y + radius);
    }
}
