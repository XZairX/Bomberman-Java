package game;

import static game.Constants.GAMESPACE_COLUMN;
import static game.Constants.GAMESPACE_ROW;

public class GameSpace {
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

    public void spawnBlockSofts() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {

                // BlockSoft Row 01
                if (column == 1 || column == 11) {
                    if (row >= 3 && row <= 11) {
                        if (BlockSoft.canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }

                // BlockSoft Row 02
                if (column == 2 || column == 10) {
                    if (row != 1 && row != 13 && row % 2 == 1) {
                        if (BlockSoft.canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }

                // BlockSoft Row 03
                if (column % 2 == 1 && column != 1 && column != 11) {
                    if (BlockSoft.canSpawnBlockSoft()) {
                        GameMain.addBlockGameObject(new BlockSoft(row, column));
                    }
                }

                // BlockSoft Row 04
                if (column % 2 == 0 && column != 2 && column != 10) {
                    if (row % 2 == 1) {
                        if (BlockSoft.canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }
            }
        }
    }

    public void spawnRows01() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column == 1 || column == 11) {
                    if (row >= 3 && row <= 11) {
                        if (BlockSoft.canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
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
                        if (BlockSoft.canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }
            }
        }
    }

    public void spawnRows03() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                if (column % 2 == 1 && column != 1 && column != 11) {
                    if (BlockSoft.canSpawnBlockSoft()) {
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
                        if (BlockSoft.canSpawnBlockSoft()) {
                            GameMain.addBlockGameObject(new BlockSoft(row, column));
                        }
                    }
                }
            }
        }
    }
}
