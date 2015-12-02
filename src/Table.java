import java.util.*;

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

    public Table(int noOfTables) {
        this.noOfTables = noOfTables;
        tables = new TablePerson[noOfTables];

        for (int i = 0; i < noOfTables; i++) {
            tables[i] = new TablePerson(i, false);
        }
    }

    public int find() {
        int count = 0;
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i].presence) {
                count++;
            }
        }
        return count;
    }

    public int find_no() {
        if(find()==noOfTables && Timer.getInstance().getTime()>120){
            System.exit(0);
        }
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i].presence && Timer.getInstance().getTime()<120) {
                tables[i].presence = true;
                tables[i].id=i+1;
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

    public synchronized void releaseTable(TablePerson tp) {

        tp.presence = false;
        notifyAll();
    }

}

