package LeaveSystem;

//import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

import java.awt.*;
import java.awt.event.*;
//import java.lang.System.Logger;
import java.sql.*;
import javax.swing.*;

public class ApplyFrame extends JFrame implements ActionListener {

  SpinnerNumberModel value1 = new SpinnerNumberModel(1, 1, 30, 1);
  SpinnerNumberModel value2 = new SpinnerNumberModel(90, 1, 100, 1);
  SpinnerNumberModel value3 = new SpinnerNumberModel(1, 1, 14, 1);
  SpinnerNumberModel value4 = new SpinnerNumberModel(1, 1, 10, 1);
  SpinnerNumberModel value5 = new SpinnerNumberModel(1, 1, 5, 1);

  JSpinner spinner;

  Connection con;
  PreparedStatement pst;
  PreparedStatement pst2;
  PreparedStatement pst3;
  PreparedStatement pst4;
  PreparedStatement pst5;

  ImageIcon klogo = new ImageIcon("kanco.png");

  JComboBox box;

  JTextField field1;
  JTextField field2;
  JTextField field3;
  JTextField field4;

  JFrame apply;

  JPanel app;
  JPanel app2;

  JRadioButton rannual;
  JRadioButton rpat;
  JRadioButton rmat;
  JRadioButton rcomp1;
  JRadioButton rcomp2;

  JButton ok;
  JButton cancel;
  JButton submit;

  Double annual = 0.0;
  int maternity = 0;
  int paternity = 0;
  int comp1 = 0;
  int comp2 = 0;

  public void connection() {
    try {
      // Class.forName("com.mysql.jdbc.Driver");
      con =
        DriverManager.getConnection(
          "jdbc:mysql://192.168.10.127:3306/leavemanagement",
          "root",
          ""
        );
    } catch (Exception ex) {
      // Logger.getLogger(ApplyFrame.Class.getName()).log(Level.SEVERE,null,ex);
    }
  }

