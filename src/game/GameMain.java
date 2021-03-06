package game;

import java.util.ArrayList;
import java.util.List;

import static game.Constants.GAMESPACE_COLUMN;
import static game.Constants.GAMESPACE_ROW;

public class GameMain {
    private static final int FRAMES_PER_SECOND = 60;
    private static final int GAME_STARTUP_DELAY = 200;
    private static final int GAME_LOOP_DELAY = 1000 / FRAMES_PER_SECOND;

    public static List<TileObject> listTileObject;
    public static List<GameObject> listObjects;
    public static List<GameObject> listAlive;
    public static int numberOfPlayers;
    public Player player1, player2, player3, player4;

    public GameMain() {
        listTileObject = new ArrayList<>();
        listObjects = new ArrayList<>();
        listAlive = new ArrayList<>();

        player1 = new Player(GAMESPACE_ROW - (GAMESPACE_ROW - 1), GAMESPACE_COLUMN - (GAMESPACE_COLUMN - 1), 1);
        player2 = new Player (GAMESPACE_ROW - 2, GAMESPACE_COLUMN - 2, 2);
        player3 = new Player(GAMESPACE_ROW - 2, GAMESPACE_COLUMN - (GAMESPACE_COLUMN - 1), 3);
        player4 = new Player (GAMESPACE_ROW - (GAMESPACE_ROW - 1), GAMESPACE_COLUMN - 2, 4);

        addAliveGameObject(player1);
        addAliveGameObject(player2);
        addAliveGameObject(player3);
        addAliveGameObject(player4);

        numberOfPlayers = listAlive.size();
    }

    public static List<TileObject> getListTileObject() {
        return listTileObject;
    }

    public static List<GameObject> getListObjects() {
        return listObjects;
    }

    public static void addTileGameObject(TileObject tileObject) {
        listTileObject.add(tileObject);
    }

    public static void addBlockGameObject(GameObject object) {
        listObjects.add(object);
    }

    public static void addAliveGameObject(GameObject object) {
        listAlive.add(object);
    }

    public static void decrementNumberOfPlayers() {
        numberOfPlayers--;
    }

    public static void main(String[] args) {
        GameMain gameMain = new GameMain();
        GameView gameView = new GameView();
        GameFrame gameFrame = new GameFrame(gameView, "Bomberman Game");
        GameKeys gameKeys = new GameKeys(gameMain);
        gameFrame.addKeyListener(gameKeys);
        GameSpace gameSpace = new GameSpace();

        gameSpace.spawnBorder();
        //gameSpace.spawnGameSpace();
        gameSpace.spawnBlockTiles();
        gameSpace.spawnBlockHards();
        gameSpace.spawnBlockSofts();

        // Debug isolated row spawning
        //gameSpace.spawnRows01();
        //gameSpace.spawnRows02();
        //gameSpace.spawnRows03();
        //gameSpace.spawnRows04();
        //gameSpace.spawnRows05();

        try {
            Thread.sleep(GAME_STARTUP_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            gameMain.update();
            gameView.repaint();

            try {
                Thread.sleep(GAME_LOOP_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // Collisions GameObjects
        for (GameObject object : listObjects) {
            if (object.isNotInitialised()) {
                object.initialise();
            }

            // Collisions Player
            if (object.getClass() == Player.class) {
                for (int i = listObjects.indexOf(this) + 1; i < listObjects.size(); i++) {
                    GameObject other = listObjects.get(i);
                    if (object.isColliding(other)) {
                        object.collisionHandling(other);
                    }
                }
            }

            // Collisions BombObject
            if (object instanceof BombObject) {
                int playersNotColliding = 0;
                for (int i = listObjects.indexOf(this) + 1; i < listObjects.size(); i++) {
                    GameObject other = listObjects.get(i);

                    if (!object.isColliding(other) && other.getClass() == Player.class) {
                        playersNotColliding++;
                    }

                    // Setting active collisions with bombs that are not being currently being collided with
                    if (playersNotColliding == numberOfPlayers) {
                        if (!((BombObject)object).getIsCollisionActive()) {
                            ((BombObject)object).setIsCollisionActive();
                        }
                    }
                }
            }

            // Collisions FireObject
            if (object instanceof FireObject) {
                for (int i = listObjects.indexOf(this) + 1; i < listObjects.size(); i++) {
                    GameObject other = listObjects.get(i);
                    if (object.isColliding(other)) {
                        object.collisionHandling(other);
                    }
                }
            }

            // Collisions BlockItem
            if (object.getClass() == BlockItem.class) {
                for (int i = listObjects.indexOf(this) + 1; i < listObjects.size(); i++) {
                    GameObject other = listObjects.get(i);
                    if (object.isColliding(other)) {
                        object.collisionHandling(other);
                    }
                }
            }

            object.update();
            if (!object.isDead) {
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
