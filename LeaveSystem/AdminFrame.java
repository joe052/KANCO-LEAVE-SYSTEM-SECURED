package LeaveSystem;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdminFrame implements ActionListener {
    JFrame admin;
    JPanel panel;
    ImageIcon klogo = new ImageIcon("kanco.png");
    ImageIcon dlogo = new ImageIcon("delete.png");
    ImageIcon elogo = new ImageIcon("edit.png");
    ImageIcon rlogo = new ImageIcon("register.png");
    ImageIcon hlogo = new ImageIcon("home.png");
    ImageIcon llogo = new ImageIcon("lview2.png");

    JButton register;
    JButton delete;
    JButton edit;
    JButton view;
    JButton home;

    AdminFrame() {
        admin = new JFrame();
        admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admin.setTitle("ADMINISTRATOR");
        admin.setSize(700, 500);
        admin.setIconImage(klogo.getImage());
        admin.setResizable(false);

        panel = new JPanel();
        //panel.setBackground(Color.decode("#501b1d"));
        panel.setBackground(Color.decode("#c96567"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        panel.setBounds(0, 0, 500, 500);

        register = new JButton("Register user");
        delete = new JButton("Delete user");
        edit = new JButton("Edit user");
        home = new JButton("Home");
        view = new JButton("View users");

        register.setIcon(rlogo);
        register.setVerticalTextPosition(JButton.BOTTOM);
        register.setHorizontalTextPosition(JButton.CENTER);
        
        delete.setIcon(dlogo);
        delete.setVerticalTextPosition(JButton.BOTTOM);
        delete.setHorizontalTextPosition(JButton.CENTER);

        edit.setIcon(elogo);
        edit.setVerticalTextPosition(JButton.BOTTOM);
        edit.setHorizontalTextPosition(JButton.CENTER);

        view.setIcon(llogo);
        view.setVerticalTextPosition(JButton.BOTTOM);
        view.setHorizontalTextPosition(JButton.CENTER);

        home.setIcon(hlogo);
        home.setVerticalTextPosition(JButton.BOTTOM);
        home.setHorizontalTextPosition(JButton.CENTER);

        register.setBounds(50, 60, 150, 50);
        delete.setBounds(250, 60, 150, 50);
        edit.setBounds(450, 60, 150, 50);
        view.setBounds(250, 160, 150, 50);
        home.setBounds(50, 160, 150, 50);

        register.setFocusable(false);
        delete.setFocusable(false);
        edit.setFocusable(false);
        home.setFocusable(false);
        view.setFocusable(false);

        register.addActionListener(this);
        home.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        view.addActionListener(this);

        panel.add(register);
        panel.add(delete);
        panel.add(edit);
        panel.add(view);
        panel.add(home);

        admin.add(panel);
        admin.setLocationRelativeTo(null);
        admin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == register) {
            admin.dispose();
            new Register();
        }else if(e.getSource()==home){
            admin.dispose();
            new HomeFrame();
        }else if(e.getSource()==delete){
            admin.dispose();
            new DeleteRegister();
        }else if(e.getSource()==edit){
            admin.dispose();
            new UpdateRegister();
        }else if(e.getSource()== view){
            admin.dispose();
            new ViewRegister();
        }

    }

}
