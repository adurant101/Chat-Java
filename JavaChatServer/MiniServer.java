package javachatserver;
import java.io.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.ObjectOutputStream;

public class MiniServer extends Thread {

    private Socket socket = null;
    private MiniServer chat = null;
    private boolean auth = false;
    private User user = null;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectOutputStream chatOutputStream;
    private static File file1;

    //takes socket
    public MiniServer(Socket socket)
    {
        super("MiniServer");
        this.socket = socket;
    }

    //return socket and user
    public User getUser(){return user;}
    public Socket getSocket(){return socket;}

    //attempt to start chat with other user
    private void requestChat(String msg)
    {
        try {
            String[]parts=msg.substring(1).split(" ");
            chat = MainServer.requestChat(parts[0], parts[1], this);
            if (chat==null)return;
            objectOutputStream.writeObject(String.format ("sent to %s", chat.getUser().getUsername()));
        }catch (Exception e)
        {
            System.err.println(e);
        }
    }

    //start initial search other user to start chat session sending objects
    public int message (MiniServer m, String message)
    {
        try {
            chat = m;
            System.out.printf ("\nMini initial search Message from %s to %s: %s\n", m.getUser().getUsername(), user.getUsername(), message);
            FileWriter writeTo = new FileWriter("serverLog.txt",true);
            writeTo.write("\nMini initial search Message from " + m.getUser().getUsername() + " to " + user.getUsername() + ": " + message + "\n");
            writeTo.close();
            objectOutputStream.writeObject(String.format ("%s: %s\n", m.getUser().getUsername(), message));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    //reply messages back and forth users in client to client through server and objects sending
    public int reply  (MiniServer m, String message)
    {
        try {
            System.out.printf ("Reply Message from %s to %s: %s\n", m.getUser().getUsername(), user.getUsername(), message);
            FileWriter writeTo = new FileWriter("serverLog.txt",true);
            writeTo.write("Reply Message from " + m.getUser().getUsername() + " to " + user.getUsername() + ": " + message + "\n");
            writeTo.close();
           objectOutputStream.writeObject(String.format ("%s: %s\n", m.getUser().getUsername(), message));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    //run miniserver send and recieve messages between client users
    public void run() {
        System.out.println("ServerSocket awaiting connections...");
        try {
            System.out.println("Connection from " + socket + "!");
            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            //get username and pass
            String cred = (String) objectInputStream.readObject();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String[] t = cred.split("~");
            user = MainServer.getUser(t[0], t[1]);
            //check if username and pass are valid
            if (user==null) {
                System.out.println("Not Authorized!!!!");
                objectOutputStream.writeObject("\n\n****Invalid user login***\n\n");
                return;
            }
            objectOutputStream.writeObject("Logged in successfully");
            auth = true;

            // create a ObjectInputStream so we can read data from it.
            for (;;) {
                // read the list of messages from the socket
                FileWriter writeTo = new FileWriter("serverLog.txt",true);
                String msg = (String)objectInputStream.readObject();
                System.out.println ("received message: "+msg);
                writeTo = new FileWriter("serverLog.txt",true);
                writeTo.write("received message: " + msg + "\n");
                //if user wants to end chat
                if(msg.equals("exit598"))
                {
                    writeTo.write("exit598 means sender chose to end chat for both parties\n");
                }
                //checks for search other user to chat with
                if (msg.charAt(0)=='@'){
                    System.out.println("sent request to start chat");
                    writeTo.write("sent request to start chat\n");
                    requestChat(msg);
                } else if (msg.charAt(0)=='$')
                {
                    objectOutputStream.writeObject("");
                }
                else if(chat==null) {
                    //not connected to other user
                    System.out.println(msg);
                    System.out.println("Mini.Reply to server: ");
                    objectOutputStream.writeObject(msg);
                }else {
                    //send message to other user
                    System.out.println ("mini sending message to "+chat.getUser().getUsername());
                    writeTo.write("mini sending message to "+chat.getUser().getUsername() + "\n");
                    chat.reply(this, msg);
                }
                writeTo.close();
            }
        } catch (Exception e) {
            System.err.println("RUN-EX: "+e);
        } finally {
            System.out.println("Closing sockets.");
        }
    }

}
