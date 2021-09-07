package LeaveSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomeFrame implements ActionListener {

  IDandPass idpass;

  ImageIcon klogo = new ImageIcon("kanco.png");
  ImageIcon vlogo = new ImageIcon("view.png");
  ImageIcon alogo = new ImageIcon("admin.png");
  ImageIcon llogo = new ImageIcon("leave.png");
  ImageIcon out = new ImageIcon("logout.png");

  JFrame frame;

  JButton view;
  JButton admin;
  JButton leaves;
  JButton back;
  JButton logout;
  JLabel logo;

  HomeFrame() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("KANCO LEAVE MANAGEMENT SYSTEM");
    frame.setSize(800, 600);
    frame.setIconImage(klogo.getImage());
    frame.setResizable(false);

    view = new JButton("View");
    admin = new JButton("Admin");
    leaves = new JButton("Leave.Mgr");
    logout = new JButton("Log Out");

    view.setIcon(vlogo);
    view.setVerticalTextPosition(JButton.BOTTOM);
    view.setHorizontalTextPosition(JButton.CENTER);

    admin.setIcon(alogo);
    admin.setVerticalTextPosition(JButton.BOTTOM);
    admin.setHorizontalTextPosition(JButton.CENTER);

    leaves.setIcon(llogo);
    leaves.setVerticalTextPosition(JButton.BOTTOM);
    leaves.setHorizontalTextPosition(JButton.CENTER);

    logout.setIcon(out);
    logout.setVerticalTextPosition(JButton.BOTTOM);
    logout.setHorizontalTextPosition(JButton.CENTER);
    logout.setBackground(Color.decode("#97aabd"));
    logout.setForeground(Color.decode("#670803"));
    logout.setFont(new Font("Play", Font.BOLD, 14));

    view.setBounds(90, 140, 125, 85);
    admin.setBounds(315, 140, 125, 85);
    leaves.setBounds(540, 140, 125, 85);
    logout.setBounds(685, 10, 95, 80);

    view.setFocusable(false);
    admin.setFocusable(false);
    leaves.setFocusable(false);
    logout.setFocusable(false);

    leaves.addActionListener(this);
    admin.addActionListener(this);
    view.addActionListener(this);
    logout.addActionListener(this);

    JPanel panel = new JPanel();
    panel.setBackground(Color.decode("#97aabd"));
    //panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 150));
    panel.setLayout(null);
    panel.setBounds(0, 0, 500, 500);
    panel.add(view);
    panel.add(admin);
    panel.add(leaves);
    panel.add(logout);

    frame.add(panel);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == leaves) {
      frame.dispose();
      new LeaveFrame();
    } else if (e.getSource() == admin) {
      frame.dispose();
      new AdminFrame();
    } else if (e.getSource() == view) {
      frame.dispose();
      new ViewFrame();
    } else if (e.getSource() == logout) {
      int response = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to Log out?",
        "Confirm Log Out",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.ERROR_MESSAGE
      );
      if (response == 0) {
        frame.dispose();
        idpass = new IDandPass();
        new LoginFrame(idpass.getLogininfo());
      }
    }
  }
}
