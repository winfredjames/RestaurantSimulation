/**
 * Created by winfredjames on 11/25/15.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

    static class InputReader {

        File file = new File("test");
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(file));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        InputReader in = new InputReader();
        Vector<Diner> dinersInfo = new Vector<>();
        int noOfDiners = in.nextInt();
        int tempDiners = noOfDiners;
        int noOfTables = in.nextInt();
        int noOfCooks = in.nextInt();
        int id = 1;
        Table tb = new Table(noOfTables);

        while (tempDiners-- > 0) {
            dinersInfo.addElement(new Diner(id++, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), tb, false));

        }

        tb.start();

        Cook[] c = new Cook[noOfCooks];
        for (int i = 0; i < noOfCooks; i++) {
            c[i] = new Cook(i+1);
        }

        for (int i = 0; i < noOfCooks; i++) {
            c[i].start();
        }

        Timer t = Timer.getInstance();
        t.start();

        Start[] s = new Start[noOfDiners];

        for (int i = 0; i < noOfDiners; i++) {
            s[i] = new Start(dinersInfo.get(i), dinersInfo.get(i).id, tb, noOfDiners);
        }

        int i = 0;
        while (Timer.getInstance().getTime() < 120 && i < noOfDiners) {
            if (dinersInfo.get(i).entryTime == t.getTime()) {
                s[i].start();
                i++;
            }
        }

        while (true) {

        }
    }
}
