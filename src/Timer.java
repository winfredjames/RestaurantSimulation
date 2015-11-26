/**
 * Created by winfredjames on 11/25/15.
 */
public class Timer extends Thread {
    public static int time = 1;

    public void run() {
        try {
            while(true) {
                Thread.sleep(1000);
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
