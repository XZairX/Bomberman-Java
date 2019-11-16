package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_RADIUS;

public class Player extends GameObject {
    private static final Color PLAYER1_COLOUR = Color.BLUE;
    private static final Color PLAYER2_COLOUR = Color.RED;
    private static final Color PLAYER3_COLOUR = Color.MAGENTA;
    private static final Color PLAYER4_COLOUR = Color.YELLOW; // GREEN (BlockTile is currently using this)

    private int playerID;

    private int heart = 1;
    private int bomb = 1;
    private int fire = 1;
    private int skate = 4;
    private double speed = (skate - 3) * 3;
    private boolean canMove = true;

    public Player(int x, int y, double radius, int playerID) {
        super(x, y, radius);
        this.x *= TILE_RADIUS * 2;
        this.x += (TILE_RADIUS * 4 + (TILE_RADIUS / 4));
        this.y *= TILE_RADIUS * 2;
        this.y += (TILE_RADIUS * 4 + (TILE_RADIUS / 4));
        this.radius += TILE_RADIUS * 1.5;
        this.playerID = playerID;
    }

    @Override
    public Rectangle getBounds() {
        radius = TILE_RADIUS * 1.5;
        return new Rectangle(x + 1, y + 1, (int)radius, (int)radius);
    }

    @Override
    public boolean isColliding(GameObject other) {
        return super.isColliding(other);
    }

    @Override
    public void collisionHandling(GameObject other) {
        if (isColliding(other)) {
            // Need to detect which side of the bounding box is hit and call appropriate move method
            // e.g. if (collision at bottom of BlockHard bounding box) { moveDown(); // to counter up movement }

            // OR create a secondary bounding box which looks slightly ahead of the player to predict a collision

            // OR detect which movement was used to trigger collision and offset it
            // e.g. if (moveUp() triggered collision) moveDown()

            if (other instanceof BlockHard) {
                // Moves right by default
                moveRight();
                System.out.println("collision hard");
            }

            if (other instanceof BlockSoft) {
                System.out.println("collision soft");
            }
        }
    }

    public void moveLeft() {
        this.x -= speed;
    }

    public void moveRight() {
        this.x += speed;
    }

    public void moveUp() {
        this.y -= speed;
    }

    public void moveDown() {
        this.y += speed;
    }

    public void heartUp() {
        if (heart < 3) {
            heart++;
        }
    }

    public void bombDown() {
        if (bomb > 1) {
            bomb--;
        }
    }

    public void bombUp() {
        if (bomb < 8) {
            bomb++;
        }
    }

    public void fireDown() {
        if (fire > 1) {
            fire--;
        }
    }

    public void fireUp() {
        if (fire < 8) {
            fire++;
        }
    }

    public void speedDown() {
        if (skate > 1) {
            skate--;
        }
    }

    public void speedUp() {
        if (skate < 8) {
            skate++;
        }
    }

    public void debugPowerUps() {
        System.out.println(
                "ID: player" + playerID + "\n \t\t" +
                        "Heart: " + heart + " \t" +
                        "Bomb: " + bomb + " \t" +
                        "Fire: " + fire + " \t" +
                        "Skate: " + skate);
    }

    @Override
    public void draw(Graphics2D g) {
        // Temporary Bounding box visibility
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getBounds().x, getBounds().y, (int)getBounds().width, (int)getBounds().height);

        switch (playerID) {
            case 1: g.setColor(PLAYER1_COLOUR);
                break;
            case 2: g.setColor(PLAYER2_COLOUR);
                break;
            case 3: g.setColor(PLAYER3_COLOUR);
                break;
            case 4: g.setColor(PLAYER4_COLOUR);
                break;
        }
        g.fillOval(x, y, (int)(TILE_RADIUS * 1.5), (int)(TILE_RADIUS * 1.5));
    }
}
