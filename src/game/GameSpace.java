package game;

import java.util.Random;

import static game.Constants.GAMESPACE_COLUMN;
import static game.Constants.GAMESPACE_ROW;

public class GameSpace {
    private static final Random RNG = new Random();

    public void spawnBorder() {
        for (int row = 0; row < GAMESPACE_ROW; row++) {
            for (int column = 0; column < GAMESPACE_COLUMN; column++) {
                if (row == 0 || column == 0 || row == 14 || column == 12) {
                    GameMain.addBlockGameObject(new BlockHard(row, column));
                }
            }
        }
    }

    public void spawnGameSpace() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                GameMain.addTileGameObject(new BlockTile(row, column));
                if (row % 2 == 0 && column % 2 == 0) {
                    GameMain.addBlockGameObject(new BlockHard(row, column));
                }
            }
        }
    }


    // Debug code to isolate BlockTile spawns
    public void spawnBlockTiles() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                GameMain.addTileGameObject(new BlockTile(row, column));
            }
        }
    }

    // Debug code to isolate BlockHard spawns
    public void spawnBlockHards() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (row % 2 == 0 && column % 2 == 0) {
                    GameMain.addBlockGameObject(new BlockHard(row, column));
                }
            }
        }
    }

    // Debug code to isolate BlockSoft spawns
    public void spawnBlockSofts() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {

                // BlockSoft Row 01
                if (column == 1 || column == 11) {
                    if (row >= 4 && row <= 10) {
                        if (canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }

                // BlockSoft Row 02
                if (column == 2 || column == 10) {
                    if (row != 1 && row != 13 && row % 2 == 1) {
                        if (canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }

                // BlockSoft Row 03
                if (column == 3 || column == 9) {
                    if (row >= 2 && row <= 12) {
                        if (canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }

                // BlockSoft Row 04
                if (column % 2 == 0 && column != 2 && column != 10) {
                    if (row % 2 == 1) {
                        if (canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }

                // BlockSoft Row 05
                if (column == 5 || column == 7) {
                    if (canSpawnBlockSoft()) {
                        GameMain.addBlockGameObject(new BlockSoft(row, column));
                    }
                }
            }
        }
    }

    // Debug code to isolate and check BlockSoft row spawns
    public void spawnRows01() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column == 1 || column == 11) {
                    if (row >= 4 && row <= 10) {
                        GameMain.addBlockGameObject(new BlockSoft(row, column));
                    }
                }
            }
        }
    }

    public void spawnRows02() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column == 2 || column == 10) {
                    if (row != 1 && row != 13 && row % 2 == 1) {
                        GameMain.addBlockGameObject(new BlockSoft(row, column));
                    }
                }
            }
        }
    }

    public void spawnRows03() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column == 3 || column == 9) {
                    if (row >= 2 && row <= 12) {
                        GameMain.addBlockGameObject(new BlockSoft(row, column));
                    }
                }
            }
        }
    }

    public void spawnRows04() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column % 2 == 0 && column != 2 && column != 10) {
                    if (row % 2 == 1) {
                        GameMain.addBlockGameObject(new BlockSoft(row, column));
                    }
                }
            }
        }
    }

    public void spawnRows05() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column == 5 || column == 7) {
                    GameMain.addBlockGameObject(new BlockSoft(row, column));
                }
            }
        }
    }

    private boolean canSpawnBlockSoft() {
        return (RNG.nextInt(100) < 66);
    }
}
