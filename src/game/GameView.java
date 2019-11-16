package game;

import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class GameView extends JComponent {
    private static final Color BACKGROUND_COLOUR = Color.GRAY;

    private GameMain game;

    public GameView(GameMain game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject object: game.listBlockTile) {
            object.draw(g);
        }

        for (GameObject object: game.listBlockHard) {
            object.draw(g);
        }

        for (GameObject object: game.listBlockSoft) {
            object.draw(g);
        }

        for (GameObject object : game.listObjects) {
            object.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
