package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.Constants.FRAME_HEIGHT;
import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

public class Player extends GameObject {
    private static final Color PLAYER1_COLOUR = Color.BLUE;
    private static final Color PLAYER2_COLOUR = Color.RED;
    private static final Color PLAYER3_COLOUR = Color.MAGENTA;
    private static final Color PLAYER4_COLOUR = Color.YELLOW; // GREEN (TileObject is currently using this)
    private static final int PLAYER_INVINCIBILITY_FIRE = 1000;
    private static final int PLAYER_INVINCIBILITY_SPAWN = 2500; // Unity implementation for returning from revenge cart
    private static final int MIN = 1;
    private static final int MAX = 8;

    private final int playerID;

    private int heart = 1;
    private int bomb = 1;
    private int fire = 2;
    private int skate = 4;
    private int speed;

    private boolean moveLeft, moveRight, moveUp, moveDown;
    private boolean canDropBomb;
    private boolean hasSingleSpecialBomb, hasMultipleSpecialBomb;

    private enum SpecialBomb { UNAVAILABLE, POWERBOMB, SPIKEBOMB, DANGEROUSBOMB, REMOTEBOMB }
    private SpecialBomb specialBomb;

    public Player(int x, int y, int playerID) {
        super(x, y);
        this.x = (x * diameter) + (diameter * 2) + (radius / 2);
        this.y = (y * diameter) + (diameter * 2) + (radius / 2);
        this.radius *= 0.5;
        this.diameter = this.radius * 2;
        this.playerID = playerID;
        this.specialBomb = SpecialBomb.UNAVAILABLE;
        canDropBomb = true;
    }

    @Override
    public void initialise() {
        super.initialise();
        setSpeed();
    }

    @Override
    public boolean isNotInitialised() {
        return super.isNotInitialised();
    }

    @Override
    public Rectangle getBounds() {
        if (moveLeft) {
            return new Rectangle(x - speed, y, radius * 2, radius * 2);

        } else if (moveRight) {
            return new Rectangle(x + speed, y, radius * 2, radius * 2);

        } else if (moveUp) {
            return new Rectangle(x, y - speed, radius * 2, radius * 2);

        } else if (moveDown) {
            return new Rectangle(x, y + speed, radius * 2, radius * 2);
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

        if (other.getClass() == BlockHard.class || other.getClass() == BlockSoft.class) {
            cancelCollisionMovement(other);
        }

        if (other instanceof BombObject) {
            if (((BombObject)other).getIsCollisionActive()) {
                cancelCollisionMovement(other);
            }
        }
    }

    @Override
    public void hit() {
        if (!isHit) {
            heart--;
            if (heart == 0) {
                GameMain.decrementNumberOfPlayers();
                System.out.println("Player " + playerID + " died.");
                super.hit();
            } else {
                isHit = true;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(PLAYER_INVINCIBILITY_FIRE);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        isHit = false;
                    }
                });
                thread.start();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        movePlayer();

        // Debug Bounding Box
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        if (isHit) {
            g.setColor(Color.WHITE);
            g.fillRect(x - (radius / 2), y - (radius / 2), (int)(diameter * 1.5), (int)(diameter * 1.5));
        }

        switch (playerID) {
            case 1: g.setColor(PLAYER1_COLOUR);
                g.drawString("Bomb " + bomb, TILE_DIAMETER, 15);
                g.drawString("Fire " + fire, TILE_DIAMETER * 4, 15);
                g.drawString("Skate " + skate, TILE_DIAMETER * 7, 15);
                g.drawString("Heart " + heart, TILE_DIAMETER * 2, 30);
                g.drawString(String.valueOf(specialBomb), TILE_DIAMETER * 5, 30);
                break;
            case 2: g.setColor(PLAYER2_COLOUR);
                g.drawString("Bomb " + bomb, TILE_DIAMETER * 10, 15);
                g.drawString("Fire " + fire, TILE_DIAMETER * 13, 15);
                g.drawString("Skate " + skate, TILE_DIAMETER * 16, 15);
                g.drawString("Heart " + heart, TILE_DIAMETER * 11, 30);
                g.drawString(String.valueOf(specialBomb), TILE_DIAMETER * 14, 30);
                break;
            case 3: g.setColor(PLAYER3_COLOUR);
                g.drawString("Bomb " + bomb, TILE_DIAMETER, FRAME_HEIGHT + 15);
                g.drawString("Fire " + fire, TILE_DIAMETER * 4, FRAME_HEIGHT + 15);
                g.drawString("Skate " + skate, TILE_DIAMETER * 7, FRAME_HEIGHT + 15);
                g.drawString("Heart " + heart, TILE_DIAMETER * 2, FRAME_HEIGHT + 30);
                g.drawString(String.valueOf(specialBomb), TILE_DIAMETER * 5, FRAME_HEIGHT + 30);
                break;
            case 4: g.setColor(PLAYER4_COLOUR);
                g.drawString("Bomb " + bomb, TILE_DIAMETER * 10, FRAME_HEIGHT + 15);
                g.drawString("Fire " + fire, TILE_DIAMETER * 13, FRAME_HEIGHT + 15);
                g.drawString("Skate " + skate, TILE_DIAMETER * 16, FRAME_HEIGHT + 15);
                g.drawString("Heart " + heart, TILE_DIAMETER * 11, FRAME_HEIGHT + 30);
                g.drawString(String.valueOf(specialBomb), TILE_DIAMETER * 14, FRAME_HEIGHT + 30);
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

        } else if (moveRight) {
            x += speed;

        } else if (moveUp) {
            y -= speed;

        } else if (moveDown) {
            y += speed;
        }
    }