  public ApplyFrame() {
    apply = new JFrame();
    apply.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    apply.setTitle("USER APPLICATION INFORMATION");
    apply.setIconImage(klogo.getImage());
    apply.setSize(700, 500);
    apply.setLayout(null);
    apply.setResizable(false);
    apply.getContentPane().setBackground(Color.decode("#314455"));

    app = new JPanel();
    app.setBackground(Color.decode("#314455"));
    app.setLayout(null);
    app.setBounds(0, 0, 350, 1000);

    app2 = new JPanel();
    app2.setBackground(Color.decode("#644e5b"));
    app2.setLayout(null);
    app2.setBounds(350, 0, 1200, 1000);

    JLabel label1 = new JLabel("Emp No.");
    JLabel label2 = new JLabel("F.name");
    JLabel label3 = new JLabel("L.name");
    JLabel label4 = new JLabel("Gender");
    JLabel label5 = new JLabel("Category");
    JLabel label6 = new JLabel("Select Leave");
    JLabel label7 = new JLabel("Days");

    label1.setForeground(Color.decode("#009dc4"));
    label1.setBackground(Color.decode("#314455"));
    label1.setBounds(25, 25, 80, 25);
    label1.setOpaque(true);
    label1.setFont(new Font("MV Boli", Font.BOLD, 16));

    label2.setForeground(Color.decode("#009dc4"));
    label2.setBackground(Color.decode("#314455"));
    label2.setBounds(25, 80, 80, 25);
    label2.setOpaque(true);
    label2.setFont(new Font("MV Boli", Font.BOLD, 16));

    label3.setForeground(Color.decode("#009dc4"));
    label3.setBackground(Color.decode("#314455"));
    label3.setBounds(25, 135, 80, 25);
    label3.setOpaque(true);
    label3.setFont(new Font("MV Boli", Font.BOLD, 16));

    label4.setForeground(Color.decode("#009dc4"));
    label4.setBackground(Color.decode("#314455"));
    label4.setBounds(25, 190, 80, 25);
    label4.setOpaque(true);
    label4.setFont(new Font("MV Boli", Font.BOLD, 16));

    label5.setForeground(Color.decode("#009dc4"));
    label5.setBackground(Color.decode("#314455"));
    label5.setBounds(25, 245, 80, 25);
    label5.setOpaque(true);
    label5.setFont(new Font("MV Boli", Font.BOLD, 16));

    label6.setForeground(Color.decode("#060d19"));
    label6.setBackground(Color.decode("#644e5b"));
    label6.setBounds(95, 20, 100, 25);
    label6.setOpaque(true);
    label6.setFont(new Font("Changa", Font.BOLD, 16));

    label7.setForeground(Color.black);
    label7.setBackground(Color.decode("#644e5b"));
    label7.setBounds(25, 280, 50, 25);
    label7.setOpaque(true);
    label7.setFont(new Font("Changa", Font.BOLD, 16));

    box = new JComboBox();
    box.setBounds(125, 25, 80, 25);
    box.getSelectedItem();
    box.addActionListener(this);

    field1 = new JTextField("");
    field2 = new JTextField("");
    field3 = new JTextField("");
    field4 = new JTextField("");

    field1.setFont(new Font("Consolas", Font.PLAIN, 12));
    field1.setForeground(Color.black);
    field1.setBackground(Color.white);
    field1.setBounds(125, 80, 150, 25);
    field1.setEditable(false);

    field2.setFont(new Font("Consolas", Font.PLAIN, 12));
    field2.setForeground(Color.black);
    field2.setBackground(Color.white);
    field2.setBounds(125, 135, 150, 25);
    field2.setEditable(false);

    field3.setFont(new Font("Consolas", Font.PLAIN, 12));
    field3.setForeground(Color.black);
    field3.setBackground(Color.white);
    field3.setBounds(125, 190, 150, 25);
    field3.setEditable(false);

    field4.setFont(new Font("Consolas", Font.PLAIN, 12));
    field4.setForeground(Color.black);
    field4.setBackground(Color.white);
    field4.setBounds(125, 245, 150, 25);
    field4.setEditable(false);

    spinner = new JSpinner(value1);

    spinner.setBounds(95, 280, 50, 25);
    spinner.setEditor(new JSpinner.DefaultEditor(spinner));
    spinner.setFocusable(false);

    rannual = new JRadioButton("Annual");
    rpat = new JRadioButton("Paternal");
    rmat = new JRadioButton("Maternal");
    rcomp1 = new JRadioButton("Compassionate 1");
    rcomp2 = new JRadioButton("Compassionate 2");

    ButtonGroup group = new ButtonGroup();

    group.add(rannual);
    group.add(rpat);
    group.add(rmat);
    group.add(rcomp1);
    group.add(rcomp2);

    rannual.setBounds(25, 60, 100, 25);
    rannual.setFocusable(false);
    rannual.setFont(new Font("changa", Font.PLAIN, 14));
    rannual.setForeground(Color.black);
    rannual.setBackground(Color.decode("#644e5b"));
    rannual.addActionListener(this);

    rpat.setBounds(175, 60, 100, 25);
    rpat.setFocusable(false);
    rpat.setFont(new Font("changa", Font.PLAIN, 14));
    rpat.setForeground(Color.black);
    rpat.setBackground(Color.decode("#644e5b"));
    rpat.addActionListener(this);

    rmat.setBounds(25, 135, 100, 25);
    rmat.setFocusable(false);
    rmat.setFont(new Font("changa", Font.PLAIN, 14));
    rmat.setForeground(Color.black);
    rmat.setBackground(Color.decode("#644e5b"));
    rmat.addActionListener(this);

    rcomp1.setBounds(175, 135, 140, 25);
    rcomp1.setFocusable(false);
    rcomp1.setFont(new Font("changa", Font.PLAIN, 14));
    rcomp1.setForeground(Color.black);
    rcomp1.setBackground(Color.decode("#644e5b"));
    rcomp1.addActionListener(this);

    rcomp2.setBounds(75, 210, 140, 25);
    rcomp2.setFocusable(false);
    rcomp2.setFont(new Font("changa", Font.PLAIN, 14));
    rcomp2.setForeground(Color.black);
    rcomp2.setBackground(Color.decode("#644e5b"));
    rcomp2.addActionListener(this);

    ok = new JButton("Apply");
    cancel = new JButton("Cancel");
    submit = new JButton("Submit");

    ok.setFocusable(false);
    ok.setBounds(225, 350, 100, 25);
    ok.setBackground(Color.decode("#5cb85c"));
    ok.setForeground(Color.decode("#ffffff"));
    ok.addActionListener(this);

    cancel.setFocusable(false);
    cancel.setBounds(25, 350, 100, 25);
    cancel.setForeground(Color.red);
    cancel.addActionListener(this);

    submit.setFocusable(false);
    submit.setBounds(75, 350, 100, 25);
    submit.setBackground(Color.decode("#5cb85c"));
    submit.setForeground(Color.decode("#ffffff"));
    submit.addActionListener(this);

    app.add(label1);
    app.add(label2);
    app.add(label3);
    app.add(label4);
    app.add(label5);

    app.add(field1);
    app.add(field2);
    app.add(field3);
    app.add(field4);
    app.add(box);

    app.add(ok);
    app.add(cancel);

    app2.add(submit);

    app2.add(label6);
    app2.add(label7);

    app2.add(spinner);

    app2.add(rannual);
    app2.add(rpat);
    app2.add(rmat);
    app2.add(rcomp1);
    app2.add(rcomp2);

    app2.setVisible(false);

    apply.add(app);
    apply.add(app2);
    apply.setVisible(true);
    apply.setLocationRelativeTo(null);

    loadEmpno();
    loadLeave();
  }

