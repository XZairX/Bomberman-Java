package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlockHard extends BlockObject {
    private static final Color BLOCKHARD_COLOUR = Color.WHITE;

    public BlockHard(int x, int y) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2);
        this.y = (y * diameter) + (diameter * 2);
        this.BLOCK_COLOUR = BLOCKHARD_COLOUR;
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
}
