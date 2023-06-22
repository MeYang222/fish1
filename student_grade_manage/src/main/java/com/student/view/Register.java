package com.student.view;

import com.student.dao.UserDao;
import com.student.entity.User;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/***
 * zhuce
 * 注册
 */
public class Register extends JFrame {
    JFrame frame = new JFrame("注册");
    JLabel label = new JLabel("欢迎注册");
    JLabel username = new JLabel("用户名：");

    JLabel password = new JLabel("密码：");
    JLabel sex = new JLabel("性别：");
    JLabel age = new JLabel("年龄：");
    JLabel address = new JLabel("地址：");
    JLabel tel = new JLabel("电话：");
    JLabel post = new JLabel("类型");

    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();

    JButton bt = new JButton("注册");
    JButton bt1 = new JButton("返回登录");
    JLabel tips = new JLabel();

    public Register() {

        Container contentPane = getContentPane();
        JPanel contentPanel = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 80, 10, 80));
        contentPanel.setLayout(new BorderLayout(5, 10));
        contentPane.add(contentPanel, BorderLayout.CENTER);

        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 2, 2));
            label.setFont(new Font("微软雅黑", Font.BOLD, 17));

            panel.add(label);
            tips.setForeground(Color.RED);
            tips.setFont(new Font("微软雅黑", Font.BOLD, 12));
            panel.add(tips);

            contentPanel.add(panel, BorderLayout.NORTH);
        }


        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 0, 10));
            username.setFont(new Font("微软雅黑", Font.BOLD, 12));
            password.setFont(new Font("微软雅黑", Font.BOLD, 12));
            panel.add(username);

            panel.add(password);
            panel.add(sex);
            panel.add(age);
            panel.add(address);
            panel.add(tel);
            panel.add(post);

            contentPanel.add(panel, BorderLayout.WEST);
        }

        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 0, 10));
            field1.setColumns(20);
            field2.setColumns(20);
            panel.add(field1);

            panel.add(field2);
            panel.add(field3);
            panel.add(field4);
            panel.add(field5);
            panel.add(field6);
            panel.add(field7);

            contentPanel.add(panel, BorderLayout.EAST);
        }


        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 2, 5, 5));

            panel.add(bt);
            panel.add(bt1);

            contentPanel.add(panel, BorderLayout.SOUTH);
        }

        bt1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /***
                 * 注册事件--------
                 * 1.先判断输入的账号密码是否为空（其他可为空，为空存入null，不为空将输入数据存入）
                 * 2.判断账号是否已经存在（存在弹出提示，不存在调用dao层添加）
                 * 3.根据dao层的返回结果判断是否添加成功
                 */

                UserDao usersDao = new UserDao();
                String username = field1.getText();
                String password = field2.getText();

                if (username.isEmpty()) {
                    tips.setText("用户名不能为空");
                } else if (password.isEmpty()) {
                    tips.setText("密码不能为空");
                } else {
                    ArrayList<User> users = new UserDao().queryByName(username);
                    if (users == null) {
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        if (!(field3.getText() == null)) {
                            user.setSex(field3.getText());
                        }
                        if (!(field4.getText().isEmpty())) {
                            int age = Integer.parseInt(field4.getText());
                            user.setAge(age);
                        }
                        if (!(field5.getText() == null)) {
                            user.setAddress(field5.getText());
                        }
                        if (!(field6.getText() == null)) {
                            user.setTel(field6.getText());
                        }
                        if (!(field7.getText() == null)) {
                            user.setPost(field7.getText());
                        }

                        int s = usersDao.save(user);
                        if (s > 0) {
                            JOptionPane.showMessageDialog(frame, "注册成功！");
                        } else {
                            JOptionPane.showMessageDialog(frame, "注册失败，请联系管理员！", "错误", JOptionPane.ERROR_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "用户名已存在！");
                    }
                }
            }
        });


    }


}
