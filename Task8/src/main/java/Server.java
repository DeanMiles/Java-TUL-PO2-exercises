import java.io.*;
import java.net.*;


public class Server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(5036);

        while (true)
        {
            Socket s = null;
            try
            {
                s = ss.accept();
                System.out.println("A new client is connected : " + s);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                DataInputStream time = new DataInputStream(s.getInputStream());
                System.out.println("Assigning new thread for this client");

                Thread t = new ClientHandler(s, dis, dos, time);                                                        //new Thread for every client
                t.start();
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}


