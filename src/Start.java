/**
 * Created by winfredjames on 11/29/15.
 */
public class Start extends Thread {

    public int id;
    public Diner d;

    Table tb;
    TablePerson tp;

    public Start(Diner d, int id, Table tb){
        this.d = d;
        this.id =id;
        this.tb = tb;
    }

    public void run(){
        System.out.println("Diner " + id +" enters the restaurant" );

        try {
            tp = tb.setTable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        QueueA.getInstance().addDiner(d);

    }

    private void findtable() {

    }
}
