import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by winfredjames on 11/27/15.
 */
public class Diner implements Comparable<Diner>{

    int id;
    int entryTime;
    int burger;
    int fries;
    int coke;
    int icecream;
    int totalTime;
    boolean busy;



    public Diner(int id, int entryTime, int burger, int fries, int coke, int icecream, Table tb,boolean busy) {
        this.id = id;
        this.entryTime = entryTime;
        this.burger = burger;
        this.fries = fries;
        this.coke = coke;
        this.icecream = icecream;
        this.totalTime = (burger * 5) + (fries * 3) + (coke * 2) + icecream;
        this.busy=false;

    }

    @Override
    public int compareTo(Diner o) {
        return Integer.compare(this.totalTime,o.totalTime);
    }

    @Override
    public boolean equals(Object o){
        Diner d = (Diner) o;
        return Integer.compare(this.id,d.id)==0;
    }
}




