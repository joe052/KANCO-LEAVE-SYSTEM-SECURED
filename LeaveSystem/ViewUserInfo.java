package LeaveSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ViewUserInfo implements ActionListener {

  Object[] columnNames = {
    "Fname",
    "Lname",
    "Empno",
    "Gender",
    "tel",
    "Category",
  };
  Object[][] rowData = {
    { "Fname", "Lname", "Empno", "Gender", "tel", "Category" },
  };

  JTable rable;
  DefaultTableModel model;

  JButton cancel;
  JButton refresh;
  JButton home;
  JButton leave;

  Connection con;
  PreparedStatement pst;

  ImageIcon klogo = new ImageIcon("kanco.png");
  ImageIcon hlogo = new ImageIcon("home.png");

  JFrame view;

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
      ex.printStackTrace();
    }
  }

  public ViewUserInfo() {
    view = new JFrame();
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setTitle("USER INFORMATION");
    view.setSize(1000, 700);
    view.setIconImage(klogo.getImage());
    view.setResizable(false);

    JPanel vPanel = new JPanel();

    vPanel.setBackground(Color.decode("#846f5a"));
    vPanel.setLayout(null);
    vPanel.setPreferredSize(new Dimension(500, 100));

    model = new DefaultTableModel(rowData, columnNames);
    rable =
      new JTable(model) {
        private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {
          return false;
        }
      };

    rable.setPreferredScrollableViewportSize(new Dimension(500, 500));
    rable.setBackground(Color.decode("#846f5a"));
    rable.setForeground(Color.decode("#201e2b"));
    rable.setSelectionBackground(Color.decode("#010205"));
    rable.setSelectionForeground(Color.decode("#ffffff"));
    rable.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rable.setFillsViewportHeight(true);

    JScrollPane sp = new JScrollPane(rable);

    JLabel label = new JLabel("", hlogo, JLabel.CENTER);

    label.setBounds(25, 10, 50, 50);

    cancel = new JButton("Cancel");
    refresh = new JButton("Refresh");
    home = new JButton("Home");
    leave = new JButton("View Leave Info");

    cancel.setFocusable(false);
    cancel.setBounds(865, 53, 100, 30);
    cancel.addActionListener(this);
    cancel.setFont(new Font("Merienda", Font.PLAIN, 14));
    cancel.setBackground(Color.decode("#cd0b00"));
    cancel.setForeground(Color.white);

    refresh.setFocusable(false);
    refresh.setBounds(440, 53, 100, 30);
    refresh.addActionListener(this);
    refresh.setFont(new Font("Merienda", Font.BOLD, 14));
    refresh.setBackground(Color.decode("#846f5a"));
    refresh.setForeground(Color.decode("#f1d200"));

    home.setFocusable(false);
    home.setBounds(13, 60, 75, 25);
    home.addActionListener(this);
    home.setFont(new Font("Merienda", Font.BOLD, 14));
    home.setBackground(Color.decode("#846f5a"));
    home.setForeground(Color.decode("#f1d200"));

    leave.setFocusable(false);
    leave.setBounds(848, 0, 135, 23);
    leave.addActionListener(this);
    leave.setFont(new Font("Merienda", Font.PLAIN, 14));
    leave.setBackground(Color.green);

    header();
    model.removeRow(0);
    loadRegister();
    columnModify();

    Dimension size1 = new Dimension();
    size1.width = 500;
    size1.height = 300;

    sp.setPreferredSize(size1);

    vPanel.add(leave);
    vPanel.add(cancel);
    vPanel.add(home);
    vPanel.add(refresh);

    vPanel.add(label);

    Container pane = view.getContentPane();
    pane.setLayout(new BorderLayout());
    pane.setBackground(Color.decode("#846f5a"));
    pane.add(sp, BorderLayout.CENTER);
    pane.add(vPanel, BorderLayout.SOUTH);

    view.setLocationRelativeTo(null);
    view.setVisible(true);
  }

  public void loadRegister() {
    try {
      connection();
      String query = "SELECT * from registration";
      pst = con.prepareStatement(query);
      ResultSet rst = pst.executeQuery(query);
      if (rst.next() == false) {
        JOptionPane.showMessageDialog(
          null,
          "Ooops!! Seems there is some Error, no data to display!"
        );
      } else {
        while (rst.next() == true) {
          String fname = rst.getString("fname");
          String lname = rst.getString("lname");
          String empno = rst.getString("empno");
          String gender = rst.getString("gender");
          String tel = rst.getString("tel");
          String category = rst.getString("category");

          Object[] rows = { fname, lname, empno, gender, tel, category };
          model.addRow(rows);
        }
        con.close();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Ooops!! " + e);
      e.printStackTrace();
    }
  }

  private void header() {
    JTableHeader head = rable.getTableHeader();
    head.setBackground(Color.decode("#bb7817"));
    head.setForeground(Color.decode("#0f0a52"));
    head.setFont(new Font("Changa", Font.BOLD, 16));
  }

  public void columnModify() {
    TableColumn column1 = rable.getColumnModel().getColumn(0);
    column1.setPreferredWidth(100);
    TableColumn column2 = rable.getColumnModel().getColumn(1);
    column2.setPreferredWidth(100);
    TableColumn column3 = rable.getColumnModel().getColumn(2);
    column3.setPreferredWidth(55);
    TableColumn column4 = rable.getColumnModel().getColumn(3);
    column4.setPreferredWidth(50);
    TableColumn column5 = rable.getColumnModel().getColumn(4);
    column5.setPreferredWidth(100);
    TableColumn column6 = rable.getColumnModel().getColumn(5);
    column6.setPreferredWidth(100);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == leave) {
      view.dispose();
      new ViewLeaveInfo();
    } else if (e.getSource() == refresh) {
      view.dispose();
      new ViewUserInfo();
    } else if (e.getSource() == cancel) {
      view.dispose();
      new ViewFrame();
    } else if (e.getSource() == home) {
      view.dispose();
      new HomeFrame();
    }
  }
}
