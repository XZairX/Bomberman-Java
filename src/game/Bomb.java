package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_RADIUS;
import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

// Bomb drawn on top of player
// Possible resizing optimisation
// Bomb snap to BlockTile's (0, 0) (-2 to x and y)
// Refactor Tile diameter for readability

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;

    public Bomb(int x, int y, double radius) {
        super(x, y, radius);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

// Player drops bomb
// Check blocktile based on position of dropped bomb
// (possibly loop through blocktiles to find closest and assign based on returned result)
// Check if tile is free
// Add bomb to list and place

    public static void spawnBomb(int x, int y) {
        //System.out.println("Px: " + x + ", " + "Py: " + y);
        x = Math.round(x / 20) * 20;
        y = Math.round(y / 20) * 20;
        //System.out.println("Bx: " + x + ", " + "By: " + y);
        //System.out.println();
        for (BlockTile tile : listBlockTile) {
            if (tile.x == x && tile.y == y) {
                if (tile.isAvailable()) {
                    System.out.println(tile.x + ", " + tile.y);
                    listObjects.add(new Bomb(x, y, TILE_RADIUS));
                    System.out.println("Bomb placed");
                    tile.toggleAvailability();
                } else {
                    System.out.println("Space occupied");
                }
            }
        }
        //listObjects.add(new Bomb(x, y, TILE_RADIUS * 0.5));
    }

    @Override
    protected void draw(Graphics2D g) {
        /*
        // For viewing the bounding box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */

        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, getBounds().width, getBounds().height);
    }
}
