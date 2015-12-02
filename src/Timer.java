public class Timer extends Thread {
    private static int time = 1;
    private static Timer timer;

    private Timer() {

    }

    public static Timer getInstance() {
        if (timer == null) {
            timer = new Timer();
        }

        return timer;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                this.time++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTime() {
        return time;
    }
}

class Color{

    private static Color color;

    private Color() {

    }

    public static Color get() {
        if (color == null) {
            color = new Color();
        }

        return color;
    }

    public String red() {
        return "\u001B[31m";
    }

    public String blue() {
        return "\u001B[34m";
    }

    public String reset(){
        return "\u001B[0m";
    }

}
