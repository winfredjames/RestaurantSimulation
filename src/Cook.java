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
            cookIt(idx);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cookIt(int i) throws InterruptedException {

        while (Timer.getInstance().getTime() < 120) {

            Diner d = QueueA.getInstance().check();

            if (d.burger > 0) {
            //    System.out.println("********************" + i);
                try {
                    BurgerMachine.getInstance().cook(d.id, d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (d.fries > 0) {
           //     System.out.println("********************" + i);
                try {
                    FriesMachine.getInstance().cook(d.id, d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (d.coke > 0) {
                try {
                    CokeMachine.getInstance().cook(d.id, d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (d.icecream > 0) {
                try {
                    IceCreamMachine.getInstance().cook(d.id, d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (d) {

                System.out.println("Order is complete for Diner " + d.id + " at " + Timer.getInstance().getTime());
                d.busy = true;
                d.notify();
            }

        }
    }
}



