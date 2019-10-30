package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeys extends KeyAdapter {
    Player player;

    public GameKeys(Player player) {
        this.player = player;
    }

    public Player player() {
        return player;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                System.out.println("UP");
                player.moveUp();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                player.moveLeft();
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                player.moveDown();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                player.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                // Drop bomb
                break;
        }
    }
}