  // METHOD BELOW IS VERY USEFUL
  public void loadEmpno() {
    connection();

    try {
      pst = con.prepareStatement("SELECT * from registration");
      ResultSet rs = pst.executeQuery();
      box.removeAllItems();

      while (rs.next()) {
        box.addItem(rs.getString("empno"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void loadLeave() {
    connection();

    String empno = box.getSelectedItem().toString();
    try {
      String query2 = "SELECT * FROM leaveinfo where empno = ?";
      pst = con.prepareStatement(query2);
      pst.setString(1, empno);
      ResultSet rs2 = pst.executeQuery();

      if (rs2.next() == false) {
        JOptionPane.showMessageDialog(
          null,
          "Oops!! Seems there is some",
          "Error!",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        annual = rs2.getDouble("annual");
        paternity = rs2.getInt("paternity");
        maternity = rs2.getInt("maternity");
        comp1 = rs2.getInt("compassionate1");
        comp2 = rs2.getInt("compassionate2");
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == box) {
      String empno = box.getSelectedItem().toString();

      connection();
      String query =
        "SELECT `fname`, `lname`, `gender`,`category` FROM `registration` WHERE empno = ?";
      try {
        pst = con.prepareStatement(query);
        pst.setString(1, empno);
        ResultSet rst = pst.executeQuery();

        if (rst.next() == false) {
          JOptionPane.showMessageDialog(
            null,
            "Oops!! Seems there is some",
            "empno Error!",
            JOptionPane.ERROR_MESSAGE
          );
        } else {
          String fname = rst.getString("fname");
          field1.setText(fname.trim());

          String lname = rst.getString("lname");
          field2.setText(lname.trim());

          String gender = rst.getString("gender");
          field3.setText(gender.trim());

          String category = rst.getString("category");
          field4.setText(category.trim());
        }
      } catch (SQLException e1) {
        JOptionPane.showMessageDialog(null, e1);
        e1.printStackTrace();
      }
    } else if (e.getSource() == ok) {
      apply.setTitle("USER LEAVE APPLICATION");
      app2.setVisible(true);
      ok.setEnabled(false);
      ok.setBackground(null);
    } else if (e.getSource() == submit) {
      loadLeave();
      int bleave = 0;

      String empno = box.getSelectedItem().toString();

      int days = Integer.parseInt(spinner.getValue().toString());
      Double dDays = Double.parseDouble(spinner.getValue().toString());

      if (rannual.isSelected() == true) {
        Double aleave = 0.0;
        aleave = annual - dDays;
        if (aleave < 0.0) {
          JOptionPane.showMessageDialog(
            this,
            "Sorrry!! You have an Annual Leave of " + annual + " days"
          );
        } else {
          String query3 = "UPDATE leaveinfo set annual = ? where empno = ?";
          try {
            pst = con.prepareStatement(query3);
            pst.setDouble(1, aleave);
            pst.setString(2, empno);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(
              this,
              "Your Annual Leave has been updated."
            );
            int response = JOptionPane.showConfirmDialog(
              null,
              "Would you like to view updated leave info?",
              "Skip to leave table",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE
            );
            if (response == 0) {
              apply.dispose();
              new ViewLeave();
            } else {
              apply.dispose();
              new LeaveFrame();
            }
          } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
          }
        }
      } else if (rpat.isSelected() == true) {
        bleave = paternity - days;
        if (bleave < 0) {
          JOptionPane.showMessageDialog(
            this,
            "Sorry!! You have Paternity Leave of " + paternity + " days"
          );
        } else {
          String query3 = "UPDATE leaveinfo set paternity = ? where empno = ?";
          try {
            pst2 = con.prepareStatement(query3);
            pst2.setInt(1, bleave);
            pst2.setString(2, empno);
            pst2.executeUpdate();
            JOptionPane.showMessageDialog(
              this,
              "Your Paternity Leave has been updated."
            );
            int response = JOptionPane.showConfirmDialog(
              null,
              "Would you like to view updated leave info?",
              "Skip to leave table",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE
            );
            if (response == 0) {
              apply.dispose();
              new ViewLeave();
            } else {
              apply.dispose();
              new LeaveFrame();
            }
          } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
          }
        }
      } else if (rmat.isSelected() == true) {
        bleave = maternity - days;
        if (bleave < 0) {
          JOptionPane.showMessageDialog(
            this,
            "Sorry!! You have a Maternity Leave of " + maternity + " days"
          );
        } else {
          String query3 = "UPDATE leaveinfo set maternity = ? where empno = ?";
          try {
            pst3 = con.prepareStatement(query3);
            pst3.setInt(1, bleave);
            pst3.setString(2, empno);
            pst3.executeUpdate();
            JOptionPane.showMessageDialog(
              this,
              "Your Materity Leave has been updated."
            );
            int response = JOptionPane.showConfirmDialog(
              null,
              "Would you like to view updated leave info?",
              "Skip to leave table",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE
            );
            if (response == 0) {
              apply.dispose();
              new ViewLeave();
            } else {
              apply.dispose();
              new LeaveFrame();
            }
          } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
          }
        }
      } else if (rcomp1.isSelected() == true) {
        bleave = comp1 - days;
        if (bleave < 0) {
          JOptionPane.showMessageDialog(
            this,
            "Sorry!! You have a Primary Compassionate Leave of " +
            comp1 +
            " days"
          );
        } else {
          String query3 =
            "UPDATE leaveinfo set compassionate1 = ? where empno = ?";
          try {
            pst4 = con.prepareStatement(query3);
            pst4.setInt(1, bleave);
            pst4.setString(2, empno);
            pst4.executeUpdate();
            JOptionPane.showMessageDialog(
              this,
              "Your Primary Compassionate Leave has been updated."
            );
            int response = JOptionPane.showConfirmDialog(
              null,
              "Would you like to view updated leave info?",
              "Skip to leave table",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE
            );
            if (response == 0) {
              apply.dispose();
              new ViewLeave();
            } else {
              apply.dispose();
              new LeaveFrame();
            }
          } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
          }
        }
      } else if (rcomp2.isSelected() == true) {
        bleave = comp2 - days;
        if (bleave < 0) {
          JOptionPane.showMessageDialog(
            this,
            "Sorry!! You have a Secondary Compassionate Leave of " +
            comp2 +
            " days"
          );
        } else {
          String query3 =
            "UPDATE leaveinfo set compassionate2 = ? where empno = ?";
          try {
            pst5 = con.prepareStatement(query3);
            pst5.setInt(1, bleave);
            pst5.setString(2, empno);
            pst5.executeUpdate();
            JOptionPane.showMessageDialog(
              this,
              "Your Secondary Compassionate Leave has been updated."
            );
            int response = JOptionPane.showConfirmDialog(
              null,
              "Would you like to view updated leave info?",
              "Skip to leave table",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE
            );
            if (response == 0) {
              apply.dispose();
              new ViewLeave();
            } else {
              apply.dispose();
              new LeaveFrame();
            }
          } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
          }
        }
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Please select Leave!!",
          "Error!",
          JOptionPane.WARNING_MESSAGE
        );
      }
    } else if (e.getSource() == rannual) {
      spinner.setModel(value1);
    } else if (e.getSource() == rpat) {
      spinner.setModel(value3);
    } else if (e.getSource() == rmat) {
      spinner.setModel(value2);
    } else if (e.getSource() == rcomp1) {
      spinner.setModel(value4);
    } else if (e.getSource() == rcomp2) {
      spinner.setModel(value5);
    } else if (e.getSource() == cancel) {
      apply.dispose();
      new LeaveFrame();
    }
  }
}
