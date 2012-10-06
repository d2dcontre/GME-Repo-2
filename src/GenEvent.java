
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class GenEvent {
    public static void main(String[] args) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setLenient(true);
            PrintWriter out = new PrintWriter("create events.sql");
            // INSERT INTO EventData(dayOfMon,month,year,timeBlock,free,UserID)
            // VALUES(int,int,int,boolean,int);
            
            // Randomizes fun
            Random r = new Random();
            
            Hashtable h = new Hashtable();
            
            for(int i = 0; i < 100; i++) {
                // Randomize month
                int month = 9 + r.nextInt(3);
                gc.set(gc.MONTH, month); // 1 is january, 

                // Randomize day of month
                int dom = gc.getActualMaximum(gc.DAY_OF_MONTH);
                int day = 1 + r.nextInt(dom);

                // Randomize time
                int time = r.nextInt(28);
                boolean free = r.nextBoolean();
                
                if(h.get(day+" "+month+" "+time) == null) {
                    out.println("INSERT INTO EventData(dayOfMon,month,year,timeBlock,free,UserID)"
                        + " VALUES(" + day + "," + month + "," + 2012 + "," + time + "," + free + "," + 100651 + ")");
                    h.put("day+\" \"+month+\" \"+time", new Boolean(free) );
                }
            }
            //out.println("dom: " + dom + " " + gc.get(gc.MONTH) + " " + gc.get(gc.DAY_OF_MONTH) );
            
            // Save file
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
