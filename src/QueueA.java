import java.util.PriorityQueue;
/**
 * Created by winfredjames on 11/29/15.
 */
public class QueueA {

    private PriorityQueue<Diner> pq;
    private static QueueA queueA;

    public QueueA() {
        pq = new PriorityQueue<>();
    }

    public static synchronized QueueA getInstance() {
        if (queueA == null) {
            queueA = new QueueA();
        }
        return queueA;
    }

    public synchronized void addDiner(Diner d) {
        pq.add(d);
        notifyAll();
    }

    public synchronized Diner check() throws InterruptedException {

        while (pq.isEmpty()) {
            wait();
        }

        Diner d = pq.poll();
        return d;

    }
}
