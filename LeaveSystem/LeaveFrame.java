package LeaveSystem;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LeaveFrame implements ActionListener {
    JFrame lframe;
    JPanel panel;
    ImageIcon klogo = new ImageIcon("kanco.png");
    ImageIcon dlogo = new ImageIcon("lview.png");
    ImageIcon llogo = new ImageIcon("leavedit.png");
    ImageIcon alogo = new ImageIcon("apply.png");
    ImageIcon hlogo = new ImageIcon("home.png");

    JButton apply;
    JButton view;
    JButton edit;
    JButton home;

    LeaveFrame() {
        lframe = new JFrame();
        lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lframe.setTitle("LEAVE CONTROL");
        lframe.setSize(700, 500);
        lframe.setIconImage(klogo.getImage());
        lframe.setResizable(false);

        panel = new JPanel();
        //panel.setBackground(Color.decode("#41b3a3"));
        panel.setBackground(Color.decode("#9e5a63"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        panel.setBounds(0, 0, 500, 500);

        apply = new JButton("Apply Leave");
        view = new JButton("View Leaveinfo");
        edit = new JButton("Edit Leave");
        home = new JButton("Home");

        apply.setIcon(alogo);
        apply.setVerticalTextPosition(JButton.BOTTOM);
        apply.setHorizontalTextPosition(JButton.CENTER);

        view.setIcon(dlogo);
        view.setVerticalTextPosition(JButton.BOTTOM);
        view.setHorizontalTextPosition(JButton.CENTER);

        edit.setIcon(llogo);
        edit.setVerticalTextPosition(JButton.BOTTOM);
        edit.setHorizontalTextPosition(JButton.CENTER);

        home.setIcon(hlogo);
        home.setVerticalTextPosition(JButton.BOTTOM);
        home.setHorizontalTextPosition(JButton.CENTER);

        apply.setBounds(50, 60, 150, 50);
        view.setBounds(250, 60, 150, 50);
        edit.setBounds(450, 60, 150, 50);
        home.setBounds(250, 160, 150, 50);


        apply.setFocusable(false);
        view.setFocusable(false);
        edit.setFocusable(false);
        home.setFocusable(false);

        apply.addActionListener(this);
        home.addActionListener(this);
        view.addActionListener(this);
        edit.addActionListener(this);

        panel.add(apply);
        panel.add(view);
        panel.add(edit);
        panel.add(home);

        lframe.add(panel);
        lframe.setLocationRelativeTo(null);
        lframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == apply) {
            lframe.dispose();
            new ApplyFrame();
        }else if(e.getSource()==home){
            lframe.dispose();
            new HomeFrame();
        }else if(e.getSource()==view){
           lframe.dispose();
           new ViewLeave();
        }else if(e.getSource()==edit){
            lframe.dispose();
            new UpdateLeave();
        }

    }

}
