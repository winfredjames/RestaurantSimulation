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
        Vector<Diner> diners = new Vector<Diner>();
        Vector<DinerStatus> dinerStatus = new Vector<>();
        int noOfDinners = in.nextInt();
        int tempDinners = noOfDinners;
        int noOfTables = in.nextInt();
        int noOfCooks = in.nextInt();
        int id = 0;
        Table tb = new Table(noOfTables);


        while (tempDinners-- > 0) {
            diners.addElement(new Diner(id++, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), tb));
        }

        tb.start();

        Timer t = Timer.getInstance();
        t.start();

        Cook c;
        int idx=0;
        for(int i=0 ;i < noOfCooks; i++){
            c = new Cook(noOfCooks,tb,idx++,diners);
            c.start();
        }

        int i = 0;
        while(t.getTime()<120 && i<noOfDinners){
            if(diners.get(i).entryTime==t.getTime()){
                diners.get(i).start();
            }
            i++;
        }

    }


}
