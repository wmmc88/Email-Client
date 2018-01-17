/**
 * Created by Melvin Wang on 2016-05-03.
 */

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;

public class EMailClientComposeMessage implements ActionListener {
    private JFrame frame = null;
    private JPanel panel1 = null;
    private JPanel panel2 = null;
    private JPanel panel3 = null;
    private JLabel receiverLabel = null;
    private JTextField receiver = null;
    private JLabel subjectLabel = null;
    private JTextField subject = null;
    private JTextArea messageText = null;
    private JButton send = null;
    private JButton cancel = null;

    public EMailClientComposeMessage() {
        frame = new JFrame("New Message - ICS3U Bloor CI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(180, 160);
        frame.setResizable(false);

        //creates container for 3 panels
        Container contentPane = frame.getContentPane();
        BoxLayout contentPaneLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(contentPaneLayout);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);

        //set up first panel
        FlowLayout panelLayout = new FlowLayout(FlowLayout.CENTER);
        panel1.setLayout(panelLayout);

        receiverLabel = new JLabel("To");
        receiverLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        panel1.add(receiverLabel);
        receiver = new JTextField(Globals.RECEIVER_LEN);
        receiver.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        panel1.add(receiver);

        subjectLabel = new JLabel("Subject");
        subjectLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        panel1.add(subjectLabel);
        subject = new JTextField(47);
        subject.setFont(new Font("Comic Sans MS", Font.ITALIC + Font.BOLD, 20));
        panel1.add(subject);

        //set up second panel
        FlowLayout panel2Layout = new FlowLayout(FlowLayout.CENTER);
        panel1.setLayout(panel2Layout);

        messageText = new JTextArea(10, 69);
        messageText.setBorder(BorderFactory.createLineBorder(Color.blue));
        messageText.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        panel2.add(messageText);

        //set up third panel
        FlowLayout panel3Layout = new FlowLayout(FlowLayout.CENTER);
        panel3.setLayout(panel3Layout);

        send = new JButton("Send");
        send.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        panel3.add(send);
        panel3.add(cancel);

        send.addActionListener(this);
        cancel.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        Object buttonPressed = event.getSource();
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Comic Sans MS", Font.BOLD, 25)));

        if (buttonPressed == send) {
            String senderId = NetIO.myUserName();
            senderId = Utils.leftPad(senderId, Globals.SENDER_LEN, '0');
            if (senderId.length() == Globals.SENDER_LEN) {
                String receiverId = receiver.getText();
                Utils.leftPad(receiverId, Globals.RECEIVER_LEN, '0');
                if (receiverId.length() == Globals.RECEIVER_LEN && Utils.isANumber(receiverId)) {
                    String entireMessage = Globals.SEND_MESSAGE +
                            senderId +
                            receiverId +
                            Utils.leftPad(Globals.STR_NULL, Globals.DATE_TIME_LEN, '0') +
                            Globals.FIRST_RECORD_OF_MESSAGE +
                            subject.getText() +
                            Globals.END_OF_SUBJECT_MARKER +
                            messageText.getText();

                    int errorCode = NetIO.sendRequest(entireMessage, Globals.SERVER_IP_ADDRESS);
                    if (errorCode == Globals.NET_OK) {
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Message not delivered", "ICS Bloor CI", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.println("Send button was pressed by " + senderId + "\nMessage sent to " + receiverId);
                } else {
                    JOptionPane.showMessageDialog(null, "Receiver ID must be 9 digits", "ICS3U Bloor CI", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Sender ID must be 9 digits", "ICS3U Bloor CI", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Send Button was pressed");
        } else if (buttonPressed == cancel) {
            System.out.println("Cancel Button was pressed");
            frame.dispose();
        }
    }
}
