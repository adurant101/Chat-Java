package javachat2;

import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.text.DefaultCaret;

public class ChatFrame extends javax.swing.JFrame implements intMessage{

    Client client = null;
    Timer timer = null;

    /**
     * Creates new form ChatFrame
     */
    public ChatFrame() {

        initComponents();
        userName.setText("");
        password.setText("");
        message.setText("");
        DefaultCaret caret = (DefaultCaret)textMessages.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        try {
            //uses a timer to constantly check for new messages and display on gui
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    String t = textMessages.getText() + "\n" + client.getMessages();
                    textMessages.setText(t);
                }
            });

        } catch (Exception e) {

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //set up gui and initialize everything, button, text, for gui
    @SuppressWarnings("unchecked")
     private void initComponents() {

        message = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnFetch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textMessages = new javax.swing.JTextArea();
        userName = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        textHost = new javax.swing.JTextField();
        lblHostName = new javax.swing.JLabel();
        lblHostName1 = new javax.swing.JLabel();
        lblHostName2 = new javax.swing.JLabel();
        info12 = new javax.swing.JLabel();
        info22 = new javax.swing.JLabel();
        info32 = new javax.swing.JLabel();
        info42 = new javax.swing.JLabel();
        info52 = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        message.setText("jTextField2");

        //send message to other user
        btnSend.setText("Send");
        btnSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatSend(evt);
            }
        });
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        //ends program for both users if end is pressed
        btnFetch.setText("End");
        btnFetch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String e = "exit598";
                e = client.sendMessage(e);
                System.exit(0);
            }
        });

        textMessages.setColumns(20);
        textMessages.setRows(5);
        jScrollPane1.setViewportView(textMessages);
        btnConnect.setText("Connect");
        btnConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdConnect(evt);
            }
        });

        textHost.setText("");
        lblHostName.setText("Host:");
        lblHostName1.setText("User:");
        lblHostName2.setText("Pass:");
        info12.setText("Please enter @{username} with the username "
                + "for the person to be");
        info22.setText("searched then just one word to be sent to start chat");
        info32.setText("Please do this after logged in successfully.");
        info42.setText("First text box below is for messages incoming "
                + "and");
        info52.setText("outgoing, second text box is for sending messages");
        //gui layout via grouplayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSend)
                        .addGap(79, 79, 79)
                        .addComponent(btnFetch)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHostName)
                            .addGap(45, 45, 45)
                            .addComponent(textHost, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHostName1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHostName2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnConnect))
                        .addComponent(info12, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(info22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(info32, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(info42, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(info52, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHostName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect)
                    .addComponent(lblHostName1)
                    .addComponent(lblHostName2))
                .addGap(20,20,20)
                .addComponent(info12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(info22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(info32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(info42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(info52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(btnFetch))
                .addContainerGap())
        );

        pack();
    }


    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    //send chat message via client
    private void chatSend(java.awt.event.MouseEvent evt) {
        String msg = message.getText();
        setServerMessage(userName.getText()+": "+msg);
        String t = client.sendMessage(msg);
        message.setText("");
    }

    //checks new messages from other user
    private void checkMessages() {
        String t = textMessages.getText() + "\n" + client.getMessages();
        textMessages.setText(t);
    }

    //display messages in gui
    StringBuilder messages = new StringBuilder("");
    public void setServerMessage(String msg)
    {
        messages.append(msg);
        String t = textMessages.getText() + "\n" + msg;
        textMessages.setText(t);
    }

    //check messages from other users
    private void checkMessages(java.awt.event.MouseEvent evt) {
        String msg = client.getMessages();
        String t = textMessages.getText() + "\n" + msg;
        textMessages.setText(t);
    }

    //connect user login
    private void cmdConnect(java.awt.event.MouseEvent evt) {
        client = new Client(userName.getText(), password.getText(),textHost.getText() , this );
        client.start();
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnFetch;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHostName;
    private javax.swing.JTextField message;
    private javax.swing.JTextField password;
    private javax.swing.JTextField textHost;
    private javax.swing.JTextArea textMessages;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel lblHostName1;
    private javax.swing.JLabel lblHostName2;
    private javax.swing.JLabel info12;
    private javax.swing.JLabel info22;
    private javax.swing.JLabel info32;
    private javax.swing.JLabel info42;
    private javax.swing.JLabel info52;

    // End of variables declaration
}
