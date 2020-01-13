package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;
import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;

    public Bomb(int x, int y, double radius) {
        super(x, y, radius);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

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
    }

    @Override
    protected void draw(Graphics2D g) {
        /*
        // For viewing the bounding box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */

        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, TILE_DIAMETER, TILE_DIAMETER);
    }
}
