package com.student.view;

import com.student.entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.student.view.Main.*;

/**
 * 修改密码
 */

public class UpdatePwd extends JDialog {

    static String pwd;
    JTextField bel = new JTextField();
    JTextField bel1 = new JTextField();
    JTextField bel2 = new JTextField();


    JLabel tips = new JLabel();

    JButton bt = new JButton("确认修改");


    public UpdatePwd(Frame frame) {

        super(frame, "修改密码", true);


        Container contentPane = getContentPane();
        JPanel contentPanel = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPane.add(contentPanel, BorderLayout.CENTER);

        setSize(300, 300);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        tips.setForeground(Color.MAGENTA);

        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 5, 5));
            ImageIcon bg = new ImageIcon("src/image/updatepwd.jpg");
            JLabel label = new JLabel(bg);
            label.setSize(bg.getIconWidth(), bg.getIconHeight());
            p.add(label);
            JLabel area = new JLabel("请修改您的密码！");

            area.setForeground(Color.RED);
            p.add(area);
            contentPanel.add(p, BorderLayout.WEST);

        }


        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 0, 1));
            JLabel label = new JLabel("旧密码:");
            JLabel label1 = new JLabel("新密码:");
            JLabel label2 = new JLabel("新密码：");


            p.add(label);
            p.add(bel);


            p.add(label1);
            p.add(bel1);


            p.add(label2);
            p.add(bel2);


            contentPanel.add(p, BorderLayout.CENTER);


        }
        {
            JPanel p = new JPanel();


            p.add(tips);
            p.add(bt);
            contentPanel.add(p, BorderLayout.SOUTH);


        }


        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String l1 = bel.getText();
                String l2 = bel1.getText();
                String l3 = bel2.getText();


                User u = new User();


                if (l1.isEmpty() || l2.isEmpty() || l3.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "输入不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    // tips.setText("输入不能为空！");

                } else if (!l2.equals(l3)) {
                    //tips.setText("两次密码输入不一致！");
                    JOptionPane.showMessageDialog(frame, "两次密码输入不一致！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    u.setPassword(l2);
                    pwd = l1;
                    Main.u = u;
                    dispose();
                }

            }
        });


        setVisible(true);
    }

}


