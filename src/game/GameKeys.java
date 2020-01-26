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
