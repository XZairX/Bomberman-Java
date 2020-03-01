package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_OUTLINE_COLOUR;

public abstract class BlockObject extends GameObject {
    protected Color BLOCK_COLOUR;

    protected BlockObject(int x, int y) {
        super(x, y);
    }

    @Override
    protected void initialise() {
        super.initialise();
    }

    @Override
    protected boolean isNotInitialised() {
        return super.isNotInitialised();
    }

    @Override
    protected Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    protected boolean isColliding(GameObject other) {
        return super.isColliding(other);
    }

    @Override
    protected void collisionHandling(GameObject other) {
        super.collisionHandling(other);
    }

    @Override
    protected void hit() {
        super.hit();
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(BLOCK_COLOUR);
        g.fillRect(x, y, diameter, diameter);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
    }
}
