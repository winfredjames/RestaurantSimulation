/**
 * Created by winfredjames on 11/26/15.
 */

import java.sql.Time;
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
                QueueA.getInstance().add(new CookTime(id,diner.get(id).totalTime,false));
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
    boolean busy;

    public CookTime(int diner_id,int time,boolean busy){
        this.diner_id = diner_id;
        this.time = time;
        this.busy = busy;
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
    public boolean isBusy;

    public Cook(int noCooks, Table tb, int idx,Vector<Diner> diner) {
        this.idx = idx;
        this.noCooks = noCooks;
        this.tb = tb;
        this.diner = diner;
    }

    public void run() {
        try {
            cookIt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void cookIt() throws InterruptedException {
        while(Timer.getInstance().getTime()<120){

            if(!QueueA.getInstance().isEmpty()){
                    CookTime c = (CookTime) QueueA.getInstance().poll();
                    if(!c.busy){
                        BurgerMachine.getInstance().cook((int)c.diner_id);
                    }
            }

        }
    }
}

class BurgerMachine extends Thread{

    private boolean working;

    private static BurgerMachine burgerMachine;

    private BurgerMachine(){

    }

    public static BurgerMachine getInstance(){
        if(burgerMachine == null){
            burgerMachine = new BurgerMachine();
        }

        return burgerMachine;
    }

    public synchronized void cook(int id) throws InterruptedException {
            while(working){
                wait();
            }
            working = true;
            System.out.println("cooking burger for " + (id+1) +  " at "+ Timer.getInstance().getTime());
            while(Timer.getInstance().getTime()+5> Timer.getInstance().getTime()){
                Thread.sleep(0);
            }
            working=false;
            notifyAll();

    }
}