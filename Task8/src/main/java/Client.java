import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Scanner scn = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("localhost");
        Socket s = new Socket(ip, 5036);                                                                           //example port
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Thread sendMessage = new Thread(() -> {                                                                         //thread which services sent messages
            while (true) {
                String msg = scn.nextLine();
                if(msg.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Connection closed");
                    break;
                }
                try {
                    dos.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readMessage = new Thread(() -> {                                                                         // readMessage thread

            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                } catch (IOException e) {
                    //e.printStackTrace();                                                                              //exception Stack Trace in case of disconnect Server
                    System.out.println("Lost connection\n" + "Program has ended for this client");
                    break;
                }
            }
        });

        try
        {
            sendMessage.start();
            readMessage.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
