package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_OUTLINE_COLOUR;

public class BlockItem extends GameObject {
    private static final Color BLOCKITEM_COLOUR = Color.CYAN;

    private final int X_CENTRE = x + 3;
    private final int Y_CENTRE = y + 15;

    private enum Item {
        BOMBUP, FIREUP, SKATEUP,
        KICK, PUNCH, THROW,
        FULLFIRE, POWERBOMB, SPIKEBOMB,
        HEART, DANGEROUSBOMB, REMOTEBOMB,
        BOMBDOWN, FIREDOWN, SKATEDOWN,
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
            case BOMBUP:
                g.drawString("B+", X_CENTRE, Y_CENTRE);
                break;
            case FIREUP:
                g.drawString("F+", X_CENTRE, Y_CENTRE);
                break;
            case SKATEUP:
                g.drawString("S+", X_CENTRE, Y_CENTRE);
                break;

            case KICK:
                g.drawString("S+", X_CENTRE, Y_CENTRE);
                break;
            case PUNCH:
                break;
            case THROW:
                break;

            case FULLFIRE:
                g.drawString("FF", X_CENTRE, Y_CENTRE);
                break;
            case POWERBOMB:
                g.drawString("PB", X_CENTRE, Y_CENTRE);
                break;
            case SPIKEBOMB:
                break;

            case HEART:
                g.drawString("H+", X_CENTRE, Y_CENTRE);
                break;
            case DANGEROUSBOMB:
                break;
            case REMOTEBOMB:
                break;

            case BOMBDOWN:
                g.drawString("B-", X_CENTRE, Y_CENTRE);
                break;
            case FIREDOWN:
                g.drawString("F-", X_CENTRE, Y_CENTRE);
                break;
            case SKATEDOWN:
                g.drawString("S-", X_CENTRE, Y_CENTRE);
                break;
        }
    }

    private void setItem() {
        double random = (Math.random() * 100);

        if (random < 60) {
            setBasicItemUP();
        } else if (random >= 60 && random < 70) {
            setUtilityItem();
        } else if (random >= 70 && random < 80) {
            setBasicSpecialItem();
        } else if (random >= 80 && random < 90) {
            setAdvancedSpecialItem();
        } else {
            setBasicItemDOWN();
        }
    }

    private void setBasicItemUP() {
        double random = ((int)(Math.random() * 100));

        if (random < 33) {
            item = Item.BOMBUP;
        } else if (random >= 33 && random < 66) {
            item = Item.FIREUP;
        } else {
            item = Item.SKATEUP;
        }
    }

    private void setUtilityItem() {
        double random = ((int)(Math.random() * 100));

        if (random < 33) {
            item = Item.KICK;
        } else if (random >= 33 && random < 66) {
            item = Item.PUNCH;
        } else {
            item = Item.THROW;
        }
    }

    private void setBasicSpecialItem() {
        double random = ((int)(Math.random() * 100));

        if (random < 33) {
            item = Item.FULLFIRE;
        } else if (random >= 33 && random < 66) {
            item = Item.POWERBOMB;
        } else {
            item = Item.SPIKEBOMB;
        }
    }

    private void setAdvancedSpecialItem() {
        double random = ((int)(Math.random() * 100));

        if (random < 33) {
            item = Item.HEART;
        } else if (random >= 33 && random < 66) {
            item = Item.DANGEROUSBOMB;
        } else {
            item = Item.REMOTEBOMB;
        }
    }

    private void setBasicItemDOWN() {
        double random = ((int)(Math.random() * 100));

        if (random < 33) {
            item = Item.BOMBDOWN;
        } else if (random >= 33 && random < 66) {
            item = Item.FIREDOWN;
        } else {
            item = Item.SKATEDOWN;
        }
    }

    private void giveItem(Player player) {
        switch (item) {
            case BOMBUP:
                player.bombUp();
                break;
            case FIREUP:
                player.fireUp();
                break;
            case SKATEUP:
                player.speedUp();
                break;

            case KICK:
                break;
            case PUNCH:
                break;
            case THROW:
                break;

            case FULLFIRE:
                player.fullFire();
                break;
            case POWERBOMB:
                break;
            case SPIKEBOMB:
                break;

            case HEART:
                player.heartUp();
                break;
            case DANGEROUSBOMB:
                break;
            case REMOTEBOMB:
                break;

            case BOMBDOWN:
                player.bombDown();
                break;
            case FIREDOWN:
                player.fireDown();
                break;
            case SKATEDOWN:
                player.speedDown();
                break;
        }
    }
}
