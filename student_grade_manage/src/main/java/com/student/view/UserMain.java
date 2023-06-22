package com.student.view;

import com.student.dao.UserDao;
import com.student.domain.User;
import com.student.service.Impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.student.view.Login.admin;

/****
 * 用户管理界面
 */


public class UserMain extends JFrame {
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    JFrame frame = new JFrame("用户管理");
    //    ArrayList<User> users = new UserDao().queryAll();
    UserDao userDao = new UserDao();

    DefaultTableModel dtm;
    JTable table;
    JScrollPane scrollPane;

    //获取表格的id，行
    int id;
    int row = -1;

    /* 定义部件*/

    //查询栏
    JButton jbb = new JButton("成绩管理");
    JButton jb = new JButton("查询所有");
    JButton jb1 = new JButton("查询");
    JButton jb2 = new JButton("查询");
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JLabel label1 = new JLabel("用户查询");
    JLabel label2 = new JLabel("ID查询");

    JMenuItem m1 = new JMenuItem("修改密码");
    JMenuItem m2 = new JMenuItem("退出登录");

    //右侧按钮
    JButton btadd = new JButton("新增用户");
    JButton btdelete = new JButton("删除");
    JButton btupdate = new JButton("修改");


    public UserMain() {


        dtm = new DefaultTableModel() {


        };
        String[] head = {"id", "用户", "密码", "性别", "年龄", "地址", "电话", "职务"};
        for (int i = 0; i < head.length; i++) {
            dtm.addColumn(head[i]);

        }
        fillTable();
        table = new JTable(dtm);
        scrollPane = new JScrollPane(table);


        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdatePwd(frame);
                if (Main.u != null) {
                    Main.u.setUsername(admin);
                    if (UpdatePwd.pwd.equals(new UserDao().queryByName(admin).getPassword())) {
                        int i = userDao.update(Main.u);
                        clearTable();
                        fillTable();
                      /*  if (i > 0) {
                            area.setText("修改成功！您的新密码为：" + u.getPassword());
                        }
                    }else {area.setText("原密码错误！请检查后重新修改。");*/
                    }
                }

            }
        });

        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });


        jbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Main();
                frame.dispose();
            }
        });
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                fillTable();
            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                fillTableByName();
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!field2.getText().isEmpty()) {
                    clearTable();
                    fillTableById();
                }
            }
        });
        //获取Table属性
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = table.getSelectedRow();
                id = (int) table.getValueAt(row, 0);
            }
        });
        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "功能正在开发中！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "功能正在开发中！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = userServiceImpl.deleteUser(id);
                if (b) {
                    JOptionPane.showMessageDialog(frame, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "删除失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                clearTable();
                fillTable();
                row = -1;
            }
        });


        init();
    }

    void init() {

        Container contentPane = getContentPane();
        frame.setLayout(new BorderLayout());
        frame.setSize(820, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //表格布局
        frame.add(scrollPane, BorderLayout.CENTER);

        //查询处布局
        {
            JPanel panel = new JPanel();
            JMenuBar bar = new JMenuBar();
            JMenu menu = new JMenu(admin);

            ImageIcon bg = new ImageIcon("src/image/admin.jpg");
            JLabel label = new JLabel(bg);
            label.setSize(bg.getIconWidth(), bg.getIconHeight());


            menu.add(m1);
            menu.add(m2);
            bar.add(menu);

            panel.add(label1);

            field1.setColumns(16);

            panel.add(field1);
            panel.add(jb1);
            panel.add(label2);
            field2.setColumns(16);


            panel.add(field2);

            panel.add(jb2);
            panel.add(jb);
            panel.add(jbb);
            panel.add(label);

            panel.add(bar);
            frame.add(panel, BorderLayout.NORTH);


        }

        //右侧布局
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(12, 0, 2, 2));

            panel.add(btadd);

            panel.add(btupdate);

            panel.add(btdelete);

            frame.add(panel, BorderLayout.EAST);
        }
    }

    public void fillTable() {
        List<User> users = userServiceImpl.getAllUser();
        for (int i = 0; i < users.size(); i++) {
            User s = users.get(i);
            dtm.addRow(new Object[]{s.getId(), s.getUsername(), s.getPassword(), s.getSex(), s.getAge(), s.getAddress(), s.getTel(), s.getPost(),});
        }


    }

    public void fillTableByName() {
        clearTable();
        User s = userServiceImpl.getUserByName(field1.getText());
        dtm.addRow(new Object[]{s.getId(), s.getUsername(), s.getPassword(), s.getSex(), s.getAge(), s.getAddress(), s.getTel(), s.getPost(),});
    }

    public void fillTableById() {

        if ((field2.getText() == null)) return;
        int id = Integer.parseInt(field2.getText());

        clearTable();
        User s = userServiceImpl.getUserById(id);
        dtm.addRow(new Object[]{s.getId(), s.getUsername(), s.getPassword(), s.getSex(), s.getAge(), s.getAddress(), s.getTel(), s.getPost(),});
//            }
    }

    //清除表格
    public void clearTable() {
        dtm.getDataVector().clear();
        dtm.fireTableDataChanged();

    }


}
