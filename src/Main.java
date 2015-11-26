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
    static class Diner {

        int id;
        int entryTime;
        int burger;
        int fries;
        int coke;
        int icecream;
        int totalTime;

        public Diner(int id, int entryTime, int burger, int fries, int coke, int icecream) {
            this.id = id;
            this.entryTime = entryTime;
            this.burger = burger;
            this.fries = fries;
            this.coke = coke;
            this.icecream = icecream;
            this.totalTime = (burger * 5) + (fries * 3) + (coke * 2) + icecream;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        InputReader in = new InputReader();
        Vector<Diner> diners = new Vector<Diner>();
        int noOfDinners = in.nextInt();
        int tempDinners = noOfDinners;
        int noOfTables = in.nextInt();
        int noOfCooks = in.nextInt();
        int id = 0;
        while (tempDinners-- > 0) {
            diners.addElement(new Diner(id++, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
        }
        Timer t = new Timer();
        t.start();
        Table tb = new Table(noOfTables);
        tb.start();
        int i = 0;

        while(t.getTime()<120){
            if(diners.get(i).entryTime==t.getTime()){
                    tb.setTable(tb.find());
            }
            i++;
        }

        Cook c = new Cook();

    }


}
