package game;

import java.awt.Graphics2D;
import java.awt.Color;

import static game.Constants.TILE_OUTLINE_COLOUR;
import static game.Constants.TILE_RADIUS;

public class BlockTile extends GameObject {
    private static final Color TILE_COLOUR = Color.GREEN;

    public BlockTile(int x, int y, double radius) {
        super(x, y, radius);
    }

    /*
    // Reference for tiles and blocks (row formation)
    0 = Tile, 1 = Hard, 2 = Soft
    01: 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0
    02: 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0
    03: 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2
    04: 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1
    (05)03
    (06)04
    (07)03
    (08)04
    (09)03
    (10)02
    (11)01
    // (13 tiles across, 11 tiles down)
    */

    public static BlockTile spawnBlockTile(int x, int y) {
        x *= TILE_RADIUS * 2;
        y *= TILE_RADIUS * 2;
        return new BlockTile(x, y, TILE_RADIUS);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, TILE_RADIUS * 2, TILE_RADIUS * 2);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, TILE_RADIUS * 2, TILE_RADIUS * 2);
    }
}
