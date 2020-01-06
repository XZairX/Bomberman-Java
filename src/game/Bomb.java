package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

// Bomb drawn on top of player
// Possible resizing optimisation

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;

    public Bomb(int x, int y, double radius) {
        super(x, y, radius);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
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
