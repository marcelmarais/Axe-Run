package test;

public class Main {

    static GameFrame gameFrame = new GameFrame ();
    static HighscoreGUI highscore = new HighscoreGUI ();
    static LoginGUI login = new LoginGUI ();
    static String name, pass;

    /**
     * Main method that opens all screens and orchestrates the reading and writing of
     * files
     *
     * @param args
     */
    public static void main (String[] args) {
        {
            MusicPlayer.playMusic ("Music/soundtrack.wav");

            login.setVisible (true);
            fileReader.readStuff ();

        }
    }

    /**
     * Persists all data to the text file when button is pressed
     */
    public static void save () {

        name = login.name;
        pass = login.password;

        fileWriter.writeStuff (name, pass);
        if (fileReader.finish = true) {

            fileReader.readStuff ();

        }

    }

    /**
     * Starts the game when data is validated
     */
    public static void next () {

        gameFrame.open ();

    }

    /**
     * Shows the high scores when the user dies
     *
     * @throws Exception
     */
    public static void showScores () throws Exception {

        fileReader.writeScore (LoginGUI.getIndex (), (int) (Character.Score));
        gameFrame.close ();
        highscore.open ();
    }

}
