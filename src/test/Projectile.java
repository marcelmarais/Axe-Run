package test;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Projectile {

    boolean visible;
    int x, y;
    Image img;

    /**
     * Initialises the Projectile with Coordinates
     *
     * @param startX
     * @param startY
     */
    public Projectile (int startX, int startY) {
        visible = true;
        x = startX;
        y = startY;
        ImageIcon newBullet = new ImageIcon ("Images/axe1.png");
        img = newBullet.getImage ();
    }

    /**
     * Bounding rectangle of the projectile object
     *
     * @return Bounds of projecting
     */
    public Rectangle getBounds () {
        return new Rectangle (x, y, 68, 40);
    }

    /**
     * Moves the the bullet across the screen
     */
    public void move () {
        x = x + 2;//2 pixels every 5 millisecs

        if (x > 1920) {
            visible = false;
        }

    }

    /**
     * Checks if the bullet is on the screen
     *
     * @return Whether the bullet is visible or not
     */
    public boolean getVisible () {
        return visible;
    }

    /**
     * X value of Bullet
     *
     * @return X coordinate of bullet
     */
    public int getX () {
        return x;
    }

    /**
     * Y value of Bullet
     *
     * @return Y coordinate of bullet
     */
    public int getY () {
        return y;
    }

    /**
     * Returns the image
     *
     * @return Image of specified projectile
     */
    public Image getImage () {
        return img;
    }
}
