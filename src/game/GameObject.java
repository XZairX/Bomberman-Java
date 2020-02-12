package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;
import static game.Constants.TILE_RADIUS;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int radius;
    protected int diameter;
    protected boolean isDead;

    protected GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.radius = TILE_RADIUS;
        this.diameter = TILE_DIAMETER;
    }

    protected GameObject(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.diameter = radius * 2;
    }

    protected Rectangle getBounds() {
        return new Rectangle(x, y, radius * 2, radius * 2);
    }

    /*protected boolean isOverlapping(GameObject other) {
        return (this.x == other.x && this.y == other.y);
    }*/

    protected boolean isColliding(GameObject other) {
        return (this.getBounds().intersects(other.getBounds()));
    }

    protected void collisionHandling(GameObject other) {
        if (other.getClass() == Fire.class) {
            this.hit();
        }
    }

    protected void hit() {
        isDead = true;
    }

    protected void update() {}

    protected abstract void draw(Graphics2D g);
}
