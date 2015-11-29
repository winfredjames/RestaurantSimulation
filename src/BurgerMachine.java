/**
 * Created by winfredjames on 11/29/15.
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
        System.out.println("cooking burger for " + (id+1) +  " at time "+ Timer.getInstance().getTime());
        while(Timer.getInstance().getTime()+5> Timer.getInstance().getTime()){
            Thread.sleep(0);
        }
        working=false;
        notifyAll();

    }
}