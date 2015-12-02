/**
 * Created by winfred james on 11/29/15.
 */
public class Machines {
}

class BurgerMachine extends Thread {

    private boolean working;

    private static BurgerMachine burgerMachine;

    private BurgerMachine() {

    }

    public static synchronized BurgerMachine getInstance() {
        if (burgerMachine == null) {
            burgerMachine = new BurgerMachine();
        }

        return burgerMachine;
    }


    public synchronized void cook(int id, Diner d) throws InterruptedException {
        while (working) {
            wait();
        }
        working = true;
        System.out.println("Cooking burger for " + (id) + " at time " + Color.get().blue() + Timer.getInstance().getTime() + Color.get().reset());
        int total = Timer.getInstance().getTime();
        while (total + (5 * d.burger) > Timer.getInstance().getTime()) {
            Thread.sleep(0);
        }
        working = false;
        notifyAll();

    }
}

class FriesMachine extends Thread {

    private boolean working;

    private static FriesMachine friesMachine;

    private FriesMachine() {

    }

    public static synchronized FriesMachine getInstance() {
        if (friesMachine == null) {
            friesMachine = new FriesMachine();
        }

        return friesMachine;
    }

    public synchronized void cook(int id, Diner d) throws InterruptedException {
        while (working) {
            wait();
        }
        working = true;
        System.out.println("Cooking fries for " + (id) + " at time " + Color.get().blue() + Timer.getInstance().getTime() + Color.get().reset());
        int total = Timer.getInstance().getTime();
        while (total + (3 * d.fries) > Timer.getInstance().getTime()) {
            Thread.sleep(0);
        }
        working = false;
        notifyAll();

    }
}

class CokeMachine extends Thread {

    private boolean working;

    private static CokeMachine cokeMachine;

    private CokeMachine() {

    }

    public static synchronized CokeMachine getInstance() {
        if (cokeMachine == null) {
            cokeMachine = new CokeMachine();
        }

        return cokeMachine;
    }

    public synchronized void cook(int id, Diner d) throws InterruptedException {
        while (working) {
            wait();
        }
        working = true;
        System.out.println("Preparing coke for " + (id) + " at time " + Color.get().blue() +Timer.getInstance().getTime() + Color.get().reset());
        int total = Timer.getInstance().getTime();
        while (total + (2 * d.coke) > Timer.getInstance().getTime()) {
            Thread.sleep(0);
        }
        working = false;
        notifyAll();

    }
}


class IceCreamMachine extends Thread {

    private boolean working;

    private static IceCreamMachine iceCreamMachine;

    private IceCreamMachine() {

    }

    public static synchronized IceCreamMachine getInstance() {
        if (iceCreamMachine == null) {
            iceCreamMachine = new IceCreamMachine();
        }

        return iceCreamMachine;
    }

    public synchronized void cook(int id, Diner d) throws InterruptedException {
        while (working) {
            wait();
        }
        working = true;
        System.out.println("Preparing ice cream for " + (id) + " at time " + Color.get().blue() + Timer.getInstance().getTime() + Color.get().reset());
        int total = Timer.getInstance().getTime();
        while (total + (d.icecream) > Timer.getInstance().getTime()) {
            Thread.sleep(0);
        }
        working = false;
        notifyAll();

    }
}
