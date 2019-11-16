package game;

public class GameSpace {
    private static final int GAMESPACE_ROW = 15;
    private static final int GAMESPACE_COLUMN = 13;

    private GameMain game;

    public GameSpace(GameMain game) {
        this.game = game;
    }

    public void spawnBorder() {
        for (int row = 0; row < GAMESPACE_ROW; row++) {
            for (int column = 0; column < GAMESPACE_COLUMN; column++) {
                if (row == 0 || column == 0 || row == 14 || column == 12) {
                    game.listBlockHard.add(BlockHard.spawnBlockHard(row, column));
                }
            }
        }
    }

    public void spawnTiles() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                game.listBlockTile.add(BlockTile.spawnBlockTile(row, column));
                if (row % 2 == 0 && column % 2 == 0) {
                    game.listBlockHard.add(BlockHard.spawnBlockHard(row, column));
                }
            }
        }
    }

    public void spawnRows() {
        for (int row = 1; row < GAMESPACE_ROW - 1; row++) {
            for (int column = 1; column < GAMESPACE_COLUMN - 1; column++) {
                // BlockSoft 01
                if (column == 1 || column == 11) {
                    if (row >= 3 && row <= 11) {
                        game.listBlockSoft.add(BlockSoft.spawnBlockSoft(row, column));
                    }
                }

                // BlockSoft Row 02
                if (column == 2 || column == 10) {
                    if (row != 1 && row != 13 && row % 2 == 1) {
                        game.listBlockSoft.add(BlockSoft.spawnBlockSoft(row, column));
                    }
                }

                // BlockSoft Row 03
                if (column % 2 == 1 && column != 1 && column != 11) {
                    game.listBlockSoft.add(BlockSoft.spawnBlockSoft(row, column));
                }

                // BlockSoft Row 04
                if (column % 2 == 0 && column != 2 && column != 10) {
                    if (row % 2 == 1) {
                        game.listBlockSoft.add(BlockSoft.spawnBlockSoft(row, column));
                    }
                }
            }
        }
    }
}
