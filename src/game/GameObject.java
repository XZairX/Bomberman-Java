package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_DIAMETER;

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

    protected Rectangle getBounds() {
        return new Rectangle(x, y, TILE_DIAMETER, TILE_DIAMETER);
    }

    protected boolean isColliding(GameObject other) {
        return (this.getBounds().intersects(other.getBounds()));
    }

    protected void collisionHandling(GameObject other) {
        // Global collision handling functionality for checking if hit by Fire object
        /*if (this.getClass() != other.getClass() && other.getClass().equals(Fire.class)) {
            this.hit();
        }*/
    }

    protected void hit() {
        dead = true;
    }

    protected void update() {}

    protected abstract void draw(Graphics2D g);
}
