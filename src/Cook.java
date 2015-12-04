import java.util.Vector;

public class Cook extends Thread {
    public int cookId;

    public Cook(int i){
        this.cookId = i;
    }
    public void run() {
        while (true) {

            Diner d = null;
            try {
                d = QueueA.getInstance().check();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cook " + cookId + " is processing for Diner "+ d.id +" at time " + Color.get().blue() + Timer.getInstance().getTime() + Color.get().reset());

            if (d.burger > 0) {
                try {
                    BurgerMachine.getInstance().cook(d.id, d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (d.fries > 0) {
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

                System.out.println("Order is complete for Diner " + d.id + " at time " + Color.get().blue() +Timer.getInstance().getTime() + Color.get().reset());
                d.busy = true;
                d.notify();
            }

        }
    }
}



