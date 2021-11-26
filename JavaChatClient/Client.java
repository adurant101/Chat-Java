package javachat2;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client extends Thread {

    OutputStream outputStream = null;
    ObjectOutputStream objectOutputStream = null;
    ObjectInputStream ois = null;
    Socket socket = null;
    String messages = "";
    intMessage client;

    public Client() {
    }

    //normal constructor to take username, password, host ip, and message
    public Client(String user, String pwd, String host, intMessage msg) {
        try {
            messages = connect(user, pwd, host);
            client = msg;

            msg.setServerMessage(messages);
        } catch (Exception e) {
            msg.setServerMessage(e.getMessage());
        }
    }

    //run client send and receive messages, login
    public void run() {
        for (;;) {
            //check for new messages
            String msg = checkForMessage();
            String last = "";
            System.out.println("."+msg+",");

            //checks if recieved end chat message, ends program (gui chat)
            last = msg.substring(msg.length() - 8);
            if(last.equals("exit598\n"))
            {
                JOptionPane.showMessageDialog(null,
                              "Ending program",
                              "Other user chose to end",
                              JOptionPane.WARNING_MESSAGE);
                System.out.println("Ending program, other user quit");
                System.exit(0);
            }

            //sends message to other user
            messages += msg;
            if (msg==null){
                client.setServerMessage("Server connection lost");
                break;
            }
            else
            {
                client.setServerMessage(msg);
            }
            messages = "";
        }
    }

    //get messages
    public String getMessages() {
        return messages;
    }

    //gets username and password and host ip, returns validity of login info
    public String connect(String userName, String password, String host) throws IOException {
        String cred = String.format("%s~%s", userName, password);
        int port = 2343;
        socket = new Socket(host, port);
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        try {
            objectOutputStream.writeObject(cred);
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            return message;
        } catch (Exception e) {
            System.exit(0);
        }
        return null;
    }

    //checks for message from other user
    public String checkForMessage() {
        try {
            String reply = (String) ois.readObject();
            return reply;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //send message to other user through server
    public String sendMessage(String msg) {
        try {
            objectOutputStream.writeObject(msg);
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
