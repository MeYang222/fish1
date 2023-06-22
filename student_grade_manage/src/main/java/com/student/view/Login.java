package com.student.view;

import com.student.dao.UserDao;
import com.student.entity.User;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * denglu
 */

public class Login extends JFrame {


    JFrame frame;
    JLabel label1 = new JLabel("用户名：");
    JLabel label2 = new JLabel("密码：");

    //设置初始框用户密码值，方便登录
    static JTextField t1 = new JTextField("admin");
    JTextField t2 = new JTextField("123456");
    JButton bt1 = new JButton("登录");
    JButton bt2 = new JButton("注册");

    JLabel label = new JLabel("欢迎登录学生管理系统");
    JLabel tips = new JLabel();

    static String admin;////将此值设置为用户名，给主页调用展示并用作是否登录判断

    public Login() {

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        JPanel contentPanel = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(40, 100, 40, 100));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPane.add(contentPanel, BorderLayout.CENTER);

        // frame.setLayout(new BorderLayout());


        {//北
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 2, 2));
            label.setFont(new Font("微软雅黑", Font.BOLD, 15));


            panel.add(label);
            tips.setForeground(Color.RED);
            tips.setFont(new Font("微软雅黑", Font.BOLD, 12));
            panel.add(tips);
            contentPanel.add(panel, BorderLayout.NORTH);
        }


        {//左
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 0, 20));
            panel.add(label1);
            panel.add(label2);

            contentPanel.add(panel, BorderLayout.WEST);
        }


        {//中心
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 0, 20));
            t1.setColumns(8);
            panel.add(t1);
            t2.setColumns(8);
            panel.add(t2);

            contentPanel.add(panel, BorderLayout.CENTER);
        }

        {//底部按钮
            JPanel panel = new JPanel();
            // panel.setLayout(new GridLayout(0, 1,0, 20));

            panel.add(bt1);

            panel.add(bt2);

            contentPanel.add(panel, BorderLayout.SOUTH);
        }

        setVisible(true);

        /***
         * 监听
         */
        //登录
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /***
                 * 1.先判断输入不能为空
                 * 2.调用数据库查询用户名是否存在
                 * 3.效验
                 */
                String username = t1.getText();
                String password = t2.getText();
                if (username.isEmpty()) {
                    tips.setText("用户名不能为空");
                } else if (password.isEmpty()) {
                    tips.setText("密码不能为空");
                } else {
                    User user = new UserDao().queryByName(username);

                    if (user == null) {
                        JOptionPane.showMessageDialog(frame, "用户名不存在", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {


                            EventQueue.invokeLater(() -> {
                                try {
                                    BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
                                    BeautyEyeLNFHelper.launchBeautyEyeLNF();//启用BeautyEyeLNF


                                    UIManager.put("RootPane.setupButtonVisible", false);
                                    admin = username;//将此值设置为用户名，给主页调用展示并用作是否登录判断
                                    new Main();//跳转主页


                                    // JOptionPane.showMessageDialog(frame, "登录成功！"+admin, "提示", JOptionPane.INFORMATION_MESSAGE);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });


                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(frame, "用户名密码不正确！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            //tips.setText("用户名密码不正确");
                        }
                    }
                }


            }
        });
        //注册
        bt2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                dispose();
            }
        });

    }


}
