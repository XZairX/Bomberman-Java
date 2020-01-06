package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_OUTLINE_COLOUR;
import static game.Constants.TILE_RADIUS;

public class BlockSoft extends GameObject {
    private static final Color TILE_COLOUR = Color.RED;

    public BlockSoft(int x, int y, double radius) {
        super(x, y, radius);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    public static boolean canSpawnBlockSoft() {
        return ((int)(Math.random() * 100) < 66);
    }

    public static BlockSoft spawnBlockSoft(int x, int y) {
        x *= TILE_DIAMETER;
        y *= TILE_DIAMETER;
        return new BlockSoft(x + TILE_DIAMETER * 2, y + TILE_DIAMETER * 2, TILE_RADIUS);
    }

    @Override
    public void hit() {
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        /*
        // For viewing the bounding box
        g.setColor(Color.MAGENTA);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */

        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, TILE_DIAMETER, TILE_DIAMETER);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, TILE_DIAMETER, TILE_DIAMETER);
    }
}
