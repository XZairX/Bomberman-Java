package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.FRAME_HEIGHT;
import static game.Constants.FRAME_WIDTH;
import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

import static game.GameMain.listObjects;
import static game.GameMain.numberOfPlayers;

public class Player extends GameObject {
    private static final Color PLAYER1_COLOUR = Color.BLUE;
    private static final Color PLAYER2_COLOUR = Color.RED;
    private static final Color PLAYER3_COLOUR = Color.MAGENTA;
    private static final Color PLAYER4_COLOUR = Color.YELLOW; // GREEN (BlockTile is currently using this)
    private static final int PLAYER_INVINCIBILITY = 2500;
    private static final int HEART_MAX = 3;
    private static final int MIN = 1;
    private static final int MAX = 8;

    private final int playerID;

    private int heart = 1;
    private int bomb = 1;
    private int fire = 1;
    private int skate = 4;
    private double speed = 3;

    private boolean canMove;
    private boolean moveLeft, moveRight, moveUp, moveDown;
    private boolean canDropBomb;
    private boolean isInvincible;

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
            if (((Bomb)other).getIsCollisionActive()) {
                cancelCollisionMovement();
            }
        }
    }

    @Override
    public void hit() {
        if (!isInvincible) {
            heart--;
            if (heart == 0) {
                numberOfPlayers--;
                System.out.println("Player " + playerID + " died.");
                super.hit();
            } else {
                isInvincible = true;
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        isInvincible = false;
                    }
                };
                timer.schedule(timerTask, PLAYER_INVINCIBILITY);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (canMove) {
            movePlayer();
        }

        // Debug Bounding Box
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        if (isInvincible) {
            g.setColor(Color.WHITE);
            g.fillRect(x - (radius / 2), y - (radius / 2), (int)(diameter * 1.5), (int)(diameter * 1.5));
        }

        switch (playerID) {
            case 1: g.setColor(PLAYER1_COLOUR);
                g.drawString(("Bomb " + bomb), TILE_DIAMETER, 15);
                g.drawString(("Fire " + fire), TILE_DIAMETER * 5, 15);
                g.drawString(("Skate " + skate), TILE_DIAMETER, 30);
                g.drawString(("Heart " + heart), TILE_DIAMETER * 5, 30);
                break;
            case 2: g.setColor(PLAYER2_COLOUR);
                g.drawString(("Bomb " + bomb), TILE_DIAMETER * 10, 15);
                g.drawString(("Fire " + fire), FRAME_WIDTH, 15);
                g.drawString(("Skate " + skate), TILE_DIAMETER * 10, 30);
                g.drawString(("Heart " + heart), FRAME_WIDTH, 30);
                break;
            case 3: g.setColor(PLAYER3_COLOUR);
                g.drawString(("Bomb " + bomb), TILE_DIAMETER, FRAME_HEIGHT + 15);
                g.drawString(("Fire " + fire), TILE_DIAMETER * 5, FRAME_HEIGHT + 15);
                g.drawString(("Skate " + skate), TILE_DIAMETER, FRAME_HEIGHT + 30);
                g.drawString(("Heart " + heart), TILE_DIAMETER * 5, FRAME_HEIGHT + 30);
                break;
            case 4: g.setColor(PLAYER4_COLOUR);
                g.drawString(("Bomb " + bomb), TILE_DIAMETER * 10, FRAME_HEIGHT + 15);
                g.drawString(("Fire " + fire), FRAME_WIDTH, FRAME_HEIGHT + 15);
                g.drawString(("Skate " + skate), TILE_DIAMETER * 10, FRAME_HEIGHT + 30);
                g.drawString(("Heart " + heart), FRAME_WIDTH, FRAME_HEIGHT + 30);
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
                Bomb.spawnBomb(x + radius, y + radius, fire);
            }
            canDropBomb = false;
        }
    }

    public void heartUp() {
        if (heart < HEART_MAX) {
            heart++;
        }
    }

    public void bombDown() {
        if (bomb > MIN) {
            bomb--;
        }
    }

    public void bombUp() {
        if (bomb < MAX) {
            bomb++;
        }
    }

    public void fireDown() {
        if (fire > MIN) {
            fire--;
        }
    }

    public void fireUp() {
        if (fire < MAX) {
            fire++;
        }
    }

    public void speedDown() {
        if (skate > MIN) {
            skate--;
        }
    }

    public void speedUp() {
        if (skate < MAX) {
            skate++;
        }
    }

    private void setSpeed() {
        // IF skate 4 or higher (speed +30)
        // IF skate 3 or lower (speed -90)
    }

    public void debugGiveAll() {
        heart = HEART_MAX;
        bomb = MAX * 4;
        fire = MAX;
        skate = MAX;
    }
}
