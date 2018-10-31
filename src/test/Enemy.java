package test;

import java.awt.*;
import javax.swing.*;

public class Enemy {

    Image enemyImg;
    int x, y;
    boolean isAlive = true;

    /**
     * Initialises an Enemy with relevant values
     *
     * @param startX
     * @param startY
     * @param imgPath
     */
    public Enemy (int startX, int startY, String imgPath) {
        x = startX;
        y = startY;

        ImageIcon upIcon = new ImageIcon (imgPath);
        enemyImg = upIcon.getImage ();
    }

    /**
     *
     * @return Returns the bounding rectangle of the Enemy
     */
    public Rectangle getBounds () {
        return new Rectangle (x, y + 50, 64, 44);
    }

    /**
     * X position of enemy
     *
     * @return X value of Enemy
     */
    public int getX () {
        return x;
    }

    /**
     * Y position of enemy
     *
     * @return Y value of Enemy
     */
    public int getY () {
        return y;
    }

    /**
     * Sets the new X value of the enemy
     *
     * @param xData
     */
    public void setX (int xData) {
        x = xData;
    }

    /**
     * Sets the new Y value of the enemy
     *
     * @param yData
     */
    public void setY (int yData) {
        y = yData;
    }

    /**
     * Checks if enemy is alive
     *
     * @return if the enemy is alive or not
     */
    public boolean Alive () {
        return isAlive;
    }

    /**
     *
     * @return The upwards image
     */
    public Image getImage () {
        return enemyImg;
    }

    /**
     * Ensures the enemy moves towards the player
     *
     * @param dx Delta X (Change in x)
     */
    public void move (int dx) {
        if (dx > 0) {
            x = x - dx;
        }

    }

}
