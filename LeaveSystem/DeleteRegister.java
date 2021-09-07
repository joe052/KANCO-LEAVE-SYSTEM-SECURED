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

public class DeleteRegister implements ActionListener {

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
  JButton  delete;

  Connection con;
  PreparedStatement pst;
  PreparedStatement  pst1;
  PreparedStatement  pst2;

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

  public DeleteRegister() {
    view = new JFrame();
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setTitle("DELETE USER");
    view.setSize(1000, 700);
    view.setIconImage(klogo.getImage());
    view.setResizable(false);

    JPanel vPanel = new JPanel();

    vPanel.setBackground(Color.decode("#a8b865"));
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
    rable.setBackground(Color.decode("#a8b865"));
    rable.setForeground(Color.decode("#201e2b"));
    rable.setSelectionBackground(Color.decode("#ffe77a"));
    rable.setSelectionForeground(Color.decode("#266d06"));
    rable.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rable.setFillsViewportHeight(true);

    JScrollPane sp = new JScrollPane(rable);

    JLabel label = new JLabel("", hlogo, JLabel.CENTER);

    label.setBounds(25, 10, 50, 50);

    cancel = new JButton("Cancel");
    refresh = new JButton("Refresh");
    home = new JButton("Home");
    delete = new JButton("delete");

    cancel.setFocusable(false);
    cancel.setBounds(865, 53, 100, 30);
    cancel.addActionListener(this);
    cancel.setFont(new Font("Merienda", Font.BOLD, 14));
    cancel.setBackground(Color.decode("#ffe77a"));
    cancel.setForeground(Color.decode("#266d06"));

    refresh.setFocusable(false);
    refresh.setBounds(440, 53, 100, 30);
    refresh.addActionListener(this);
    refresh.setFont(new Font("Merienda", Font.BOLD, 14));
    refresh.setBackground(Color.decode("#266d06"));
    refresh.setForeground(Color.decode("#ffe77a"));

    home.setFocusable(false);
    home.setBounds(13, 60, 75, 25);
    home.addActionListener(this);
    home.setFont(new Font("Merienda", Font.BOLD, 14));
    home.setBackground(Color.decode("#266d06"));
    home.setForeground(Color.decode("#ffe77a"));

    delete.setFocusable(false);
    delete.setBounds(908, 0, 75, 23);
    delete.addActionListener(this);
    delete.setFont(new Font("Merienda", Font.PLAIN, 14));
    delete.setBackground(Color.decode("#cd0b00"));
    delete.setForeground(Color.decode("#ffffff"));

    header();
    model.removeRow(0);
    loadRegister();
    columnModify();

    Dimension size1 = new Dimension();
    size1.width = 500;
    size1.height = 300;

    sp.setPreferredSize(size1);

    vPanel.add(delete);
    vPanel.add(cancel);
    vPanel.add(home);
    vPanel.add(refresh);

    vPanel.add(label);

    Container pane = view.getContentPane();
    pane.setLayout(new BorderLayout());
    pane.setBackground(Color.decode("#a8b865"));
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
          "Ooops!! Seems there is some Errorcode 001!"
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
      JOptionPane.showMessageDialog(
        null,
        "Ooops!! " + e
      );
      e.printStackTrace();
    }
  }

  private void header() {
    JTableHeader head = rable.getTableHeader();
    head.setBackground(Color.decode("#266d06"));
    head.setForeground(Color.decode("#ffe77a"));
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

  public void deleter() {
    int row = rable.getSelectedRow();
    String selected = model.getValueAt(row, 2).toString();
    model.removeRow(row);

    if (row >= 0) {
      connection();
      try {
        String query = "DELETE registration , leaveinfo  FROM registration  INNER JOIN leaveinfo WHERE registration.empno = leaveinfo.empno AND registration.empno = ?";
        String query2 = "DELETE FROM registration WHERE `empno` = ?";
        pst1 = con.prepareStatement(query);
        pst1.setString(1, selected);
        pst1.executeUpdate();

        pst2 = con.prepareStatement(query2);
        pst2.setString(1, selected);
        pst2.executeUpdate();

        JOptionPane.showMessageDialog(null, "User deleted successfully!!");
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
          null, e);
        e.printStackTrace();
      }
    }
  }

  public void rowModify() {}

  /*public Component PrepareRenderer(TableCellRenderer r, int rowname,int columnName){
    Component c = super.PrepareRenderer(r,rowname,columnName);
    return c;

  }*/

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == delete) {
      int response = JOptionPane.showConfirmDialog(
        null,
        "Do you want to proceed deleting User?",
        "Confirm Delete",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.ERROR_MESSAGE
      );
      if (response == 0) {
        deleter();
      } else {
        view.dispose();
        new DeleteRegister();
      }
    } else if (e.getSource() == refresh) {
      view.dispose();
      new DeleteRegister();
    } else if (e.getSource() == cancel) {
      view.dispose();
      new AdminFrame();
    } else if (e.getSource() == home) {
      view.dispose();
      new HomeFrame();
    }
  }
}
