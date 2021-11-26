package javachatserver;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainServer {

    private static List<MiniServer> users = new ArrayList<MiniServer>();
    private static File file1;

    //to search for user to chat with
    public static MiniServer requestChat(String user, String message, MiniServer sender)
    {
        System.out.printf ("Receiptent: {%s}\n",user);
        String s = "Receiptent: " + user + "\n";
        try
        {
            FileWriter writeTo = new FileWriter("serverLog.txt",true);
            writeTo.write(s);

            //checks if valid username and password
            for (MiniServer ms : users)
            {
                if (user.equals(ms.getUser().getUsername()))
                {
                    System.out.println ("Found: "+ms.getUser().getUsername() + " Sending Message");
                    writeTo.write("Found: "+ms.getUser().getUsername() + " Sending Message\n");
                    ms.message(sender, message);
                    return ms;
                }
            }
            writeTo.close();

        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        System.out.printf ("Not Found {%}\n", user);
        return null;
    }

    //run mainserver
    public static void main(String[] args) throws IOException {
        loadUsers();
               try{
       file1 = new File("serverLog.txt");
       if(file1.createNewFile())
       {
           System.out.println("File created, log will be saved to serverLog.txt");
       }
        FileWriter writeTo = new FileWriter("serverLog.txt",false);
        writeTo.write("");
        writeTo.close();
        }catch(IOException e)
        {
            System.out.println(e);
        }
        System.out.println("Main Server running...");
        ServerSocket serverSocket = null;
        boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(2343);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 2343");
        }
        while(listeningSocket){
            //gets incoming connections from client
            Socket clientSocket = serverSocket.accept();
            MiniServer mini = new MiniServer(clientSocket);
            users.add(mini);
            mini.start();
        }
        serverSocket.close();
    }

    //list of users in server for client to login
    private static List<User>userList = new ArrayList<User>();
    private static void loadUsers()
    {
        userList.add(new User("bob", "123"));
        userList.add(new User("abc", "123"));
        userList.add(new User("jeff", "123"));
        userList.add(new User("andre", "123"));
    }

    //check if user is valid user
    public static User getUser (String un, String p)
    {
        System.out.printf("Get User: %s/%s\n", un,p);
        for (User u : userList)
        {
            //checks for valid username and password
            if (u.isValid(un, p))
            {
                System.out.println("User " + un + " is valid, logging in");
                try
                {
                    FileWriter writeTo = new FileWriter("serverLog.txt",true);
                    writeTo.write("User " + un + " is valid, logging in\n");
                    writeTo.close();
                }
                catch(IOException e)
                {

                }
               return u;
            }
        }
        return null;
    }
}
