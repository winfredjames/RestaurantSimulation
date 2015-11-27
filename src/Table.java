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

    public Vector<Diner> diner;
    public static volatile TablePerson[] tables;
    public int noOfTables;
    public static boolean ready = true;
    public Queue<Integer> queue = new LinkedList<Integer>();

    public Table(int noOfTables,Vector<Diner> diner) {
        this.noOfTables = noOfTables;
        tables = new TablePerson[noOfTables];
        this.diner = diner;
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
                QueueA.getInstance().add(new CookTime(id,diner.get(id).totalTime));
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

class QueueA{
    private static PriorityBlockingQueue<CookTime> pq;
    private QueueA(){

    }
    public static PriorityBlockingQueue getInstance(){
        if(pq == null){
            pq  = new PriorityBlockingQueue<CookTime>();
        }
        return pq;
    }
}

class Cook extends Thread {

    public static int noCooks;
    public  Table tb;
    public int idx;
    public Vector<Diner> diner;

    public Cook(int noCooks, Table tb, int idx,Vector<Diner> diner) {
        this.idx = idx;
        this.noCooks = noCooks;
        this.tb = tb;
        this.diner = diner;
    }

    public void run() {
        cookIt();
    }

    public synchronized void cookIt(){
        while(Timer.getInstance().getTime()<120){
            if(!QueueA.getInstance().isEmpty()){
                
            }
        }
    }
}