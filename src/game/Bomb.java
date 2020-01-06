package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

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

    public static Bomb spawnBlockHard(int x, int y) {
        //if ()
        return new Bomb(x + TILE_DIAMETER * 2, y + TILE_DIAMETER * 2, TILE_RADIUS);
    }

    @Override
    protected void draw(Graphics2D g) {
        // For viewing the bounding box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, getBounds().width, getBounds().height);
    }
}
