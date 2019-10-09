package game;

import java.awt.Dimension;

public class Constants {
    public static final int TILE_RADIUS = 10;
    public static final int FRAME_HEIGHT = TILE_RADIUS * 15 * 2;
    public static final int FRAME_WIDTH = TILE_RADIUS * 15 * 2;
    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    // Sleep time between frames
    public static final int DELAY = 20; // milliseconds
    public static final double DT = DELAY / 1000.0; // seconds
}

