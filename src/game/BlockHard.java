package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
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

    @Override
    public void draw(Graphics2D g) {
        /*
        // Debug Bounding Box
        g.setColor(Color.RED);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */
        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, TILE_DIAMETER, TILE_DIAMETER);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, TILE_DIAMETER, TILE_DIAMETER);
    }

    public static BlockHard spawnBlockHard(int x, int y) {
        x *= TILE_DIAMETER;
        y *= TILE_DIAMETER;
        return new BlockHard(x + TILE_DIAMETER * 2, y + TILE_DIAMETER * 2, TILE_RADIUS);
    }
}
