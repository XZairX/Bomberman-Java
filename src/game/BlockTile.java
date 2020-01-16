package game;

import java.awt.Color;
import java.awt.Graphics2D;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_OUTLINE_COLOUR;
import static game.Constants.TILE_RADIUS;

public class BlockTile extends GameObject {
    private static final Color TILE_COLOUR = Color.GREEN;

    private boolean isAvailable = true;

    public BlockTile(int x, int y, double radius) {
        super(x, y, radius);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, TILE_DIAMETER, TILE_DIAMETER);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, TILE_DIAMETER, TILE_DIAMETER);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void toggleAvailability() {
        isAvailable = (isAvailable == true) ? false : true;
    }

    public static BlockTile spawnBlockTile(int x, int y) {
        x *= TILE_DIAMETER;
        y *= TILE_DIAMETER;
        return new BlockTile(x + TILE_DIAMETER * 2, y + TILE_DIAMETER * 2, TILE_RADIUS);
    }
}
