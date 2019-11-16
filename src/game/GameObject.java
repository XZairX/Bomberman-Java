package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import static game.Constants.TILE_RADIUS;

public abstract class GameObject {
    public int x;
    public int y;
    public double radius;
    public boolean dead = false;

    public GameObject(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void hit() {
        dead = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, (int)TILE_RADIUS * 2, (int)TILE_RADIUS * 2);
    }

    public boolean isColliding(GameObject other) {
        if (this.getBounds().intersects(other.getBounds())) {
            return true;
        }
        return false;
    }

    /*
    // Potentially redundant collision handling code
    public boolean overlap(GameObject other) {
        if (this.x - other.x <= this.radius + other.radius
                || this.y - other.y <= this.radius + other.radius) {
            return true;
        }
        return false;
    }

    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }
    }
    */

    public void update() {}

    public abstract void draw(Graphics2D g);
}
