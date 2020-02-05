package game;

import java.awt.Color;
import java.awt.Graphics2D;

import static game.Constants.TILE_OUTLINE_COLOUR;

public class BlockTile extends GameObject {
    private static final Color TILE_COLOUR = Color.GREEN;

    private boolean isAvailable;

    public BlockTile(int x, int y) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2);
        this.y = (y * diameter) + (diameter * 2);
        isAvailable = true;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, diameter, diameter);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(isAvailable).substring(0, 1).toUpperCase(), x + 7, y + 15);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void toggleAvailability() {
        isAvailable = (isAvailable == true) ? false : true;
    }
}
