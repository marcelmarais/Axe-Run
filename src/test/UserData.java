package test;

/**
 *
 * @author MarcelMarais
 */
public class UserData {

    private int score;
    private String name, password;

    /**
     * Takes Data from User input and initialises class
     *
     * @param nameData
     * @param passwordData
     */
    UserData () {
    }

    /**
     *
     * @return name of user
     */
    public String getName () {
        return name;
    }

    /**
     * Sets the user's name
     *
     * @param nameData
     */
    public void setName (String nameData) {
        name = nameData;
    }

    /**
     * Returns the score achieved by the user
     *
     * @return The user's score
     */
    public int getScore () {
        return score;
    }

    /**
     * Sets the user's score
     *
     * @param scoreData
     */
    public void setScore (int scoreData) {
        score = scoreData;
    }

    /**
     * Returns the user's password
     *
     * @return password
     */
    public String getPass () {
        return password;
    }

    /**
     * Set's the the user's password
     *
     * @param passData
     */
    public void setPass (String passData) {
        password = passData;
    }

    /**
     * Shows all the user's data
     *
     * @return All user data
     */
    public String toString () {
        String s = "Name: " + name + "\tPassword: " + password + "\tScore: " + score;
        return s;
    }
}
