package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeys extends KeyAdapter {
    GameMain game;

    public GameKeys(GameMain game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                game.player1.setMovement(Player.Movement.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                game.player1.setMovement(Player.Movement.RIGHT);
                break;
            case KeyEvent.VK_UP:
                game.player1.setMovement(Player.Movement.UP);
                break;
            case KeyEvent.VK_DOWN:
                game.player1.setMovement(Player.Movement.DOWN);
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
                game.player1.resetMovement();
                break;
            case KeyEvent.VK_RIGHT:
                game.player1.resetMovement();
                break;
            case KeyEvent.VK_UP:
                game.player1.resetMovement();
                break;
            case KeyEvent.VK_DOWN:
                game.player1.resetMovement();
                break;
            case KeyEvent.VK_SPACE:
                game.player1.canDropBomb();
                break;
        }
    }
}
