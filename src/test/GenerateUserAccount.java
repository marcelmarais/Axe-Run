package test;

public class GenerateUserAccount {

    /**
     * Blank constructor for generatePassword
     */
    GenerateUserAccount () {
    }

    /**
     * Generates a password for the user given a strength and name
     *
     * @param strength Strength of password
     * @param name Name of user
     * @return Generated password
     */
    public static String getPass (int strength, String name) {

        String sourceA = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        String sourceB = "abcdefghijqlmnopqrstuvwxyz";
        String pass = "";

        for (int i = 0; i < strength; i++) {
            int n = (int) (Math.random () * 24 + 1);

            char a = sourceA.charAt (n);
            char b = sourceB.charAt (n);
            char d = 'a';

            pass = pass + a + b + d;
        }

        return pass;
    }

    /**
     * Creates a username for the user
     *
     * @param name
     * @return The name of the user
     */
    public static String getUserName (String name) {

        char intial = name.charAt (0);
        String intialString = "";
        intialString = intialString.valueOf (intial);
        name = intialString.toUpperCase () + name;

        return name;
    }

}
