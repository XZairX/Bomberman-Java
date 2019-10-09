package game;

import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

import static game.Constants.DELAY;

public class GameMain {
    public List<GameObject> objects;

    public GameMain() {
        objects = new ArrayList<GameObject>();
        for (int row = 0; row < 13; row++) {
            for (int column = 0; column < 11; column++) {
                objects.add(BlockTile.spawnBlockTile(row, column));
                if (row % 2 == 1 && column % 2 == 1) {
                    objects.add(BlockHard.spawnBlockHard(row, column));
                }

                // BlockSoft Row 01
                if (column == 0 && row >= 2 && row <= 10
                        || column == 10 && row >= 2 && row <= 10) {
                    objects.add(BlockSoft.spawnBlockSoft(row, column));
                }

                // BlockSoft Row 02
                if (column == 1 && row % 2 == 0 && row != 0 && row != 12
                        || column == 9 && row % 2 == 0 && row != 0 && row != 12) {
                    objects.add(BlockSoft.spawnBlockSoft(row, column));
                }

                // BlockSoft Row 03
                if (column % 2 == 0 && column != 0 && column != 10) {
                    objects.add(BlockSoft.spawnBlockSoft(row, column));
                }

                // BlockSoft Row 04
                if (column % 2 == 1 && column != 1 && column != 9) {
                    if (row % 2 == 0) {
                        objects.add(BlockSoft.spawnBlockSoft(row, column));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        GameMain game = new GameMain();
        GameView view = new GameView(game);
        new JEasyFrame(view, "Bomberman Game");

        while (true) {
            game.update();
            view.repaint();
            //Thread.sleep(DELAY);
        }
    }

    public void update() {
        List<GameObject> alive = new ArrayList<>();
        for (GameObject object : objects) {
            object.update();
        }
    }
}
