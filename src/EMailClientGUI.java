/**
 * Created by Melvin Wang on 2016-05-17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EMailClientGUI implements ActionListener {
    JFrame frame = null;
    JPanel panel1 = null;
    JPanel panel2 = null;
    JPanel panel3 = null;
    EMailClientPane EMailClientPane = null;
    JLabel titles = null;

    JButton compose = null;
    JButton getMail = null;
    JButton delete = null;
    JButton inBox = null;
    JButton outBox = null;

    public EMailClientGUI() {
        frame = new JFrame("Email Client - ICS3U");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(100, 50);
        frame.setResizable(false);

        Container contentPane = frame.getContentPane();

        BoxLayout contentPaneLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(contentPaneLayout);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);

        FlowLayout panel1Layout = new FlowLayout(FlowLayout.LEFT);
        panel1.setLayout(panel1Layout);
        titles = new JLabel(" From          Date Received          Subject");
        titles.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        panel1.add(titles);

        FlowLayout panel2Layout = new FlowLayout(FlowLayout.LEFT);
        panel2.setLayout(panel2Layout);
        EMailClientPane = new EMailClientPane();
        panel2.add(EMailClientPane.getSplitPane());

        FlowLayout panel3Layout = new FlowLayout(FlowLayout.CENTER);
        panel3.setLayout(panel3Layout);
        compose = new JButton("Compose");
        compose.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        getMail = new JButton("Get Mail");
        getMail.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        delete = new JButton("Delete");
        delete.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        inBox = new JButton("InBox");
        inBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        outBox = new JButton("OutBox");
        outBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        panel3.add(compose);
        panel3.add(getMail);
        panel3.add(delete);
        panel3.add(inBox);
        panel3.add(outBox);
        compose.addActionListener(this);
        getMail.addActionListener(this);
        delete.addActionListener(this);
        inBox.addActionListener(this);
        outBox.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        Object buttonPressed = event.getSource();
        if(buttonPressed == compose){
            EMailClientComposeMessage compose = new EMailClientComposeMessage();
        }
    }
}
