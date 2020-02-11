package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_OUTLINE_COLOUR;

public class BlockItem extends GameObject {
    private static final Color BLOCKITEM_COLOUR = Color.CYAN;

    private enum Item {
        HEART,
        BOMBUP, FIREUP, SKATEUP,
        BOMBDOWN, FIREDOWN, SKATEDOWN,
        KICK, PUNCH, THROW,
        POWERBOMB, FULLFIRE,
        DANGEROUSBOMB, REMOTEBOMB
    }
    private Item item;

    public BlockItem(int x, int y) {
        super(x, y);
        setItem();
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
        if (other.getClass() == Player.class) {
            giveItem((Player)other);
            hit();
        }
    }

    @Override
    public void hit() {
        super.hit();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(BLOCKITEM_COLOUR);
        g.fillRect(x, y, diameter, diameter);
        g.setColor(TILE_OUTLINE_COLOUR);
        g.drawRect(x, y, diameter, diameter);

        g.setColor(Color.BLACK);

        switch (item) {
            case HEART:
                g.drawString("H+", x + 3, y + 15);
                break;
            case BOMBUP:
                g.drawString("B+", x + 3, y + 15);
                break;
            case BOMBDOWN:
                g.drawString("B-", x + 3, y + 15);
                break;
            case FIREUP:
                g.drawString("F+", x + 3, y + 15);
                break;
            case FIREDOWN:
                g.drawString("F-", x + 3, y + 15);
                break;
            case SKATEUP:
                g.drawString("S+", x + 3, y + 15);
                break;
            case SKATEDOWN:
                g.drawString("S-", x + 3, y + 15);
                break;
            case POWERBOMB:
                g.drawString("PB", x + 3, y + 15);
                break;
            case FULLFIRE:
                g.drawString("FF", x + 3, y + 15);
                break;
        }
    }

    private void setItem() {
        double random = (Math.random() * 100);

        if (random < 10) {
            item = Item.HEART;
        } else if (random >= 10 && random < 20) {
            item = Item.FULLFIRE;
        } else if (random >= 20 && random < 80) {
            setBasicItemUP();
        } else {
            setBasicItemDOWN();
        }
    }

    private void setBasicItemUP() {
        double random = (Math.random() * 100);

        if (random < 40) {
            item = Item.BOMBUP;
        } else if (random >= 40 && random < 80) {
            item = Item.FIREUP;
        } else {
            item = Item.SKATEUP;
        }
    }

    private void setBasicItemDOWN() {
        double random = (Math.random() * 100);

        if (random < 40) {
            item = Item.BOMBDOWN;
        } else if (random >= 40 && random < 80) {
            item = Item.FIREDOWN;
        } else {
            item = Item.SKATEDOWN;
        }
    }

    private void giveItem(Player player) {
        switch (item) {
            case HEART:
                player.heartUp();
                break;
            case BOMBUP:
                player.bombUp();
                break;
            case BOMBDOWN:
                player.bombDown();
                break;
            case FIREUP:
                player.fireUp();
                break;
            case FIREDOWN:
                player.fireDown();
                break;
            case SKATEUP:
                player.speedUp();
                break;
            case SKATEDOWN:
                player.speedDown();
                break;
            case FULLFIRE:
                player.fullFire();
                break;
            case POWERBOMB:
                break;
        }
    }
}
