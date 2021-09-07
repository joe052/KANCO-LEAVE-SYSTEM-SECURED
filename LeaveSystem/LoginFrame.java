package LeaveSystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class LoginFrame implements ActionListener, KeyListener {

  HashMap<String, String> logininfo = new HashMap<String, String>();
  ImageIcon kanco = new ImageIcon("orig.png");
  ImageIcon klogo = new ImageIcon("kanco.png");
  JFrame log;
  MyPanel panel;
  private BufferedImage img;

  JButton loginButton;
  JButton resetButton;
  JTextField idField;
  JPasswordField passField;
  JLabel idLabel;
  JLabel passLabel;
  JLabel messLabel;
  JLabel logo;

  public LoginFrame(HashMap<String, String> loginInfoOrig) {
    logininfo = loginInfoOrig;

    log = new JFrame("LEAVE SYSTEM LOGIN");
    log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    log.setIconImage(klogo.getImage());
    log.setSize(650, 450);

    panel = new MyPanel();
    panel.setLayout(null);

    try {
      img = ImageIO.read(new File("white.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    //log.setContentPane(panel);

    loginButton = new JButton("Login");
    resetButton = new JButton("Reset");
    idField = new JTextField();
    passField = new JPasswordField();
    idLabel = new JLabel("UserID: ");
    passLabel = new JLabel("Password: ");
    messLabel = new JLabel("Login ");

    logo = new JLabel("", kanco, JLabel.CENTER);
    logo.setBounds(140, 20, 300, 75);

    idLabel.setBounds(100, 115, 75, 25);
    idLabel.setFont(new Font("Teko", Font.BOLD, 12));

    passLabel.setBounds(100, 170, 75, 25);
    passLabel.setFont(new Font("Teko", Font.BOLD, 12));

    messLabel.setBounds(275, 350, 250, 35);
    messLabel.setFont(new Font("Comfortaa", Font.ITALIC, 25));
    messLabel.setForeground(Color.white);

    idField.setBounds(175, 115, 150, 25);
    idField.setFont(new Font("Play", Font.PLAIN, 12));
    idField.addKeyListener(this);

    passField.setBounds(175, 170, 150, 25);
    passField.addKeyListener(this);

    loginButton.setBounds(100, 250, 100, 25);
    loginButton.setFocusable(false);
    loginButton.setBackground(Color.decode("#4f914b"));
    loginButton.setForeground(Color.white);
    passLabel.setFont(new Font("Play", Font.PLAIN, 12));
    loginButton.addActionListener(this);

    resetButton.setBounds(250, 250, 100, 25);
    passLabel.setFont(new Font("Play", Font.PLAIN, 12));
    resetButton.setFocusable(false);
    resetButton.addActionListener(this);

    panel.add(idLabel);
    panel.add(passLabel);
    panel.add(messLabel);
    panel.add(idField);
    panel.add(passField);

    panel.add(loginButton);
    panel.add(resetButton);

    panel.add(logo);

    log.add(panel);
    log.setLocationRelativeTo(null);
    log.setVisible(true);
  }

  private class MyPanel extends JPanel {

    protected void paintComponent(Graphics g) {
      super.paintComponents(g);
      g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == resetButton) {
      idField.setText("");
      passField.setText("");
    } else if (e.getSource() == loginButton) {
      String userID = idField.getText();
      String password = String.valueOf(passField.getPassword());

      if (logininfo.containsKey(userID)) {
        if (logininfo.get(userID).equals(password)) {
          messLabel.setForeground(Color.green);
          messLabel.setText("Login successful!");
          JOptionPane.showMessageDialog(
            null,
            "Welcome to the KANCO Leave Management System!",
            "WELCOME",
            JOptionPane.INFORMATION_MESSAGE
          );
          log.dispose();
          new HomeFrame();
        } else {
          messLabel.setForeground(Color.red);
          messLabel.setText("Wrong password!!");
          JOptionPane.showMessageDialog(
            null,
            "Wrong Password!! Please input the correct password.",
            "ACCESS DENIED!!",
            JOptionPane.WARNING_MESSAGE
          );
        }
      } else {
        messLabel.setForeground(Color.red);
        messLabel.setText("User invalid!!");
        JOptionPane.showMessageDialog(
          null,
          "Invalid Username!! Please input the valid username.",
          "ACCESS DENIED!!",
          JOptionPane.WARNING_MESSAGE
        );
      }
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      //SwingUtilities.getWindowAncestor(e.getComponent());
      String userID = idField.getText();
      String password = String.valueOf(passField.getPassword());

      if (logininfo.containsKey(userID)) {
        if (logininfo.get(userID).equals(password)) {
          messLabel.setForeground(Color.green);
          messLabel.setText("Login successful!");
          JOptionPane.showMessageDialog(
            null,
            "Welcome to the KANCO Leave Management System!",
            "WELCOME",
            JOptionPane.INFORMATION_MESSAGE
          );
          log.dispose();
          new HomeFrame();
        } else {
          messLabel.setForeground(Color.red);
          messLabel.setText("Wrong password!!");
          JOptionPane.showMessageDialog(
            null,
            "Wrong Password!! Please input the correct password.",
            "ACCESS DENIED!!",
            JOptionPane.WARNING_MESSAGE
          );
        }
      } else {
        messLabel.setForeground(Color.red);
        messLabel.setText("User invalid!!");
        JOptionPane.showMessageDialog(
          null,
          "Invalid Username!! Please input the valid username.",
          "ACCESS DENIED!!",
          JOptionPane.WARNING_MESSAGE
        );
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }
}
