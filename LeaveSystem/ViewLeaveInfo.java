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

public class ViewLeaveInfo implements ActionListener {

  Object[] columnNames = {
    "Empno",
    "Fname",
    "Lname",
    "Annual",
    "Maternity",
    "Paternity",
    "Compassionate 1",
    "Compassionate 2",
    "Year",
  };
  Object[][] rowData = {
    {
      "Empno",
      "",
      "",
      "Annual",
      "Maternity",
      "Paternity",
      "Compassionate 1",
      "Compassionate 2",
      "Year",
    },
  };

  JTable rable;
  DefaultTableModel model;

  JButton cancel;
  JButton refresh;
  JButton home;
  JButton ok;

  Connection con;
  PreparedStatement pst;
  PreparedStatement pst2;

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

  public ViewLeaveInfo() {
    view = new JFrame();
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setTitle("USER LEAVE INFORMATION");
    view.setSize(1000, 700);
    view.setIconImage(klogo.getImage());
    view.setResizable(false);

    JPanel vPanel = new JPanel();

    vPanel.setBackground(Color.decode("#846f5a"));
    vPanel.setPreferredSize(new Dimension(500, 100));
    vPanel.setLayout(null);

    JLabel label = new JLabel("", hlogo, JLabel.CENTER);

    label.setBounds(25, 10, 50, 50);

    cancel = new JButton("Cancel");
    refresh = new JButton("Refresh");
    home = new JButton("Home");
    ok = new JButton("Apply");

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

    ok.setFocusable(false);
    ok.setBounds(910, 0, 75, 23);
    ok.addActionListener(this);
    ok.setFont(new Font("Merienda", Font.PLAIN, 14));
    ok.setBackground(Color.green);

    model = new DefaultTableModel(rowData, columnNames);
    rable = new JTable(model) {
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

    header();
    model.removeRow(0);
    loadRegister();
    columnModify();

    Dimension size1 = new Dimension();
    size1.width = 500;
    size1.height = 300;

    sp.setPreferredSize(size1);

    vPanel.add(cancel);
    vPanel.add(refresh);
    vPanel.add(home);
    vPanel.add(ok);

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
      //String query2 = "SELECT `fname`, `lname` FROM `registration`";
      String query =
        "SELECT leaveinfo.*,registration.* FROM leaveinfo INNER JOIN registration ON leaveinfo.empno = registration.empno";
      pst = con.prepareStatement(query);
      ResultSet rst = pst.executeQuery(query);

      //pst2 = con.prepareStatement(query2);
      //ResultSet rst2 = pst.executeQuery(query2);

      if (rst.next() == false) {
        JOptionPane.showMessageDialog(
          null,
          "Ooops!! Seems there is some Error, no data to display!"
        );
      } else {
        while (rst.next() == true) {
          String annual = String.valueOf(rst.getDouble("annual"));
          String maternity = String.valueOf(rst.getInt("maternity"));
          String empno = rst.getString("empno");
          String paternity = String.valueOf(rst.getInt("paternity"));
          String comp1 = String.valueOf(rst.getInt("compassionate1"));
          String comp2 = String.valueOf(rst.getInt("compassionate2"));
          String year = String.valueOf(rst.getInt("year"));

          String fname = rst.getString("fname");
          String lname = rst.getString("lname");

          Object[] rows = {
            empno,
            fname,
            lname,
            annual,
            maternity,
            paternity,
            comp1,
            comp2,
            year,
          };

          model.addRow(rows);
        }
        con.close();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
        null,
        "Ooops!! "
      + e);
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
    column3.setPreferredWidth(100);
    TableColumn column4 = rable.getColumnModel().getColumn(3);
    column4.setPreferredWidth(100);
    TableColumn column5 = rable.getColumnModel().getColumn(4);
    column5.setPreferredWidth(100);
    TableColumn column6 = rable.getColumnModel().getColumn(5);
    column6.setPreferredWidth(100);
    TableColumn column7 = rable.getColumnModel().getColumn(6);
    column7.setPreferredWidth(100);
    TableColumn column8 = rable.getColumnModel().getColumn(7);
    column8.setPreferredWidth(100);
    TableColumn column9 = rable.getColumnModel().getColumn(8);
    column9.setPreferredWidth(55);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == refresh) {
      view.dispose();
      new ViewLeaveInfo();
    } else if (e.getSource() == ok) {
      view.dispose();
      new ApplyFrame();
    } else if (e.getSource() == cancel) {
      view.dispose();
      new ViewFrame();
    } else if (e.getSource() == home) {
      view.dispose();
      new HomeFrame();
    }
  }
}
