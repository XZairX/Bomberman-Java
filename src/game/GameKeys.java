package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeys extends KeyAdapter {
    private GameMain game;

    // To assist with debugging
    private enum Special { BOMB, POWER, SPIKE, DANGEROUS, REMOTE }
    private Special special = Special.BOMB;

    public GameKeys(GameMain game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                game.player1.setMoveLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                game.player1.setMoveRight(true);
                break;
            case KeyEvent.VK_UP:
                game.player1.setMoveUp(true);
                break;
            case KeyEvent.VK_DOWN:
                game.player1.setMoveDown(true);
                break;
            case KeyEvent.VK_SPACE:
                game.player1.dropBomb();
                break;

            case KeyEvent.VK_A:
                game.player2.setMoveLeft(true);
                break;
            case KeyEvent.VK_D:
                game.player2.setMoveRight(true);
                break;
            case KeyEvent.VK_W:
                game.player2.setMoveUp(true);
                break;
            case KeyEvent.VK_S:
                game.player2.setMoveDown(true);
                break;
            case KeyEvent.VK_L:
                game.player2.dropBomb();
                break;

            // Debug keys
            case KeyEvent.VK_1:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        object.hit();
                    }
                }
                break;

            case KeyEvent.VK_2:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).heartUp();
                    }
                }
                break;

            case KeyEvent.VK_3:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).debugHeartDown();
                    }
                }
                break;

            case KeyEvent.VK_4:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).bombUp();
                    }
                }
                break;

            case KeyEvent.VK_5:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).bombDown();
                    }
                }
                break;

            case KeyEvent.VK_6:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).fireUp();
                    }
                }
                break;

            case KeyEvent.VK_7:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).fireDown();
                    }
                }
                break;

            case KeyEvent.VK_8:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).speedUp();
                    }
                }
                break;

            case KeyEvent.VK_9:
                for (GameObject object : GameMain.getListObjects()) {
                    if (object.getClass() == Player.class) {
                        ((Player)object).speedDown();
                    }
                }
                break;

            case KeyEvent.VK_0:
                switch (special) {
                    case BOMB:
                        special = Special.POWER;
                        for (GameObject object : GameMain.getListObjects()) {
                            if (object.getClass() == Player.class) {
                                ((Player)object).powerBomb();
                            }
                        }
                        break;
                    case POWER:
                        special = Special.SPIKE;
                        for (GameObject object : GameMain.getListObjects()) {
                            if (object.getClass() == Player.class) {
                                ((Player)object).spikeBomb();
                            }
                        }
                        break;
                    case SPIKE:
                        special = Special.DANGEROUS;
                        for (GameObject object : GameMain.getListObjects()) {
                            if (object.getClass() == Player.class) {
                                ((Player)object).dangerousBomb();
                            }
                        }
                        break;
                    case DANGEROUS:
                        special = Special.REMOTE;
                        for (GameObject object : GameMain.getListObjects()) {
                            if (object.getClass() == Player.class) {
                                ((Player)object).remoteBomb();
                            }
                        }
                        break;
                    case REMOTE:
                        special = Special.POWER;
                        for (GameObject object : GameMain.getListObjects()) {
                            if (object.getClass() == Player.class) {
                                ((Player)object).powerBomb();
                            }
                        }
                        break;
                }
                break;

            case KeyEvent.VK_X:
                game.player1.detonateRemoteBomb();
                break;
            case KeyEvent.VK_ESCAPE:
                game.player1.debugGiveAll();
                break;
            case KeyEvent.VK_BACK_SPACE:
                game.player1.debugReset();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                game.player1.setMoveLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                game.player1.setMoveRight(false);
                break;
            case KeyEvent.VK_UP:
                game.player1.setMoveUp(false);
                break;
            case KeyEvent.VK_DOWN:
                game.player1.setMoveDown(false);
                break;
            case KeyEvent.VK_SPACE:
                game.player1.canDropBomb();
                break;

            case KeyEvent.VK_A:
                game.player2.setMoveLeft(false);
                break;
            case KeyEvent.VK_D:
                game.player2.setMoveRight(false);
                break;
            case KeyEvent.VK_W:
                game.player2.setMoveUp(false);
                break;
            case KeyEvent.VK_S:
                game.player2.setMoveDown(false);
                break;
            case KeyEvent.VK_L:
                game.player2.canDropBomb();
                break;
        }
    }
}
