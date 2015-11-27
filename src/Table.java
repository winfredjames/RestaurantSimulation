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
    public static volatile TablePerson[] tables;
    public int noOfTables;
    public static boolean ready = true;
    public Queue<Integer> queue = new LinkedList<Integer>();

    public Table(int noOfTables) {
        this.noOfTables = noOfTables;
        tables = new TablePerson[noOfTables];
        for (int i = 0; i < noOfTables; i++) {
            tables[i] = new TablePerson(-1, false);
        }
    }

    public synchronized boolean find() {
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i].presence) {
                return true;
            }
        }
        return false;
    }

    public synchronized int find_no() {
        for (int i = 0; i < noOfTables; i++) {
            if (tables[i].presence) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void setTable(boolean place, int id) throws InterruptedException {

        queue.add(id);

        while (!place && ready) {
            wait();
        }
        if (!queue.isEmpty()) {
            id = queue.remove();
        }
        for (int i = 0; i < noOfTables; i++) {
            if (!tables[i].presence) {
                tables[i].presence = true;
                tables[i].id = id;
                break;
            }
        }
        ready = true;
    }

    public synchronized void releaseTable() {
        for (int i = 0; i < noOfTables; i++) {
            if (tables[i].presence) {
                tables[i].presence = false;
                tables[i].id=-1;
            }
        }
        ready = false;
        notifyAll();
    }

}
class CookTime implements Comparable<CookTime>{

    int diner_id;
    int time;

    public CookTime(int diner_id,int time){
        this.diner_id = diner_id;
        this.time = time;
    }

    @Override
    public int compareTo(CookTime o) {
        return Integer.compare(this.time,o.time);
    }

    @Override
    public boolean equals(Object o){
        CookTime c = (CookTime) o;
        return Integer.compare(this.diner_id,c.diner_id)==0;
    }
}


class Cook extends Thread {
    public static int noCooks;
    public static PriorityBlockingQueue<CookTime> pq = new PriorityBlockingQueue<CookTime>();
    public  Table tb;

    public Cook(int noCooks, Table tb) {
        this.noCooks = noCooks;
        this.tb = tb;
    }

    public synchronized void run() {
        while(true){
            int id = tb.find_no();
            if(id!=-1 && !pq.contains(new CookTime(id,0))){

            }
        }

    }
}