/**
 * Created by winfred james on 11/29/15.
 */
public class BurgerMachine extends Thread{

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


    public synchronized void cook(int id,Diner d) throws InterruptedException {
        while(working){
            wait();
        }
        working = true;
        System.out.println("cooking burger for " + (id) +  " at time "+ Timer.getInstance().getTime());
        int total = Timer.getInstance().getTime();
        while(total + (5*d.burger)> Timer.getInstance().getTime()){
            Thread.sleep(0);
        }
        working=false;
        notifyAll();

    }
}

class FriesMachine extends Thread{

    private boolean working;

    private static FriesMachine friesMachine;

    private FriesMachine(){

    }

    public static FriesMachine getInstance(){
        if(friesMachine == null){
            friesMachine = new FriesMachine();
        }

        return friesMachine;
    }

    public synchronized void cook(int id,Diner d) throws InterruptedException {
        while(working){
            wait();
        }
        working = true;
        System.out.println("cooking fries for " + (id) +  " at time "+ Timer.getInstance().getTime());
        int total = Timer.getInstance().getTime();
        while(total + (3*d.fries)> Timer.getInstance().getTime()){
            Thread.sleep(0);
        }
        working=false;
        notifyAll();

    }
}

class CokeMachine extends Thread{

    private boolean working;

    private static CokeMachine cokeMachine;

    private CokeMachine(){

    }

    public static CokeMachine getInstance(){
        if(cokeMachine == null){
            cokeMachine = new CokeMachine();
        }

        return cokeMachine;
    }

    public synchronized void cook(int id,Diner d) throws InterruptedException {
        while(working){
            wait();
        }
        working = true;
        System.out.println("Preparing coke for " + (id) +  " at time "+ Timer.getInstance().getTime());
        int total = Timer.getInstance().getTime();
        while(total + (2*d.coke)> Timer.getInstance().getTime()){
            Thread.sleep(0);
        }
        working=false;
        notifyAll();

    }
}


class IceCreamMachine extends Thread{

    private boolean working;

    private static IceCreamMachine iceCreamMachine;

    private IceCreamMachine(){

    }

    public static IceCreamMachine getInstance(){
        if(iceCreamMachine == null){
            iceCreamMachine = new IceCreamMachine();
        }

        return iceCreamMachine;
    }

    public synchronized void cook(int id,Diner d) throws InterruptedException {
        while(working){
            wait();
        }
        working = true;
        System.out.println("Preparing icecream for " + (id) +  " at time "+ Timer.getInstance().getTime());
        int total = Timer.getInstance().getTime();
        while(total + (d.icecream)> Timer.getInstance().getTime()){
            Thread.sleep(0);
        }
        working=false;
        notifyAll();

    }
}