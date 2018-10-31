package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CoreMechanics extends JPanel implements ActionListener, Runnable {

    GameFrame gameFrame = new GameFrame ();//Frame for all content

    //Platform Attributes
    int heightOfPlatform = 500;
    int firstPlatfromPosition = (int) Math.random () * 50;

    //Enemy Attributes
    int enemyCounter = 0;
    int enemySpeed = 3;

    Enemy en[] = new Enemy[1000];
    int characterTouchedByEnemyCnt = 0;
    boolean charcterTouchedEnemy = false;

    //Character Attributes
    public static Character mainCharacter = new Character ();
    public static int characterY = mainCharacter.getY ();
    int count = 0;
    int healthOfCharacter = 10;

    //Jumping Attributes
    boolean isJumping = false;

    //Visual Attributes
    static Font font = new Font ("Luminari", Font.BOLD, 40);

    ImageIcon backgroundImg = new ImageIcon ("Images/gameBackGround.png");
    ImageIcon platform = new ImageIcon ("/Images/src/Platform.png");
    ImageIcon imgProjectile = new ImageIcon ("Images/heroine_set.png");

    Image imgScrollingBackground = backgroundImg.getImage (), imgPlatform = platform.getImage ();

    int assetFrequency = 1;//Alows assets to appear more often if increased

    //Animation Attributes
    Thread animator;
    Timer refreshTime;

    /**
     * Instantiates all attributes that are going to be used by the board class
     */
    public CoreMechanics () {

        addKeyListener (new AL ());//Checks for key presses
        setFocusable (true);

        //Creates new enemy with jumpPeak random y-location
        en[enemyCounter] = new Enemy ((500), 350 + (int) Math.random () * 100, "Images/BatUp.png");

        //Determines how often assets are refreshed
        refreshTime = new Timer (1, this);
        refreshTime.start ();

    }

    /**
     * Checks for collisions of Enemies, projectiles and the character
     */
    public void checkCollisions () {
        Rectangle r1 = en[enemyCounter].getBounds ();

        ArrayList proj = mainCharacter.getProjectiles ();
        for (int w = 0; w < proj.size (); w++) {
            Projectile fired = (Projectile) proj.get (w);
            Rectangle m1 = fired.getBounds ();
            if (r1.intersects (m1) && en[enemyCounter].Alive () && mainCharacter.x > 600) {
                MusicPlayer.playMusic ("Music/AxeHit.wav");
                en[enemyCounter].isAlive = false;
                fired.visible = false;
                mainCharacter.Score += 50;
            }

        }
        Rectangle mc = mainCharacter.getBounds ();

        if (mc.intersects (r1) && en[enemyCounter].isAlive == true && charcterTouchedEnemy == false && mainCharacter.x > 700) {
            mainCharacter.health = mainCharacter.health - 1;
            charcterTouchedEnemy = true;
            MusicPlayer.playMusic ("Music/Damage.wav");
        }
    }

    /**
     * Infinitely looping event that allows projectiles to be run on backgroundImg thread
     * and checks collisions
     *
     * @param e
     */
    public void actionPerformed (ActionEvent e) {
        if (healthOfCharacter == 0) {
            healthOfCharacter--;
            try {
                Main.showScores ();
            } catch (Exception x) {
                System.out.println ("ERROR: Scores cannot be shown");
                x.printStackTrace ();
            }
        }
        if (charcterTouchedEnemy == true) {

            characterTouchedByEnemyCnt++;

            if (characterTouchedByEnemyCnt >= 55) {
                charcterTouchedEnemy = false;
                characterTouchedByEnemyCnt = 0;
            }

        }
        en[enemyCounter].setX (en[enemyCounter].getX () - enemySpeed);
        checkCollisions ();

        ArrayList proj = mainCharacter.getProjectiles ();
        for (int i = 0; i < proj.size (); i++) {
            Projectile fired = (Projectile) proj.get (i);

            if (fired.getVisible () == true) {
                fired.move ();
            } else {
                proj.remove (i);
            }
        }

        mainCharacter.move ();

        if (mainCharacter.x > (900 * (enemyCounter))) {
            en[enemyCounter].move (mainCharacter.getDx ());
        }
        repaint ();
    }

    public class AL extends KeyAdapter {

        public void keyReleased (KeyEvent e) {
            mainCharacter.keyReleased (e);
        }

        public void keyPressed (KeyEvent e) {
            mainCharacter.keyPressed (e);
        }

    }

    /**
     * Adds the various graphics and text to the CoreMechanics Runs on backgroundImg
     * thread
     *
     * @param g
     */
    public void paint (Graphics g) {

        if (mainCharacter.dy == 1 && isJumping == false) {
            animator = new Thread (this);
            animator.start ();
            isJumping = true;
        }

        super.paint (g);

        Graphics2D g2d = (Graphics2D) g;

        if ((mainCharacter.getX () - 2500) % 6770 == 0)//Infinite Scrolling by checking when to replace background
        {
            mainCharacter.nx = 0;
        }

        if ((mainCharacter.getX () - 6050) % 6770 == 0) {
            mainCharacter.nx2 = 0;
        }

        g2d.drawImage (imgScrollingBackground, 1000 - mainCharacter.nx2, -310, null);

        if (mainCharacter.getX () >= 2500) {
            g2d.drawImage (imgScrollingBackground, 1000 - mainCharacter.nx, -310, null);
        }

        g2d.drawImage (mainCharacter.getImage (), mainCharacter.left - 25, characterY - 75, null);

        g2d.setFont (font);
        g2d.setColor (Color.WHITE);

        //Ensures no reprinting of instructions
        if (mainCharacter.getX () < 2500) {
            g2d.drawString ("Use the arrow keys to move!", 50 - mainCharacter.nx, 400);
            g2d.drawString ("Press the space bar to throw your axe!", 850 - mainCharacter.nx, 400);
            g2d.drawString ("Press 'e' to Exit!", 1950 - mainCharacter.nx, 400);
        }

        if (mainCharacter.health >= 0) {
            healthOfCharacter = mainCharacter.health;
        } else {
            healthOfCharacter = -1;
        }
        if (mainCharacter.health == 0) {
            MusicPlayer.playMusic ("Music/GameOver.wav");
        }

        g2d.drawString ("Health: " + healthOfCharacter, 50, 50);

        g2d.drawString ("Score: " + (Math.round (mainCharacter.Score)), 50, 100);

        g2d.drawString ("Ammo: " + mainCharacter.ammo, 50, 150);

        if (LoginGUI.getPrevUser () == true) {

            g2d.drawString ("Highscore: " + (Math.round (fileReader.content ()[LoginGUI.getIndex ()].getScore ())), 700, 50);

        }

        ArrayList proj = mainCharacter.getProjectiles ();

        for (int i = 0; i < proj.size (); i++) {

            Projectile fired = (Projectile) proj.get (i);
            g2d.drawImage (fired.getImage (), fired.getX (), fired.getY () - 75, null);
        }
        if (mainCharacter.getX () % 4075 == 0) {

            assetFrequency = 1;
            mainCharacter.platX = 0;

        }

        if (mainCharacter.x % 3500 == 0) {
            if (enemySpeed <= 5) {
                enemySpeed++;
            }

            if (enemyCounter <= 2) {
                mainCharacter.setSpeed (mainCharacter.speed + 1);
            }
        }
        if (mainCharacter.x % 450 == 0) {

            enemyCounter++;
            int rand = (int) (Math.random () * 100 + 1);
            en[enemyCounter] = new Enemy ((1000), 450 + rand, "Images/BatUp.png");
            en[enemyCounter].setY (450 + rand);

        }

        if (mainCharacter.x > 900) {

            if (en[enemyCounter].Alive () == true) {
                g2d.drawImage (en[enemyCounter].getImage (), en[enemyCounter].getX (), en[enemyCounter].getY (), null);
            }
        }

        if (mainCharacter.health <= 0) {

            mainCharacter.health--;
            int opacity = 150;
            Color myColour = new Color (255, opacity, opacity);
            g2d.drawString ("Game Over!", 512, 350);

            g2d.setComposite (AlphaComposite.SrcOver.derive (0.8f));
            g2d.setColor (myColour);
            g2d.fillRect (0, 0, 1024, 700);

        }
    }

    /**
     * Allows the character to run
     */
    @Override
    public void run () {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis ();
        while (onGround == false && offPlatform == false) {
            jump ();

            timeDiff = System.currentTimeMillis () - beforeTime;
            sleep = 10 - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep (sleep);
            } catch (Exception e) {
            }

            beforeTime = System.currentTimeMillis ();

        }
        notJumping = false;
        jumpPeak = false;
        onGround = false;
        isJumping = false;

    }

    boolean notJumping = false;//Checks if not jumping
    boolean jumpPeak = false;//Checks if jump is at peak
    boolean onGround = false;//Checks if on ground
    boolean offPlatform = false;//Checks if off Platform

    /**
     * Allows the character to jump
     */
    public void jump () {

        if (notJumping == false) {
            characterY = characterY - 5;
        }

        if (characterY == 400) {
            notJumping = true;
        }

        if (notJumping == true && characterY <= 600) {
            characterY = characterY + 5;
            if (characterY == 600) {
                onGround = true;
            }
        }

        if (mainCharacter.getX () < -600 && mainCharacter.getX () < 732 * assetFrequency && characterY == heightOfPlatform - 40) {

            if (jumpPeak == false) {

                characterY = characterY - 2;
            }

            if (characterY == 100000) {
                jumpPeak = true;
            }

            if (jumpPeak == true && characterY <= 450) {
                characterY = characterY + 2;
                if (characterY == heightOfPlatform) {
                    onGround = true;
                    offPlatform = true;
                }

            }

        }

    }

}
