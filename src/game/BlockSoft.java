package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_OUTLINE_COLOUR;

public class BlockSoft extends GameObject {
    private static final Color TILE_COLOUR = Color.RED;
    private static final int DESTROY_DELAY = 500;

    public BlockSoft(int x, int y) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2);
        this.y = (y * diameter) + (diameter * 2);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    public void hit() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(DESTROY_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isDead = true;
            }
        });
        thread.start();
    }

    @Override
    public void draw(Graphics2D g) {
        /*
        // Debug Bounding Box
        g.setColor(Color.MAGENTA);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */
        g.setColor(TILE_COLOUR);
        g.fillRect(x, y, diameter, diameter);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, diameter, diameter);
    }
}
