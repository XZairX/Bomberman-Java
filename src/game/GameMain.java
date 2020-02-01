package game;

import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

import static game.Constants.GAME_LOOP_DELAY;
import static game.Constants.GAMESPACE_COLUMN;
import static game.Constants.GAMESPACE_ROW;
import static game.Constants.TILE_RADIUS;

public class GameMain {
    public static List<BlockTile> listBlockTile;
    public List<BlockHard> listBlockHard;
    public List<BlockSoft> listBlockSoft;
    public List<Player> listPlayer;
    public static List<GameObject> listObjects;
    public static List<GameObject> listAlive;
    public Player player1, player2, player3, player4;

    public GameMain() {
        listBlockTile = new ArrayList<>();
        listBlockHard = new ArrayList<>();
        listBlockSoft = new ArrayList<>();
        listPlayer = new ArrayList<>();
        listObjects = new ArrayList<>();
        listAlive = new ArrayList<>();

        player1 = new Player(GAMESPACE_ROW - (GAMESPACE_ROW - 1), GAMESPACE_COLUMN - (GAMESPACE_COLUMN - 1), TILE_RADIUS, 1);
        player2 = new Player (GAMESPACE_ROW - 2, GAMESPACE_COLUMN - 2, TILE_RADIUS, 2);
        player3 = new Player(GAMESPACE_ROW - 2, GAMESPACE_COLUMN - (GAMESPACE_COLUMN - 1), TILE_RADIUS, 3);
        player4 = new Player (GAMESPACE_ROW - (GAMESPACE_ROW - 1), GAMESPACE_COLUMN - 2, TILE_RADIUS, 4);

        listPlayer.add(player1);
        listPlayer.add(player2);
        listPlayer.add(player3);
        listPlayer.add(player4);

        /*
        // Debug initial inventory state
        for (Player player : listPlayer) {
            player.debugPowerUps();
        }
        */
    }

    public static void addGameObject(GameObject object) {
        listAlive.add(object);
    }

    public static void main(String[] args) throws Exception {
        GameMain game = new GameMain();
        GameView view = new GameView(game);
        GameSpace space = new GameSpace(game);
        new JEasyFrame(view, "Bomberman Game").addKeyListener(new GameKeys(game));

        space.spawnBorder();
        //space.spawnGameSpace();
        space.spawnBlockTiles();
        //space.spawnBlockHards();
        //space.spawnBlockSofts();

        // Debug isolated row spawning
        //space.spawnRows01();
        //space.spawnRows02();
        //space.spawnRows03();
        //space.spawnRows04();

        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(GAME_LOOP_DELAY);
        }
    }

    public void update() {
        // Collisions BlockHard
        for (GameObject object: listBlockHard) {
            if (player1.isColliding(object)) {
                player1.collisionHandling(object);
            }
        }

        // Collisions BlockSoft
        for (GameObject object : listBlockSoft) {
            if (player1.isColliding(object)) {
                player1.collisionHandling(object);
            }
        }

        // Collisions GameObjects
        for (GameObject object : listObjects) {
            if (player1.isColliding(object)) {
                player1.collisionHandling(object);
            }

            // Setting active collisions with new bombs that are not being currently being collided with
            if ((!player1.isColliding(object)) && (object.getClass() == Bomb.class)) {
                Bomb bomb = (Bomb)object;
                if (!bomb.getIsCollisionActive()) {
                    bomb.setIsCollisionActive();
                }
            }

            object.update();
            if (!object.dead) {
                listAlive.add(object);
            }
        }

        synchronized (GameMain.class) {
            listObjects.clear();
            listObjects.addAll(listAlive);
            listAlive.clear();
        }
    }
}
