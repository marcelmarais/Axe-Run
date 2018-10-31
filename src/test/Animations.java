package test;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Animations {

    //Array of image icons could not achieve same effect therefore
    ImageIcon wr1 = new ImageIcon ("Images/src/wr1.png");
    ImageIcon wr1a = new ImageIcon ("Images/walkingRight.png");
    ImageIcon wr2 = new ImageIcon ("Images/wr2.png");
    ImageIcon wr3 = new ImageIcon ("Images/wr3.png");
    ImageIcon wr4 = new ImageIcon ("Images/wr4.png");
    ImageIcon wr5 = new ImageIcon ("Images/wr5.png");
    ImageIcon wr6 = new ImageIcon ("Images/wr6.png");
    ImageIcon wr7 = new ImageIcon ("Images/wr7.png");
    ImageIcon wr8 = new ImageIcon ("Images/wr8.png");
    ImageIcon wr9 = new ImageIcon ("Images/wr9.png");

    Image still;

    double cntAnimation = 0;
    int start = 0;

    /**
     * Changes between images in running Animations
     */
    public void run () {
        cntAnimation = cntAnimation + 3;
        Character.dx = 1 + Character.speed;
        Character.still = wr1a.getImage ();

        if (cntAnimation % 1.5 == 0 && start == 0) {

            Character.still = wr1a.getImage ();

        }
        if (cntAnimation % 1.5 == 0 && cntAnimation > 1.5 && start != 0) {

            Character.still = wr3.getImage ();

        }
        if (cntAnimation % 1.5 == 0 && cntAnimation > 3) {

            Character.still = wr4.getImage ();

        }

        if (cntAnimation % 1.5 == 0 && cntAnimation > 4.5) {

            Character.still = wr5.getImage ();

        }
        if (cntAnimation % 1.5 == 0 && cntAnimation > 6) {

            Character.still = wr6.getImage ();

        }
        if (cntAnimation % 1.5 == 0 && cntAnimation > 8.5) {

            Character.still = wr7.getImage ();

        }
        if (cntAnimation % 1.5 == 0 && cntAnimation > 10) {
            Character.still = wr8.getImage ();

        }
        if (cntAnimation % 1.5 == 0 && cntAnimation > 11.5) {

            Character.still = wr9.getImage ();
            cntAnimation = 0;

        }

    }
}
