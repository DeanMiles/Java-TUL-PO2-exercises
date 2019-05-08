import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class TaskHandler implements Runnable {

    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    long timeOfSleep;
    final String received;
    final DataInputStream dis;
    final DataOutputStream dos;
    final DataInputStream time;
    String toreturn;
    Date date = new Date();
    public TaskHandler(long timeOfSleep, String received, DataInputStream dis, DataOutputStream dos, DataInputStream time)
    {
        this.received= received;
        this.timeOfSleep = timeOfSleep;
        this.dis = dis;
        this.dos = dos;
        this.time = time;
    }

    @Override
    public void run()
    {
        if(timeOfSleep>0){                                                                                              //control of value of difference
        try {
            sleep(timeOfSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } }
        switch (received) {                                                                                             //validation of command
            case "Date" :
                toreturn = fordate.format(date);
                try {
                    dos.writeUTF(toreturn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Time" :
                toreturn = fortime.format(date);
                try {
                    dos.writeUTF(toreturn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Author" :
                toreturn = "Alan Franas";
                try {
                    dos.writeUTF(toreturn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "University" :
                toreturn = "Lodz University of Technology";
                try {
                    dos.writeUTF(toreturn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                try {
                    dos.writeUTF("Invalid input");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
