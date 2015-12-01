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