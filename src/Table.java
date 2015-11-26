/**
 * Created by winfredjames on 11/26/15.
 */
public class Table extends Thread {
    public static boolean[] tables;
    public int noOfTables;
    public static boolean ready=true;

    public Table(int noOfTables) {
        this.noOfTables = noOfTables;
    }

    public boolean find() {
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i]) {
                return true;
            }
        }
        return false;
    }

    public synchronized void setTable(boolean place) throws InterruptedException {
        while (!place && ready) {
            wait();
        }
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i]) {
                tables[i] = true;
                break;
            }
        }
        ready = true;
    }

    public synchronized void releaseTable() {
        for (int i = 0; i < noOfTables; i++) {
            if (tables[i]) {
                tables[i] = false;
            }
        }
        ready = false;
        notifyAll();
    }

}
