package test;

import java.io.*;

public class fileWriter {

    static BufferedWriter out, out2;
    static BufferedReader br, br2;
    static String file = "Data/Users.txt";
    static String fileScore = "Data/UsersWithScore.txt";
    public static int length;

    static boolean end = false;

    /**
     * File Writer blank constructor
     */
    public fileWriter () {
    }

    /**
     * Writes data to the text file when given a username and password
     *
     * @param uN Username
     * @param p Password
     */
    public static void writeStuff (String uN, String p) {
        try {

            br = new BufferedReader (new FileReader (file));
            out = new BufferedWriter (new FileWriter (file, true));
            end = true;
            out.newLine ();

            int i = 0;

            while (br.readLine () != null) {
                i++;
            }

            out.write (uN + "#" + p);
            out.close ();
            end = true;

        } catch (IOException e) {

            System.out.println ("TEXT FILE COULD NOT BE FOUND ");
            end = false;
        }
    }

    public static int getLength () throws Exception {
        int len = 0;
        br = new BufferedReader (new FileReader (file));
        String line = br.readLine ();
        while (line != null) {
            len++;
            line = br.readLine ();
        }
        length = len;
        return length;
    }

    public static void writeFinal () throws Exception {
        try {
            out2 = new BufferedWriter (new FileWriter (fileScore));
            br2 = new BufferedReader (new FileReader (fileScore));

            for (int i = 0; i < fileWriter.getLength () - 2; i++) {

                out2.write (fileReader.content ()[i].getName () + "#" + fileReader.content ()[i].getPass () + "#" + fileReader.content ()[i].getScore ());
                out2.newLine ();
            }
            out2.close ();
        } catch (IOException e) {
            e.printStackTrace ();

        }
    }
}
