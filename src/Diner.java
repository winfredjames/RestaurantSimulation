/**
 * Created by winfredjames on 11/27/15.
 */
public class Diner extends Thread{

    int id;
    int entryTime;
    int burger;
    int fries;
    int coke;
    int icecream;
    int totalTime;
    Table tb;
    int tableno;


    public Diner(int id, int entryTime, int burger, int fries, int coke, int icecream, Table tb) {
        this.id = id;
        this.entryTime = entryTime;
        this.burger = burger;
        this.fries = fries;
        this.coke = coke;
        this.icecream = icecream;
        this.totalTime = (burger * 5) + (fries * 3) + (coke * 2) + icecream;
        this.tb = tb;
    }

    public void run(){

        System.out.println("Diner " + id +" enters the restaurant" );

        try {
           tableno = tb.setTable(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    private void findtable() {

    }
}
