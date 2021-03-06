package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Random;

public class BlockSoft extends BlockObject {
    private static final Random RNG = new Random();
    private static final Color BLOCKSOFT_COLOUR = Color.RED;
    private static final Color BLOCKSOFT_DESTROY_COLOUR = Color.ORANGE;
    private static final int BLOCKSOFT_DESTROY_DELAY = 500;

    public BlockSoft(int x, int y) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2);
        this.y = (y * diameter) + (diameter * 2);
        this.BLOCK_COLOUR = BLOCKSOFT_COLOUR;
    }

    @Override
    public void initialise() {
        super.initialise();
    }

    @Override
    public boolean isNotInitialised() {
        return super.isNotInitialised();
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    public boolean isColliding(GameObject other) {
        return super.isColliding(other);
    }

    @Override
    public void collisionHandling(GameObject other) {
        super.collisionHandling(other);
    }

    @Override
    public void hit() {
        isHit = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(BLOCKSOFT_DESTROY_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (canSpawnBlockItem()) {
                    GameMain.addAliveGameObject(new BlockItem(x, y));
                }
                BlockSoft.super.hit();
            }
        });
        thread.start();
    }

    @Override
    public void draw(Graphics2D g) {
        if (isHit) {
            BLOCK_COLOUR = BLOCKSOFT_DESTROY_COLOUR;
        }
        super.draw(g);
    }

    public boolean isNotHit() {
        return !isHit;
    }

    private boolean canSpawnBlockItem() {
        return (RNG.nextInt(100) < 45);
    }
}