    private void cancelCollisionMovement(GameObject other) {
        /*
        * Unity Implementation
        * IF player is certain position
        * Correct their position and move them to prevent unnecessary blocking issues
        * Prevents image overlapping
        */

        if (moveLeft) {
            moveLeft = false;
            x = other.getBounds().x + other.getBounds().width;

        } else if (moveRight) {
            moveRight = false;
            x = other.getBounds().x - diameter;

        } else if (moveUp) {
            moveUp = false;
            y = other.getBounds().y + other.getBounds().height;

        } else if (moveDown) {
            moveDown = false;
            y = other.getBounds().y - diameter;
        }
    }

    public void setCanDropBomb() {
        if (!isHit) {
            canDropBomb = true;
        }
    }

    public void dropBomb() {
        if (canDropBomb) {
            List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
            int droppedBombs = 0;
            boolean hasNotDroppedSingleBomb = true;

            if (hasSingleSpecialBomb) {
                for (GameObject object : listObject) {
                    switch (specialBomb) {
                        case POWERBOMB:
                            if (object.getClass() == PowerBomb.class && ((BombObject)object).getBombID() == playerID) {
                                droppedBombs++;
                                break;
                            }
                        case DANGEROUSBOMB:
                            if (object.getClass() == DangerousBomb.class && ((BombObject)object).getBombID() == playerID) {
                                droppedBombs++;
                                break;
                            }
                    }
                }

                if (droppedBombs < 1) {
                    switch (specialBomb) {
                        case POWERBOMB:
                            GameMain.addAliveGameObject(new PowerBomb(x + radius, y + radius, fire, playerID));
                            break;
                        case DANGEROUSBOMB:
                            GameMain.addAliveGameObject(new DangerousBomb(x + radius, y + radius, fire, playerID));
                            break;
                    }
                    hasNotDroppedSingleBomb = false;
                }
            }

            for (GameObject object : listObject) {
                if (hasMultipleSpecialBomb) {
                    if (object.getClass() == SpikeBomb.class || object.getClass() == RemoteBomb.class) {
                        if (((BombObject)object).getBombID() == playerID) {
                            droppedBombs++;
                        }
                    }
                } else if (object.getClass() == Bomb.class) {
                    if (((BombObject)object).getBombID() == playerID) {
                        droppedBombs++;
                    }
                }
            }

            if (droppedBombs < bomb) {
                if (hasMultipleSpecialBomb) {
                    switch (specialBomb) {
                        case SPIKEBOMB:
                            GameMain.addAliveGameObject(new SpikeBomb(x + radius, y + radius, fire, playerID));
                            break;
                        case REMOTEBOMB:
                            GameMain.addAliveGameObject(new RemoteBomb(x + radius, y + radius, fire, playerID));
                            break;
                    }
                } else if (hasNotDroppedSingleBomb) {
                    GameMain.addAliveGameObject(new Bomb(x + radius, y + radius, fire, playerID));
                }
            }
            canDropBomb = false;
        }
    }

    public void heartUp() {
        if (heart < MAX) {
            heart++;
        }
    }

    public void debugHeartDown() {
        heart--;
    }

    public void bombUp() {
        if (bomb < MAX) {
            bomb++;
        }
    }

    public void bombDown() {
        if (bomb > MIN) {
            bomb--;
        }
    }

    public void fireUp() {
        if (fire < MAX) {
            fire++;
        }
    }

    public void fireDown() {
        if (fire > MIN) {
            fire--;
        }
    }

    public void fullFire() {
        fire = MAX;
    }

    public void speedUp() {
        if (skate < MAX) {
            skate++;
            setSpeed();
        }
    }

    public void speedDown() {
        if (skate > MIN) {
            skate--;
            setSpeed();
        }
    }

    private void setSpeed() {
        if (skate <= 3) {
            speed = 1 * (TILE_RADIUS / 10);
        } else if (skate <= 6) {
            speed = 2 * (TILE_RADIUS / 10);
        } else {
            speed = 3 * (TILE_RADIUS / 10);
        }
    }

    public void powerBomb() {
        specialBomb = SpecialBomb.POWERBOMB;
        setHasSingleSpecialBomb();
    }

    public void spikeBomb() {
        specialBomb = SpecialBomb.SPIKEBOMB;
        setHasMultipleSpecialBomb();
    }

    public void dangerousBomb() {
        specialBomb = SpecialBomb.DANGEROUSBOMB;
        setHasSingleSpecialBomb();
    }

    public void remoteBomb() {
        specialBomb = SpecialBomb.REMOTEBOMB;
        setHasMultipleSpecialBomb();
    }

    public void detonateRemoteBomb() {
        List<GameObject> listObject = new ArrayList<>(GameMain.getListObjects());
        Collections.reverse(listObject);
        for (GameObject object : listObject) {
            if (object.getClass() == RemoteBomb.class && ((RemoteBomb)object).isNotHit()) {
                if (((BombObject)object).getBombID() == playerID) {
                        ((RemoteBomb)object).detonate();
                        break;
                }
            }
        }
    }

    private void setHasSingleSpecialBomb() {
        hasSingleSpecialBomb = true;
        hasMultipleSpecialBomb = false;
    }

    private void setHasMultipleSpecialBomb() {
        hasSingleSpecialBomb = false;
        hasMultipleSpecialBomb = true;
    }

    public void debugReset() {
        heart = 1;
        bomb = 1;
        fire = 2;
        skate = 4;
        setSpeed();
        specialBomb = SpecialBomb.UNAVAILABLE;
        hasSingleSpecialBomb = false;
        hasMultipleSpecialBomb = false;
    }

    public void debugGiveAll() {
        heart = MAX;
        bomb = MAX;
        fire = MAX;
        skate = MAX;
        setSpeed();
    }
}
