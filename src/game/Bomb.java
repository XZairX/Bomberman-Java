package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;
import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class Bomb extends GameObject {
    private static final Color BOMB_COLOUR = Color.BLACK;
    private static final int BOMB_DELAY = 3000;

    // For debugging
    private int secondsToExplode = 3;

    private boolean isRunning = false;

    public Bomb(int x, int y, double radius) {
        super(x, y, radius);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                isRunning = true;
                if (secondsToExplode > 0) {
                    secondsToExplode--;
                } else {
                    for (BlockTile tile : listBlockTile) {
                        if (tile.x == x && tile.y == y) {
                            tile.toggleAvailability();
                            break;
                        }
                    }
                    hit();
                    timer.cancel();
                }
            }
        };
        // Correct version
        //timer.schedule(timerTask, BOMB_DELAY);

        // Debug version
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    public static void spawnBomb(int x, int y) {
        //System.out.println("Px: " + x + ", " + "Py: " + y);
        x = Math.round(x / 20) * 20;
        y = Math.round(y / 20) * 20;
        //System.out.println("Bx: " + x + ", " + "By: " + y);
        //System.out.println();
        for (BlockTile tile : listBlockTile) {
            if (tile.x == x && tile.y == y) {
                if (tile.isAvailable()) {
                    System.out.println("Bomb placed");
                    listObjects.add(new Bomb(x, y, TILE_RADIUS));
                    tile.toggleAvailability();
                } else {
                    System.out.println("Bomb could not be placed");
                }
                break;
            }
        }
    }

    @Override
    public void hit() {
        secondsToExplode = 0;
        isRunning = false;
        dead = true;
        /*
        for (GameObject bomb : listObjects) {
            if (bomb.x == x && bomb.y == y) {
                listObjects.remove(bomb);
                break;
            }
        }
        */
    }

    @Override
    protected void draw(Graphics2D g) {
        /*
        // For viewing the bounding box
        g.setColor(Color.WHITE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        */

        g.setColor(BOMB_COLOUR);
        g.fillOval(x, y, TILE_DIAMETER, TILE_DIAMETER);

        // Debug code
        g.setColor(Color.WHITE);
        if (isRunning) {
            g.drawString(Integer.toString(secondsToExplode + 1), x + TILE_RADIUS, y + TILE_RADIUS);
        }
    }
}
