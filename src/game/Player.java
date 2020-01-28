package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listObjects;

public class Player extends GameObject {
    private static final Color PLAYER1_COLOUR = Color.BLUE;
    private static final Color PLAYER2_COLOUR = Color.RED;
    private static final Color PLAYER3_COLOUR = Color.MAGENTA;
    private static final Color PLAYER4_COLOUR = Color.YELLOW; // GREEN (BlockTile is currently using this)

    private final int playerID;

    private int heart = 1;
    private int bomb = 99;
    private int fire = 1;
    private int skate = 4;
    private double speed = 3;//(skate - 3) * TILE_DIAMETER; //3; // (Speed modifier)

    private boolean canMove;
    private boolean moveLeft, moveRight, moveUp, moveDown;
    private boolean canDropBomb;

    public Player(int x, int y, int radius, int playerID) {
        super(x, y, radius);
        this.x *= TILE_DIAMETER;
        this.x += (TILE_DIAMETER * 2 + (TILE_RADIUS / 2));
        this.y *= TILE_DIAMETER;
        this.y += (TILE_DIAMETER * 2 + (TILE_RADIUS / 2));
        this.radius *= 0.5;
        this.diameter = this.radius * 2;
        this.playerID = playerID;
        canMove = true;
        canDropBomb = true;
    }

    @Override
    public Rectangle getBounds() {
        if (moveLeft) {
            return new Rectangle(x - (int)speed, y, radius * 2, radius * 2);
        }
        if (moveRight) {
            return new Rectangle(x + (int)speed, y, radius * 2, radius * 2);
        }
        if (moveUp) {
            return new Rectangle(x, y - (int)speed, radius * 2, radius * 2);
        }
        if (moveDown) {
            return new Rectangle(x, y + (int)speed, radius * 2, radius * 2);
        }
        return super.getBounds();
    }

    @Override
    public boolean isColliding(GameObject other) {
        return super.isColliding(other);
    }

    @Override
    public void collisionHandling(GameObject other) {
        super.collisionHandling(other);

        if (other.getClass() == BlockHard.class) {
            cancelCollisionMovement();
        }

        if (other.getClass() == BlockSoft.class) {
            cancelCollisionMovement();
        }

        if (other.getClass() == Bomb.class) {
            Bomb bomb = (Bomb)other;
            if (bomb.getIsCollisionActive()) {
                cancelCollisionMovement();
            }
        }
    }

    @Override
    public void hit() {
        System.out.println("player hit");
    }

    @Override
    public void draw(Graphics2D g) {
        if (canMove) {
            movePlayer();
        }

        // Debug Bounding Box
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

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
        g.fillOval(x, y, diameter, diameter);
    }

    public void setMoveLeft(boolean movement) {
        moveLeft = movement;
    }

    public void setMoveRight(boolean movement) {
        moveRight = movement;
    }

    public void setMoveUp(boolean movement) {
        moveUp = movement;
    }

    public void setMoveDown(boolean movement) {
        moveDown = movement;
    }

    private void movePlayer() {
        if (moveLeft) {
            x -= speed;
        }
        if (moveRight) {
            x += speed;
        }
        if (moveUp) {
            y -= speed;
        }
        if (moveDown) {
            y += speed;
        }
    }

    private void cancelCollisionMovement() {
        canMove = false;
        if (moveLeft) {
            moveLeft = false;
            x += speed;
            x = Math.round((x / TILE_DIAMETER) * TILE_DIAMETER);
        }
        if (moveRight) {
            moveRight = false;
            x -= speed;
            x = Math.round((x / TILE_DIAMETER) * TILE_DIAMETER) + (TILE_DIAMETER - diameter);
        }
        if (moveUp) {
            moveUp = false;
            y += speed;
            y = Math.round((y / TILE_DIAMETER) * TILE_DIAMETER);
        }
        if (moveDown) {
            moveDown = false;
            y -= speed;
            y = Math.round((y / TILE_DIAMETER) * TILE_DIAMETER) + (TILE_DIAMETER - diameter);
        }
        canMove = true;
    }

    public void canDropBomb() {
        canDropBomb = true;
    }

    public void dropBomb() {
        if (canDropBomb) {
            int droppedBombs = 0;
            for (GameObject object : listObjects) {
                if (object.getClass() == Bomb.class) {
                    droppedBombs++;
                }
            }
            if (droppedBombs < bomb) {
                Bomb.spawnBomb(x + radius, y + radius);
            }
            canDropBomb = false;
        }
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
}
