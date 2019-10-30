package utilities;

import game.Constants;

import javax.swing.*;
import java.awt.*;

public class JEasyFrame extends JFrame {
    private static final Color BACKGROUND_COLOUR = Color.BLACK;

    public Component comp;
    public JEasyFrame(Component comp, String title) {
        super(title);
        this.comp = comp;

        JPanel panelNorth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelWest = new JPanel();

        panelNorth.setBackground(BACKGROUND_COLOUR);
        panelEast.setBackground(BACKGROUND_COLOUR);
        panelSouth.setBackground(BACKGROUND_COLOUR);
        panelWest.setBackground(BACKGROUND_COLOUR);

        // Placeholder buttons
        panelNorth.add(new JButton("NORTH")).setFocusable(false);
        panelEast.add(new JButton("EAST")).setFocusable(false);
        panelSouth.add(new JButton("SOUTH")).setFocusable(false);
        panelWest.add(new JButton("WEST")).setFocusable(false);

        //getContentPane().add
        add(comp, BorderLayout.CENTER);
        add(BorderLayout.NORTH, panelNorth);
        add(BorderLayout.EAST, panelEast);
        add(BorderLayout.SOUTH, panelSouth);
        add(BorderLayout.WEST, panelWest);

        pack();
        setSize(getWidth() + Constants.TILE_RADIUS * 8, getHeight() + Constants.TILE_RADIUS * 4);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }
}
