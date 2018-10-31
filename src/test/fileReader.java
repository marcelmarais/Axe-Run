package test;

import java.io.*;

public class fileReader {

    static UserData[] d = new UserData[50];
    static int i = 0, cnt = 0, z = 0;
    static String pos;
    static BufferedReader br, br2;
    static BufferedWriter out, out2;
    static boolean finish = false;

    static String fileScore = "Data/UsersWithScore.txt";

    /**
     * Reads file from a text file
     *
     * @return the number of lines in the text file
     */
    public static int readStuff () {
        try {
            String file = "Data/Users.txt";
            int len = 0;

            BufferedReader br = new BufferedReader (new FileReader (file));
            BufferedReader br2 = new BufferedReader (new FileReader (fileScore));
            BufferedReader br3 = new BufferedReader (new FileReader (file));

            String line = br.readLine ();
            String line2 = br2.readLine ();

            line = br.readLine ();
            while (line != null) {
                line = br.readLine ();

                len++;
            }
            String line3 = br3.readLine ();
            line3 = br3.readLine ();

            len = len - 1;

            LoginGUI.index = len;
            i = 0;
            while (i <= len) {

                String field[] = line3.split ("#");
                d[i] = new UserData ();
                d[i].setName (field[0]);
                d[i].setPass (field[1]);

                line3 = br3.readLine ();
                i++;
            }
            i = 0;

            while (i < len) {
                d[i].setScore (Integer.parseInt (line2.split ("#")[2]));
                line2 = br2.readLine ();
                i++;

            }

            finish = true;
            while (z < i) {
                z++;
            }
        } catch (FileNotFoundException e) {

            System.out.println ("Cant find the file");
        } catch (IOException a) {

            System.out.println ("Input error");
        }

        return i;
    }

    /**
     * Searches for user's password given an index
     *
     * @param name
     * @return User's password
     */
    public String getPass (String name) {

        while (cnt < d.length) {
            System.out.println (d[z]);
            if (d[cnt].getName ().equals (name)) {
                pos = d[cnt].getPass ();
            }
            cnt++;
        }
        return pos;
    }

    public static UserData[] content () {
        return d;
    }

    public static void writeScore (int index, int score) throws Exception {

        try {
            if (score > fileReader.content ()[index].getScore ()) {

                fileReader.content ()[index].setScore (score);
            }
        } catch (NullPointerException a) {
            fileReader.content ()[index].setScore (0);
            System.out.println ("Index is incorrect");

        }
        try {
            out2 = new BufferedWriter (new FileWriter (fileScore));
            br2 = new BufferedReader (new FileReader (fileScore));

            int len = fileWriter.getLength () - 1;

            for (int i = 0; i < len; i++) {
                out2.write (d[i].getName () + "#" + d[i].getPass () + "#" + d[i].getScore ());
                out2.newLine ();
            }
            out2.close ();

        } catch (IOException e) {
            e.printStackTrace ();

        }
    }
}
