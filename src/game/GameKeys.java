package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeys extends KeyAdapter {
    GameMain game;

    public GameKeys(GameMain game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                game.player1.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                game.player1.moveRight();
                break;
            case KeyEvent.VK_UP:
                game.player1.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                game.player1.moveDown();
                break;
            case KeyEvent.VK_SPACE:
                game.player1.dropBomb();
                break;
        }
    }
}
