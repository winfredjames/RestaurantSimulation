import java.util.Vector;

/**
 * Created by winfredjames on 11/29/15.
 */

public class Cook extends Thread {

    public static int noCooks;
    public Table tb;
    public int idx;
    public Vector<Diner> diner;

    public Cook(int noCooks) {
        this.idx = idx;
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

                Diner d = QueueA.getInstance().check();
                BurgerMachine.getInstance().cook(d.id, d);

            }

        }
    }


