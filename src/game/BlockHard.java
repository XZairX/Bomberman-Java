package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_OUTLINE_COLOUR;

public class BlockHard extends GameObject {
    private static final Color BLOCKHARD_COLOUR = Color.WHITE;

    public BlockHard(int x, int y) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2);
        this.y = (y * diameter) + (diameter * 2);
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
        g.setColor(BLOCKHARD_COLOUR);
        g.fillRect(x, y, diameter, diameter);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
    }
}
