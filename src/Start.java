import java.sql.Time;

public class Start extends Thread {

    public int id;
    public Diner d;
    int noOfDiners;

    Table tb;
    TablePerson tp;

    public Start(Diner d, int id, Table tb,int noOfDiners) {
        this.d = d;
        this.id = id;
        this.tb = tb;
        this.noOfDiners=noOfDiners;
    }

    public synchronized void run() {

        try {
            tp = tb.setTable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Diner " + id + " sits at time " + Color.get().blue() + Timer.getInstance().getTime() + Color.get().reset() + " at table " + tp.id);

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

        while (tempTime + 30 > Timer.getInstance().getTime()) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Diner " + d.id + " finishes eating and leaves the restaurant at time " + Color.get().red() + Timer.getInstance().getTime() + Color.get().reset() );

        tb.releaseTable(tp);

        if(id==noOfDiners)
            System.exit(0);
    }
}
