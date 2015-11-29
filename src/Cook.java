import java.util.Vector;

/**
 * Created by winfredjames on 11/29/15.
 */

public class Cook extends Thread {

    public static int noCooks;
    public Table tb;
    public int idx;
    public Vector<Diner> diner;
    public boolean isBusy;

    public Cook(int noCooks, Table tb, int idx, Vector<Diner> diner) {
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
        while (Timer.getInstance().getTime() < 120) {
            Thread.sleep(1000);
            if (!QueueA.getInstance().isEmpty()) {
                Diner d = (Diner) QueueA.getInstance().poll();
                BurgerMachine.getInstance().cook(d.id,d);

            }

        }
    }
}
