package game;

import java.awt.Color;
import java.awt.Graphics2D;

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

    public Player(int x, int y, double radius, int playerID) {
        super(x, y, radius);
        this.x *= TILE_RADIUS * 2;
        this.x += (TILE_RADIUS * 4 + (TILE_RADIUS / 4));
        this.y *= TILE_RADIUS * 2;
        this.y += (TILE_RADIUS * 4 + (TILE_RADIUS / 4));
        this.radius += TILE_RADIUS * 1.5;
        this.playerID = playerID;
    }

    /*
    //Method which may be later deleted due to redundancy
    public static Player spawnPlayer(int x, int y, int playerID) {
        x *= TILE_RADIUS * 2;
        y *= TILE_RADIUS * 2;
        return new Player(x + (TILE_RADIUS * 4 + (TILE_RADIUS / 4)),
                y + (TILE_RADIUS * 4 + (TILE_RADIUS / 4)), (int)(TILE_RADIUS * 1.5), playerID);
    }
    */

    public void debugPowerUps() {
        System.out.println(
                "ID: player" + playerID + "\n \t\t" +
                "Heart: " + heart + " \t" +
                "Bomb: " + bomb + " \t" +
                "Fire: " + fire + " \t" +
                "Skate: " + skate);
    }

    public void HeartUp() {
        if (heart < 3) {
            heart++;
        }
    }

    public void BombDown() {
        if (bomb > 1) {
            bomb--;
        }
    }

    public void BombUp() {
        if (bomb < 8) {
            bomb++;
        }
    }

    public void FireDown() {
        if (fire > 1) {
            fire--;
        }
    }

    public void FireUp() {
        if (fire < 8) {
            fire++;
        }
    }

    public void SpeedDown() {
        if (skate > 1) {
            skate--;
        }
    }

    public void SpeedUp() {
        if (skate < 8) {
            skate++;
        }
    }

    @Override
    public void draw(Graphics2D g) {
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
