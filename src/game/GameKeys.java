package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeys extends KeyAdapter {
    private GameMain game;

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

            // Debug keys
            case KeyEvent.VK_H:
                game.player1.heartUp();
                break;
            case KeyEvent.VK_J:
                game.player1.hit();
                break;
            case KeyEvent.VK_B:
                game.player1.bombUp();
                break;
            case KeyEvent.VK_N:
                game.player1.bombDown();
                break;
            case KeyEvent.VK_F:
                game.player1.fireUp();
                break;
            case KeyEvent.VK_G:
                game.player1.fireDown();
                break;
            case KeyEvent.VK_S:
                game.player1.speedUp();
                break;
            case KeyEvent.VK_D:
                game.player1.speedDown();
                break;
            case KeyEvent.VK_P:
                game.player1.powerBomb();
                break;
            case KeyEvent.VK_O:
                game.player1.spikeBomb();
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
        }
    }
}
