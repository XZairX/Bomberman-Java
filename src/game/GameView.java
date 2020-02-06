package game;

import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import static game.Constants.FRAME_SIZE;

public class GameView extends JComponent {
    private static final Color BACKGROUND_COLOUR = Color.GRAY;

    public GameView() {}

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D)g0;
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject object : GameMain.getListBlockTile()) {
            object.draw(g);
        }

        for (GameObject object : GameMain.getListObjects()) {
            object.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return FRAME_SIZE;
    }
}
