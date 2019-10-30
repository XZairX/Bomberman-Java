// Dummy class for developmental testing purposes

package game;

import java.awt.Color;
import java.awt.Graphics2D;

import static game.Constants.TILE_RADIUS;

/*
public class Player1 extends Player {
    private static final Color PLAYER1_COLOUR = Color.BLUE;

    public Player1(int x, int y, double radius) {
        super(x, y, radius);
    }

    public static Player1 spawnPlayer(int x, int y) {
        x *= TILE_RADIUS * 2;
        y *= TILE_RADIUS * 2;
        return new Player1(x + (TILE_RADIUS * 4 + (TILE_RADIUS / 4)),
                y + (TILE_RADIUS * 4 + (TILE_RADIUS / 4)), (int)(TILE_RADIUS * 1.5));
    }

    @Override
    public void debugPowerUps() {
        super.debugPowerUps();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(PLAYER1_COLOUR);
        g.fillOval(x, y, (int)(TILE_RADIUS * 1.5), (int)(TILE_RADIUS * 1.5));
    }
}
*/