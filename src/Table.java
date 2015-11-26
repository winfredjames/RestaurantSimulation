/**
 * Created by winfredjames on 11/26/15.
 */
public class Table extends Thread {
    public static boolean[] tables;
    public int noOfTables;

    public Table(int noOfTables) {
        this.noOfTables = noOfTables;
    }

    public int find(){
        for(int i=0;i<noOfTables;i++){
            if(!tables[i]){
                return i;
            }
        }
        return -1;
    }

    public synchronized void setTable(int i){
            while(i)
            tables[i]=true;
    }

    public void releaseTable(){
        for(int i=0;i<noOfTables;i++){
            if(tables[i]){
                tables[i]=false;
            }
        }
    }

}
