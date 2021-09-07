package LeaveSystem;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import java.sql.DriverManager;

import java.awt.*;

public class LeaveInfo implements ActionListener {

    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;

    ImageIcon klogo = new ImageIcon("kanco.png");

    JTextField field;

    JButton ok, cancel, refresh;

    SpinnerModel value1 = new SpinnerNumberModel(21, 0, 30, 1);
    SpinnerModel value2 = new SpinnerNumberModel(90, 0, 100, 1); 
    SpinnerModel value3 = new SpinnerNumberModel(14, 0, 14, 1);
    SpinnerModel value4 = new SpinnerNumberModel(7, 0, 10, 1);
    SpinnerModel value5 = new SpinnerNumberModel(1, 0, 5, 1);
    JSpinner spinner1, spinner2, spinner3, spinner4, spinner5;

    public LeaveInfo() {
        JFrame lFrame = new JFrame();
        lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lFrame.setResizable(false);
        lFrame.setLayout(null);
        lFrame.setSize(625, 450);
        lFrame.setTitle("Leave Information");
        lFrame.setIconImage(klogo.getImage());

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#49cff9"));
        // panel.setLayout(new FlowLayout(FlowLayout.LEADING, 250, 50));
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 800);

        JLabel label1 = new JLabel("Annual Leave");
        JLabel label2 = new JLabel("Compassionate 1");
        JLabel label3 = new JLabel("Compassionate 2");
        JLabel label4 = new JLabel("Maternity Leave");
        JLabel label5 = new JLabel("Paternity Leave");
        JLabel label6 = new JLabel("Year");

        field = new JTextField();
        field.setFont(new Font("Consolas", Font.PLAIN, 12));
        field.setForeground(Color.black);
        field.setBackground(Color.white);
        field.setBounds(495, 175, 75, 25);
        // field.set

        ok = new JButton("Ok");
        cancel = new JButton("Cancel");
        refresh = new JButton("Refresh");

        ok.setFocusable(false);
        ok.setBounds(420, 300, 100, 25);
        ok.addActionListener(this);

        refresh.setFocusable(false);
        refresh.setBounds(225, 300, 100, 25);
        refresh.addActionListener(this);

        cancel.setFocusable(false);
        cancel.setBounds(25, 300, 100, 25);
        cancel.addActionListener(this);

        spinner1 = new JSpinner(value1);
        spinner2 = new JSpinner(value2);
        spinner3 = new JSpinner(value3);
        spinner4 = new JSpinner(value4);
        spinner5 = new JSpinner(value5);
        // Border border = BorderFactory.createEtchedBorder();

        spinner1.setBounds(200, 25, 50, 25);
        spinner1.setEditor(new JSpinner.DefaultEditor(spinner1));
        spinner1.setFocusable(false);

        spinner2.setBounds(200, 95, 50, 25);
        spinner2.setEditor(new JSpinner.DefaultEditor(spinner2));
        spinner2.setFocusable(false);

        spinner3.setBounds(200, 175, 50, 25);
        spinner3.setEditor(new JSpinner.DefaultEditor(spinner3));
        spinner3.setFocusable(false);

        spinner4.setBounds(495, 25, 50, 25);
        spinner4.setEditor(new JSpinner.DefaultEditor(spinner4));
        spinner4.setFocusable(false);

        spinner5.setBounds(495, 95, 50, 25);
        spinner5.setEditor(new JSpinner.DefaultEditor(spinner5));
        spinner5.setFocusable(false);

        label1.setForeground(Color.red);
        label1.setBackground(Color.BLACK);
        label1.setBounds(25, 25, 140, 25);
        label1.setOpaque(true);
        label1.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label2.setForeground(Color.red);
        label2.setBackground(Color.BLACK);
        label2.setBounds(325, 25, 140, 25);
        label2.setOpaque(true);
        label2.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label3.setForeground(Color.red);
        label3.setBackground(Color.BLACK);
        label3.setBounds(325, 95, 140, 25);
        label3.setOpaque(true);
        label3.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label4.setForeground(Color.red);
        label4.setBackground(Color.BLACK);
        label4.setBounds(25, 95, 140, 25);
        label4.setOpaque(true);
        label4.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label5.setForeground(Color.red);
        label5.setBackground(Color.BLACK);
        label5.setBounds(25, 175, 140, 25);
        label5.setOpaque(true);
        label5.setFont(new Font("MV Boli", Font.PLAIN, 16));

        label6.setForeground(Color.red);
        label6.setBackground(Color.BLACK);
        label6.setBounds(325, 175, 140, 25);
        label6.setOpaque(true);
        label6.setFont(new Font("MV Boli", Font.PLAIN, 16));

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);

        panel.add(spinner1);
        panel.add(spinner2);
        panel.add(spinner3);
        panel.add(spinner4);
        panel.add(spinner5);
        panel.add(field);

        panel.add(ok);
        panel.add(cancel);
        panel.add(refresh);

        lFrame.add(panel);
        lFrame.setLocationRelativeTo(null);
        lFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            String annual = spinner1.getValue().toString();
            String mat = spinner2.getValue().toString();
            String pat = spinner3.getValue().toString();
            String comp1 = spinner4.getValue().toString();
            String comp2 = spinner5.getValue().toString();
            String year = field.getText();

            // Connection();
            try {
                String query = "SELECT empno FROM `registration` WHERE 1";
                con = DriverManager.getConnection("jdbc:mysql://192.168.10.127:3306/leavemanagement", "root", "");
                pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                String empvalue;
                while (rs.next()) {
                    empvalue = rs.getString("empno");

                    pst1 = con.prepareStatement("INSERT INTO `leaveinfo`( `empno`, `annual`, `maternity`, `paternity`, `compassionate1`, `compassionate2`, `year`) VALUES (?,?,?,?,?,?,?)");
                    pst1.setString(1, empvalue);
                    pst1.setString(2, annual);
                    pst1.setString(3, mat);
                    pst1.setString(4, pat);
                    pst1.setString(5, comp1);
                    pst1.setString(6, comp2);
                    pst1.setString(7, year);
                    pst1.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Leave Information Added Successfully");
                /*
                 * pst = con.prepareStatement("SELECT empno FROM `registration` WHERE 1");
                 * Resultset rs = pst.executeQuery();
                 * 
                 * String empvalue; while (rs.next()) {
                 * 
                 * }
                 */
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex); 
            }

        }

    }

}
