import java.sql.Time;

/**
 * Created by winfredjames on 11/29/15.
 */
public class Start extends Thread {

    public int id;
    public Diner d;

    Table tb;
    TablePerson tp;

    public Start(Diner d, int id, Table tb){
        this.d = d;
        this.id =id;
        this.tb = tb;
    }

    public synchronized void run() {
        System.out.println("Diner  " + id + " enters the restaurant");

        try {
            tp = tb.setTable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        QueueA.getInstance().addDiner(d);

        synchronized (d) {
            while (!d.busy) {
                try {
                   d.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        int tempTime = Timer.getInstance().getTime();

        while(tempTime + 30 > Timer.getInstance().getTime()){
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Diner " + d.id +" finishes eating and leaves the restaurant at " + Timer.getInstance().getTime() );

        tb.releaseTable(tp);

    }

}
