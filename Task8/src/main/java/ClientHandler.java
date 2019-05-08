import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final DataInputStream time;
    final Socket s;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, DataInputStream time)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.time = time;
    }

    @Override
    public void run()
    {
        String received;
        long diffInMillis = 0;
        String data;
        Date dateTask = new Date();
        boolean dateFormat = true;                                                                                      //initialization of boolean flag which control acceptance of data
        while (true)
            try {
                if (diffInMillis < 0)
                    sleep(100);
                do {
                    dos.writeUTF("What do you want?[Date | Time | Author | University]..\n" +
                            "Type Exit to terminate connection.");
                    received = dis.readUTF();
                } while (!received.equals("Date") && !received.equals("Time") && !received.equals("Author") && !received.equals("University"));//first answer from client

                do {
                    dateFormat = true;
                    dos.writeUTF("What time do you want to do this?[yyyy-MM-dd HH:mm:ss]");
                    data = time.readUTF();                                                                               //second respond from client
                        try {
                            dateTask = parseDate(data, "yyyy-MM-dd HH:mm:ss");
                        } catch (ParseException parseEx) {
                            //parseEx.printStackTrace();                                                                //parsing exception track
                            System.out.println("Wrong date pattern");
                            dateFormat = false;
                        }
                } while (!dateFormat);

                if (received.equals("Exit")) {                                                                          //closing connection after client command
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                System.out.println("Message: " + received + " from user: " + s);

                Date date = new Date();
                diffInMillis = dateTask.getTime() - date.getTime();                                                     //counting the delay
                System.out.println(diffInMillis);
                Thread t = new Thread(new TaskHandler(diffInMillis, received, dis, dos, time));                         //Begin thread which control output to client and the time
                t.start();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("Wrong format date");
                e.printStackTrace();
            }
        try                                                                                                             //closing connections
        {
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private Date parseDate(String date, String format) throws ParseException {                                          //conventer String date to Date
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
}