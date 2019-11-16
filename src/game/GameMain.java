package game;

import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

import static game.Constants.GAMESPACE_COLUMN;
import static game.Constants.GAMESPACE_ROW;
import static game.Constants.TILE_RADIUS;

public class GameMain {
    public List<BlockTile> listBlockTile;
    public List<BlockHard> listBlockHard;
    public List<BlockSoft> listBlockSoft;
    public List<GameObject> listObjects;
    public Player player1, player2, player3, player4;

    public GameMain() {
        listBlockTile = new ArrayList<BlockTile>();
        listBlockHard = new ArrayList<BlockHard>();
        listBlockSoft = new ArrayList<BlockSoft>();
        listObjects = new ArrayList<GameObject>();

        // Position spawn references for players
        player1 = new Player(GAMESPACE_ROW - (GAMESPACE_ROW - 1), GAMESPACE_COLUMN - (GAMESPACE_COLUMN - 1),TILE_RADIUS, 1);
        player2 = new Player (GAMESPACE_ROW - 2, GAMESPACE_COLUMN - 2, TILE_RADIUS, 2);
        player3 = new Player(GAMESPACE_ROW - 2, GAMESPACE_COLUMN - (GAMESPACE_COLUMN - 1), TILE_RADIUS, 3);
        player4 = new Player (GAMESPACE_ROW - (GAMESPACE_ROW - 1), GAMESPACE_COLUMN - 2, TILE_RADIUS, 4);

        listObjects.add(player1);
        listObjects.add(player2);
        listObjects.add(player3);
        listObjects.add(player4);

        /*
        // For after collsion detection
        player1.debugPowerUps();
        player2.debugPowerUps();
        player3.debugPowerUps();
        player4.debugPowerUps();
        */
    }

    public static void main(String[] args) throws Exception {
        GameMain game = new GameMain();
        GameView view = new GameView(game);
        GameSpace space = new GameSpace(game);
        new JEasyFrame(view, "Bomberman Game").addKeyListener(new GameKeys(game));

        space.spawnBorder();
        space.spawnTiles();
        space.spawnRows();

        while (true) {
            game.update();
            view.repaint();
            //Thread.sleep(DELAY);
        }
    }

    public void update() {
        List<GameObject> alive = new ArrayList<>();
        for (GameObject object: listBlockHard)
        {
            if (player1.isColliding(object)) {
                // Insert BlockHard collision handling here
            }
        }

        for (GameObject object : listBlockSoft) {
            if (player1.isColliding(object)) {
                // Insert BlockSoft collision handling here
            }
        }

        for (GameObject object : listObjects) {
            object.update();
        }
    }
}
