
/**
 * Created by winfredjames on 11/25/15.
 */
public class Timer extends Thread {
    private static int time = 1;
    private static Timer timer;

    private Timer(){

    }

    public static Timer getInstance(){
        if(timer == null){
            timer  = new Timer();
        }

        return timer;
    }

    public void run() {
        try {
            while(true) {
                Thread.sleep(2000);
                this.time++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTime(){
        return time;
    }
}
