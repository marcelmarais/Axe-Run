package test;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class GameFrame {

    Toolkit tk = Toolkit.getDefaultToolkit ();

    int x = (int) tk.getScreenSize ().getWidth ();//Length of user's screen
    int y = (int) tk.getScreenSize ().getHeight ();//Height of user's screen
    JFrame first = new JFrame ();
    boolean done = false;

    /**
     * Creates the JFrame on which the game is displayed
     */
    public void open () {

        first.setAlwaysOnTop (true);
        first.setSize (1024, 700);
        first.setLocation ((x - first.getWidth ()) / 2 - 23, ((y - first.getHeight ()) / 2));//Ensures the frame is in the center of the screen
        first.setUndecorated (true);
        first.add (new CoreMechanics ());
        first.setVisible (true);
    }

    /**
     * Closes the JFrame
     */
    public void close () {
        first.setVisible (false);
    }

    /**
     * Moves the user to the next screen
     *
     * @param next
     */
    public void setNextScreen (boolean next) {
        done = next;
    }

}
