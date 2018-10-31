package test;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Character {

    static boolean isProjectileTossed;
    boolean dead;
    int x, checkX;

    int hx, hy;

    static int dx;
    static int health;

    double scoreCoefficient;
    int y, dy;
    int height, ammo, counter;
    int nx2, nx, platX;
    int left;
    static int speed = 0;
    public static double Score;
    static Image still;
    Animations walking;

    ImageIcon imgNotMovingRight = new ImageIcon ("Images/Standing.png");
    ImageIcon imgNotMoving = new ImageIcon ("Images/Front.png");
    ImageIcon imgWalkLeft = new ImageIcon ("Images/walkingLeft.png");

    static ArrayList projectiles;

    /**
     * Male Character Attributes
     */
    public Character () {
        walking = new Animations ();
        projectiles = new ArrayList ();
        still = imgNotMovingRight.getImage ();

        isProjectileTossed = false;
        dead = false;

        ammo = 10;
        health = 10;

        left = 75;
        x = 75;
        y = 600;
        platX = 0;

        nx = 0;
        nx2 = 1080;

    }

    /**
     * Projectiles for character
     *
     * @return Projectiles
     */
    public static ArrayList getProjectiles () {
        return projectiles;
    }

    /**
     * Bounding rectangle for character
     *
     * @return rectangle bounds
     */
    public Rectangle getBounds () {
        return new Rectangle (left, CoreMechanics.characterY, 100, 100);
    }

    /**
     * Allows the character to fire a projectile
     */
    public void fire (int yProjectile) {
        if (ammo > 0) {
            if (dy > 0) {
                height = dy;
            }

            Projectile hammer = new Projectile (left + 128, (yProjectile));
            projectiles.add (hammer);
            ammo--;
        }

    }

    /**
     * Controls movement of character
     */
    public void move () {
        if (x % 600 == 0 && ammo < 10)//Recharges ammo
        {
            ammo++;
        }

        if (dx != -1) {
            if (left + dx <= 75)//Allows the charcter to move to the left without moving the screen
            {
                left = left + dx;
            } else {
                x = x + dx;
                nx2 = nx2 + dx;
                nx = nx + dx;
                platX = platX + dx;
            }
        }

        if (dx != -1) {
            hx = hx + dx + 1;
        } else {
            if (left + dx > 0) {
                left = left + dx;
            }
        }

        if (dx > 0) {

            if (x > checkX)//Ensures the player has to move further than their furthest position to increase score
            {
                scoreCoefficient = dx;
                Score = Score + (0.01 * scoreCoefficient);
                //System.out.println(x));
            }
        }

    }

    /**
     * X coordinate of character (Absolute)
     *
     * @return X coordinate
     */
    public int getX () {
        return x;
    }

    /**
     * Y coordinate of character (Absolute)
     *
     * @return Y coordinate
     */
    public int getY () {
        return y;
    }

    /**
     * The change in x (delta x)
     *
     * @return change in x (either 1,0 or -1)
     */
    public int getDx () {
        return dx;
    }

    /**
     * Image of character
     *
     * @return Character image
     */
    public Image getImage () {
        return still;
    }

    public static void setSpeed (int speedData) {
        speed = speedData;
    }

    /**
     * Health of character
     *
     * @return Character health
     */
    public static int getHealth () {
        return health;
    }

    public static void setHealth (int healthData) {
        health = healthData;
    }

    /**
     * Checks which keys are pressed down
     *
     * @param e
     */
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode ();

        if (key == KeyEvent.VK_E) {
            try {

                fileReader.writeScore (LoginGUI.getIndex (), 0);

            } catch (Exception z) {

                System.out.println ("Index not found");

            }
            System.exit (0);
        }

        if (key == KeyEvent.VK_SPACE) {
            if (ammo > 0) {
                MusicPlayer.playMusic ("Music/AxeThrow.wav");
            }
            fire (CoreMechanics.characterY);
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = (-1);
            still = imgWalkLeft.getImage ();

        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            walking.run ();
        }

        if (key == KeyEvent.VK_UP) {
            dy = 1;
            y = y + dy;
            still = imgNotMoving.getImage ();

        }
    }

    /**
     * Checks when a key has been released
     *
     * @param e
     */
    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode ();

        if (key == KeyEvent.VK_LEFT);
        {

            still = imgNotMoving.getImage ();
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT);
        {
            still = imgNotMoving.getImage ();
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            still = imgNotMoving.getImage ();
        }

    }

}
