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
    public static List<GameObject> listObjects;
    public static List<GameObject> listAlive;
    public static List<Player> listPlayer;
    public Player player1, player2, player3, player4;

    public GameMain() {
        listBlockTile = new ArrayList<>();
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

    public static void addTileGameObject(BlockTile blockTile) {
        listBlockTile.add(blockTile);
    }

    public static void addBlockGameObject(GameObject object) {
        listObjects.add(object);
    }

    public static void addAliveGameObject(GameObject object) {
        listAlive.add(object);
    }

    public static void main(String[] args) throws Exception {
        GameMain gameMain = new GameMain();
        GameView gameView = new GameView();
        GameSpace gameSpace = new GameSpace();
        new JEasyFrame(gameView, "Bomberman Game").addKeyListener(new GameKeys(gameMain));

        gameSpace.spawnBorder();
        //gameSpace.spawnGameSpace();
        gameSpace.spawnBlockTiles();
        //gameSpace.spawnBlockHards();
        //gameSpace.spawnBlockSofts();

        // Debug isolated row spawning
        //gameSpace.spawnRows01();
        //gameSpace.spawnRows02();
        //gameSpace.spawnRows03();
        //gameSpace.spawnRows04();

        while (true) {
            gameMain.update();
            gameView.repaint();
            Thread.sleep(GAME_LOOP_DELAY);
        }
    }

    public void update() {
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

            // Collisions Fire
            if (object.getClass() == Fire.class) {
                for (int i = listObjects.indexOf(this) + 1; i < listObjects.size(); i++) {
                    GameObject other = listObjects.get(i);
                    if (object.isColliding(other)) {
                        object.collisionHandling(other);
                    }
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
