package game;

import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import static game.Constants.FRAME_SIZE;

import static game.GameMain.listBlockTile;
import static game.GameMain.listObjects;

public class GameView extends JComponent {
    private static final Color BACKGROUND_COLOUR = Color.GRAY;

    public GameView() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Temporary fix to stop errors on startup
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        repaint();
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D)g0;
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject object : listBlockTile) {
            object.draw(g);
        }

        for (GameObject object : listObjects) {
            object.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return FRAME_SIZE;
    }
}
