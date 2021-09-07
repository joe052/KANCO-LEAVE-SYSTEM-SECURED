package LeaveSystem;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register implements ActionListener {
    Connection con;
    PreparedStatement pst;

    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JTextField field5;

    JFrame registry;
    ImageIcon klogo = new ImageIcon("kanco.png");
    JComboBox<String> comboBox;
    JButton reg;
    JButton ref;
    JButton back;

    String[] gender = { "Male", "Female", "Other" };

    Register() {
        registry = new JFrame();
        registry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registry.setTitle("USER REGISTRATION");
        registry.setSize(625, 450);
        registry.setIconImage(klogo.getImage());
        registry.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#123c69"));
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 600);

        comboBox = new JComboBox(gender);
        comboBox.setBounds(420, 95, 75, 25);
        comboBox.addActionListener(this);

        reg = new JButton("Register");
        ref = new JButton("Refresh");
        back = new JButton("Cancel");

        reg.setFocusable(false);
        reg.setBounds(420, 300, 100, 25);
        reg.setForeground(Color.decode("#123c69"));
        reg.setBackground(Color.green);
        reg.addActionListener(this);

        ref.setFocusable(false);
        ref.setBounds(225, 300, 100, 25);
        ref.addActionListener(this);

        back.setFocusable(false);
        back.setBounds(25, 300, 100, 25);
        back.setForeground(Color.red);
        back.addActionListener(this);

        JLabel label1 = new JLabel("FNAME");
        JLabel label2 = new JLabel("LNAME");
        JLabel label3 = new JLabel("GENDER");
        JLabel label4 = new JLabel("EMP.NO:  KA-");
        JLabel label5 = new JLabel("TEL.NO");
        JLabel label6 = new JLabel("CATEGORY");

        label1.setForeground(Color.decode("#df678c"));
        label1.setBackground(Color.decode("#123c69"));
        label1.setBounds(25, 25, 75, 25);
        label1.setOpaque(true);
        label1.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label2.setForeground(Color.decode("#df678c"));
        label2.setBackground(Color.decode("#123c69"));
        label2.setBounds(325, 25, 75, 25);
        label2.setOpaque(true);
        label2.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label3.setForeground(Color.decode("#df678c"));
        label3.setBackground(Color.decode("#123c69"));
        label3.setBounds(325, 95, 75, 25);
        label3.setOpaque(true);
        label3.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label4.setForeground(Color.decode("#df678c"));
        label4.setBackground(Color.decode("#123c69"));
        label4.setBounds(25, 95, 118, 25);
        label4.setOpaque(true);
        label4.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label5.setForeground(Color.decode("#df678c"));
        label5.setBackground(Color.decode("#123c69"));
        label5.setBounds(25, 175, 75, 25);
        label5.setOpaque(true);
        label5.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label6.setForeground(Color.decode("#df678c"));
        label6.setBackground(Color.decode("#123c69"));
        label6.setBounds(325, 175, 95, 25);
        label6.setOpaque(true);
        label6.setFont(new Font("MV Boli", Font.PLAIN, 16));

        field1 = new JTextField("");
        field2 = new JTextField("");
        field3 = new JTextField("");
        field4 = new JTextField("");
        field5 = new JTextField("");
        
        field1.setFont(new Font("Consolas", Font.PLAIN, 12));
        field1.setForeground(Color.black);
        field1.setBackground(Color.white);
        field1.setBounds(120, 25, 150, 25);

        field2.setFont(new Font("Consolas", Font.PLAIN, 12));
        field2.setForeground(Color.black);
        field2.setBackground(Color.white);
        field2.setBounds(420, 25, 150, 25);

        field3.setFont(new Font("Consolas", Font.PLAIN, 12));
        field3.setForeground(Color.black);
        field3.setBackground(Color.white);
        field3.setBounds(145, 95, 125, 25);

        field4.setFont(new Font("Consolas", Font.PLAIN, 12));
        field4.setForeground(Color.black);
        field4.setBackground(Color.white);
        field4.setBounds(120, 175, 150, 25);

        field5.setFont(new Font("Consolas", Font.PLAIN, 12));
        field5.setForeground(Color.black);
        field5.setBackground(Color.white);
        field5.setBounds(420, 175, 150, 25);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);

        panel.add(field1);
        panel.add(field2);
        panel.add(field3);
        panel.add(field4);
        panel.add(field5);

        panel.add(comboBox);
        panel.add(reg);
        panel.add(ref);
        panel.add(back);

        registry.add(panel);
        registry.setLocationRelativeTo(null);
        registry.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ref) {
            registry.dispose();
            new Register();
        } else if (e.getSource() == comboBox) {
            comboBox.getSelectedItem();
            // System.out.println("\n" + comboBox.getSelectedIndex());
        } else if (e.getSource() == back) {
            registry.dispose();
            new AdminFrame();
        } else if (e.getSource() == reg) {
            try {
                String query = "INSERT INTO `registration`(`fname`, `lname`, `empno`, `gender`, `tel`, `category`) VALUES (?, ?, ?, ?, ?, ?)";
                con = DriverManager.getConnection("jdbc:mysql://192.168.10.127:3306/leavemanagement", "root", "");
                pst = con.prepareStatement(query);
                pst.setString(1, field1.getText());
                pst.setString(2, field2.getText());
                pst.setString(3, field3.getText());
                pst.setString(4, comboBox.getSelectedItem().toString());
                pst.setString(5, field4.getText());
                pst.setString(6, field5.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "User registration successful!!");

                JOptionPane.showMessageDialog(null, "Please proceed to update user leave Info..It is Mandatory!!");

                registry.dispose();
                new LeaveUpdate();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
}
