package utilities;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import static game.Constants.TILE_RADIUS;

public class JEasyFrame extends JFrame {
    private static final Color BACKGROUND_COLOUR = Color.BLACK;

    private Component component;

    public JEasyFrame(Component component, String title) {
        super(title);
        this.component = component;

        JPanel panelNorth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelWest = new JPanel();

        panelNorth.setBackground(BACKGROUND_COLOUR);
        panelEast.setBackground(BACKGROUND_COLOUR);
        panelSouth.setBackground(BACKGROUND_COLOUR);
        panelWest.setBackground(BACKGROUND_COLOUR);

        //getContentPane().add
        add(component, BorderLayout.CENTER);
        add(BorderLayout.NORTH, panelNorth);
        add(BorderLayout.EAST, panelEast);
        add(BorderLayout.SOUTH, panelSouth);
        add(BorderLayout.WEST, panelWest);

        pack();
        setSize(getWidth() + TILE_RADIUS * 8, getHeight() + TILE_RADIUS * 4);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }
}
