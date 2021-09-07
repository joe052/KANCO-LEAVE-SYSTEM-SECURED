package LeaveSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ViewFrame implements ActionListener {
    ImageIcon klogo = new ImageIcon("kanco.png");
    ImageIcon ulogo = new ImageIcon("user.png");
    ImageIcon ilogo = new ImageIcon("info.png");
    ImageIcon hlogo = new ImageIcon("home.png");

    JFrame frame;

    JButton user;
    JButton leave;
    JButton home;
    JButton back;

    ViewFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("VIEW INFORMATION");
        frame.setSize(700, 500);
        frame.setIconImage(klogo.getImage());
        frame.setResizable(false);

        user = new JButton("Users");
        leave = new JButton("Leave Info");
        home = new JButton("Home");

        user.setIcon(ulogo);
        user.setVerticalTextPosition(JButton.BOTTOM);
        user.setHorizontalTextPosition(JButton.CENTER);

        leave.setIcon(ilogo);
        leave.setVerticalTextPosition(JButton.BOTTOM);
        leave.setHorizontalTextPosition(JButton.CENTER);

        home.setIcon(hlogo);
        home.setVerticalTextPosition(JButton.BOTTOM);
        home.setHorizontalTextPosition(JButton.CENTER);

        user.setBounds(50, 60, 150, 50);
        leave.setBounds(250, 60, 150, 50);
        home.setBounds(450, 60, 150, 50);

        user.setFocusable(false);
        leave.setFocusable(false);
        home.setFocusable(false);

        home.addActionListener(this);
        leave.addActionListener(this);
        user.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#314455"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        panel.setBounds(0, 0, 500, 500);
        panel.add(user);
        panel.add(leave);
        panel.add(home);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            frame.dispose();
             new HomeFrame();
        }else if(e.getSource()== leave){
            frame.dispose();
            new ViewLeaveInfo();
        }else if(e.getSource() == user){
            frame.dispose();
            new ViewUserInfo();
        }

    }

}