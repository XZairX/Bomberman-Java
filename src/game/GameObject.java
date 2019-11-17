package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_RADIUS;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected double radius;
    protected boolean dead = false;

    protected GameObject(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    protected void hit() {
        dead = true;
    }

    protected Rectangle getBounds() {
        return new Rectangle(x, y, (int)TILE_RADIUS * 2, (int)TILE_RADIUS * 2);
    }

    protected boolean isColliding(GameObject other) {
        return (this.getBounds().intersects(other.getBounds()));
    }

    protected void collisionHandling(GameObject other) {
        /*if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }*/
    }

    protected void update() {}

    protected abstract void draw(Graphics2D g);
}
