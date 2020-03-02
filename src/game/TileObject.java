package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import static game.Constants.TILE_OUTLINE_COLOUR;

public class TileObject extends GameObject {
    private static final Color BLOCKTILE_COLOUR = Color.GREEN;
    private static final Color DEBUG_AVAILABILITY_COLOUR = Color.BLUE;

    private boolean isAvailable;

    public TileObject(int x, int y) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2);
        this.y = (y * diameter) + (diameter * 2);
        isAvailable = true;
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
        g.setColor(BLOCKTILE_COLOUR);
        g.fillRect(x, y, diameter, diameter);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, diameter, diameter);

        // Debugging TileObject isAvailable boolean flag
        g.setColor(DEBUG_AVAILABILITY_COLOUR);
        g.drawString(String.valueOf(isAvailable).substring(0, 1).toUpperCase(), x + 7, y + 15);
    }

    /*public boolean isAvailable() {
        return isAvailable;
    }

    public void toggleAvailability() {
        isAvailable = (isAvailable == true) ? false : true;
    }*/
}
