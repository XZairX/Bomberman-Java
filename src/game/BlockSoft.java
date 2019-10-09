package game;

import java.awt.Graphics2D;
import java.awt.Color;

import static game.Constants.TILE_OUTLINE_COLOUR;
import static game.Constants.TILE_RADIUS;

public class BlockSoft extends GameObject {
    private static final Color TILE_COLOUR = Color.RED;

    public BlockSoft(int x, int y, double radius) {
        super(x, y, radius);
    }

    // Currently 100% spawn rate, to be adjusted at a later time
    public static BlockSoft spawnBlockSoft(int x, int y) {
        x *= TILE_RADIUS * 2;
        y *= TILE_RADIUS * 2;
        return new BlockSoft(x, y, TILE_RADIUS);
    }

    @Override
    public void hit() {
        super.hit();;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, TILE_RADIUS * 2, TILE_RADIUS * 2);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, TILE_RADIUS * 2, TILE_RADIUS * 2);
    }
}
