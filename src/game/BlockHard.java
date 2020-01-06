package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import static game.Constants.TILE_OUTLINE_COLOUR;
import static game.Constants.TILE_RADIUS;

public class BlockHard extends GameObject {
    private static final Color TILE_COLOUR = Color.WHITE;

    public BlockHard(int x, int y, double radius) {
        super(x, y, radius);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    public static BlockHard spawnBlockHard(int x, int y) {
        x *= TILE_RADIUS * 2;
        y *= TILE_RADIUS * 2;
        return new BlockHard(x + TILE_RADIUS * 4, y + TILE_RADIUS * 4, TILE_RADIUS);
    }

    @Override
    public void draw(Graphics2D g) {
        /*
        // For viewing the bounding box
        g.setColor(Color.RED);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */

        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, TILE_RADIUS * 2, TILE_RADIUS * 2);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, TILE_RADIUS * 2, TILE_RADIUS * 2);
    }
}
