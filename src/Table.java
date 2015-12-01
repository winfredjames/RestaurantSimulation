/**
 * Created by winfredjames on 11/26/15.
 */

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
class TablePerson {
    int id;
    boolean presence;

    public TablePerson(int id, boolean presence) {
        this.id = id;
        this.presence = presence;
    }
}

public class Table extends Thread {

    public static TablePerson[] tables;
    public int noOfTables;
    public static boolean ready = true;
    public Queue<Integer> queue = new LinkedList<Integer>();

    public Table(int noOfTables) {
        this.noOfTables = noOfTables;
        tables = new TablePerson[noOfTables];

        for (int i = 0; i < noOfTables; i++) {
            tables[i] = new TablePerson(i, false);
        }
    }

    public boolean find() {
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i].presence) {
                return true;
            }
        }
        return false;
    }

    public int find_no() {
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i].presence) {
                tables[i].presence = true;
                return i;
            }
        }
        return -1;
    }

    public synchronized TablePerson setTable() throws InterruptedException {
        int idx = find_no();

        while (idx == -1) {
            wait();
            idx = find_no();
        }
        return tables[idx];
    }

    public synchronized void releaseTable(TablePerson id) {

        id.presence = false;
        notifyAll();
    }

}

