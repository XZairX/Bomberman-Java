package game;

import java.awt.Dimension;
import java.awt.Color;

public class Constants {
    public static final int TILE_RADIUS = 10;
    public static final int TILE_DIAMETER = TILE_RADIUS * 2;
    public static final int FRAME_HEIGHT = TILE_DIAMETER * 15;
    public static final int FRAME_WIDTH = TILE_DIAMETER * 15;
    public static final int GAMESPACE_ROW = 15;
    public static final int GAMESPACE_COLUMN = 13;
    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    public static final Color TILE_OUTLINE_COLOUR = Color.BLACK;

    // Sleep time between frames (May not be necessary)
    public static final int DELAY = 20; // milliseconds
    public static final double DT = DELAY / 1000.0; // seconds
}
