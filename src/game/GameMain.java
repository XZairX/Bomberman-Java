package game;

import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

public class GameMain {
    public List<GameObject> nonRemovableObjects;
    public List<GameObject> objects;

    public GameMain() {
        nonRemovableObjects = new ArrayList<GameObject>();
        objects = new ArrayList<GameObject>();

        // BlockHard Border
        for (int row = 0; row < 15; row++) {
            for (int column = 0; column < 13; column++) {
                if (row == 0 || column == 0 || row == 14 || column == 12) {
                    nonRemovableObjects.add(BlockHard.spawnBlockHard(row + 2, column + 2));
                }
            }
        }

        // BlockTile Game Space
        for (int row = 1; row < 14; row++) {
            for (int column = 1; column < 12; column++) {
                nonRemovableObjects.add(BlockTile.spawnBlockTile(row + 2, column + 2));
                if (row % 2 == 0 && column % 2 == 0) {
                    nonRemovableObjects.add(BlockHard.spawnBlockHard(row + 2, column + 2));
                }

                // BlockSoft Row 01
                if (column == 1 || column == 11) {
                    if (row >= 3 && row <= 11) {
                        objects.add(BlockSoft.spawnBlockSoft(row + 2, column + 2));
                    }
                }

                // BlockSoft Row 02
                if (column == 2 || column == 10) {
                    if (row != 1 && row != 13 && row % 2 == 1) {
                        objects.add(BlockSoft.spawnBlockSoft(row + 2, column + 2));
                    }
                }

                // BlockSoft Row 03
                if (column % 2 == 1 && column != 1 && column != 11) {
                    objects.add(BlockSoft.spawnBlockSoft(row + 2, column + 2));
                }

                // BlockSoft Row 04
                if (column % 2 == 0 && column != 2 && column != 10) {
                    if (row % 2 == 1) {
                        objects.add(BlockSoft.spawnBlockSoft(row + 2, column + 2));
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
