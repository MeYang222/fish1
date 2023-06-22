package com.student.view;

import com.student.dao.StudentDao;
import com.student.dao.UserDao;
import com.student.entity.Student;
import com.student.entity.User;
import com.student.service.Impl.StudentServiceImpl;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import com.student.util.ExcelExporter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static com.student.view.Login.admin;

/**
 * 主界面
 */
public class Main {
    //创建dao层对象
    StudentDao studentdao = new StudentDao();
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
    UserDao userDao = new UserDao();

    //用来存数据库数据（这样有bug，弃用）
    //  ArrayList<Student> students = studentdao.queryAll();

    //临时数据
    ArrayList studentlist = new ArrayList();

    //给子窗口传参
    JFrame frame = new JFrame("学生成绩管理系统");
    JFrame frame1;
    JFrame frame2;


    public static Student stu = null; // 临时学生对象（Student）
    public static User u = null; // 临时用户对象(User)

    //表格
    public static DefaultTableModel dtm;
    public JTable table1;
    public JScrollPane scrollPane;

    //获取表格的id，行
    static String id = null;
    static int row = -1;

    //用来保存返回值，给窗口调用

    static double average1 = 0;
    static double average2 = 0;
    static double average3 = 0;

    static double rate1;
    static double rate2;
    static double rate3;

    static double max1;
    static double max2;
    static double max3;

    static double min1;
    static double min2;
    static double min3;


    /**
     * 定义部件
     */


    //查询栏
    JButton jbb = new JButton("用户管理");
    JButton jb = new JButton("查询所有");
    JButton jb1 = new JButton("查询");
    JButton jb2 = new JButton("查询");
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JLabel label1 = new JLabel("姓名查询");
    JLabel label2 = new JLabel("学号查询");

    JMenuItem m1 = new JMenuItem("修改密码");
    JMenuItem m2 = new JMenuItem("退出登录");


    //右侧按钮
    JButton jb3 = new JButton("新增学生");
    JButton jb4 = new JButton("修改");
    JButton jb5 = new JButton("删除");
    JButton jb6 = new JButton("排序");
    JButton jb7 = new JButton("平均值");
    JButton jb8 = new JButton("不及格");
    JButton jb9 = new JButton("最值");
    JButton jb10 = new JButton("优秀率");
    JButton jb11 = new JButton("下载表格");

    //下侧文本域
    JTextArea area = new JTextArea(3, 80);

    /***
     * 防火墙
     * 检测用户是否登录
     * 浅做一下样子
     */
    void MainTest() {
        if (admin == null) {
            JOptionPane.showMessageDialog(null, "还未登录！请先登录", "提示", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new Login();
        } else {
            new Main();
        }
    }

    /**
     * 构造方法
     */
    public Main() {


        //文本框不可编辑
        area.setEditable(false);

        dtm = new DefaultTableModel() {
            // 设置单元格不可编辑
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }

        };
        //表头
        String[] head = {"学号", "姓名", "性别", "年龄", "java", "高数", "英语", "总分"};
        for (int i = 0; i < head.length; i++) {
            dtm.addColumn(head[i]);

        }
        //初始调用数据
        fillTable();
        table1 = new JTable(dtm);
        scrollPane = new JScrollPane(table1);


        /**
         * 事件监控
         */
        {
            //获取Table属性
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    row = table1.getSelectedRow();
                    id = (String) table1.getValueAt(row, 0);
                }
            });

            //右上角修改密码
            m1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdatePwd(frame);
                    if (u != null) {
                        u.setUsername(admin);//将当期登录账号封装到该对象
                        if (UpdatePwd.pwd.equals(new UserDao().queryByName(admin).getPassword())) {
                            int i = userDao.update(u);
                            if (i > 0) {
                                area.setText("修改成功！您的新密码为：" + u.getPassword());
                            }
                        } else {
                            area.setText("原密码错误！请检查后重新修改。");
                        }
                    }

                }
            });
            //右上角退出登录
            m2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new Login();

                }
            });

            //用户管理界面
            jbb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UserMain();
                    frame.dispose();
                }
            });
            //查询所有
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearTable();
                    fillTable();
                }
            });

            //姓名查询
            jb1.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!field1.getText().isEmpty()) {
                        fillTableByName();
                    }


                }
            });
            //学号查询
            jb2.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!field2.getText().isEmpty()) {
                        fillTableById();
                    }
                }
            });

            //添加学生信息
            jb3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AddDialog(frame);
                    clearTable();
                    fillTable();
                }
            });

            //修改学生信息
            jb4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (row != -1) {

                        new UpdateDialog(frame);
                        clearTable();
                        fillTable();
//                        }
                    }
                }
            });

            //删除
            jb5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//
                    studentServiceImpl.deleteStudentById(id);
                    area.setText("已删除id:" + id + "的数据！");

                    clearTable();
                    fillTable();
                    row = -1;//删除后设置为-1，不然下次没点table就会删除当前行
//                        }
//                    }
                }
            });

            //排序
            jb6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /***
                     * 临时数组存值
                     * 调用构造器进行排序
                     * 将当期数组加进table显示
                     */
//
                    clearTable();
                    fillsort();
                    area.setText("已进行降序排序！");
                    studentlist.clear();//清除该数组数据，不然下次会加2倍数据
                }
            });


            //查询各科不及格
            jb8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame("不及格");


                    frame.setLayout(new BorderLayout());
                    frame.setSize(250, 150);
                    frame.setVisible(true);
                    // setDefaultCloseOperation(EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);

                    JPanel p = new JPanel();
                    JButton bt1 = new JButton("java");
                    // bt1.setBounds(100, 100, 40, 30);

                    p.add(bt1);

                    JButton bt2 = new JButton("高数");
                    // bt1.setBounds(100, 100, 40, 30);
                    p.add(bt2);

                    JButton bt3 = new JButton("英语");
                    // bt1.setBounds(100, 100, 40, 30);
                    p.add(bt3);

                    frame.add(p, BorderLayout.CENTER);


                    JPanel panel = new JPanel();
                    JLabel label = new JLabel("请选择要查询的科目");
                    label.setForeground(Color.MAGENTA);
                    panel.add(label);
                    frame.add(panel, BorderLayout.SOUTH);


                    /***
                     * 将查询到数据加到临时数组
                     * 再讲数组加到table
                     */

                    bt1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            area.setText("本次查询记录为java成绩不及格♡");
                            ArrayList<Student> students = studentdao.queryAll();

                            ArrayList list = new ArrayList();
                            for (int i = 0; i < students.size(); i++) {
                                if (students.get(i).getJavascore() < 60) {
                                    list.add(students.get(i));
                                }
                            }
                            clearTable();
                            for (int i = 0; i < list.size(); i++) {
                                Student s = (Student) list.get(i);
                                dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore(), s.getTotal()});

                            }

                            frame.dispose();
                        }
                    });

                    bt2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            area.setText("本次查询记录为高数成绩不及格♡");
                            ArrayList<Student> students = studentdao.queryAll();
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < students.size(); i++) {
                                if (students.get(i).getGaoshuscore() < 60) {
                                    list.add(students.get(i));
                                }
                            }
                            clearTable();
                            for (int i = 0; i < list.size(); i++) {
                                Student s = (Student) list.get(i);
                                dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore(), s.getTotal()});

                            }
                            frame.dispose();
                        }
                    });


                    bt3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            area.setText("本次查询记录为英语成绩不及格♡");
                            ArrayList<Student> students = studentdao.queryAll();
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < students.size(); i++) {
                                if (students.get(i).getEnglishscore() < 60) {
                                    list.add(students.get(i));
                                }
                            }
                            clearTable();
                            for (int i = 0; i < list.size(); i++) {
                                Student s = (Student) list.get(i);
                                dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore(), s.getTotal()});

                            }
                            frame.dispose();
                        }
                    });

                }
            });

            /***
             * 平均值，优秀率，最值
             * 对三个按钮进行监听，打开对应参数的TipDialog窗口
             * 调用相应的方法
             * 在窗口中显示计算结果
             */


            //平均值
            jb7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Student> students = studentdao.queryAll();
                    average(students);
                    area.setText("java平均值为：" + average1 + "\n" + "高数平均值为：" + average2 + "\n" + "英语平均值为：" + average3);
                    new TipDialog(frame, frame1);
                }
            });

            //最大最小值
            jb9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Student> students = studentdao.queryAll();
                    max(students);
                    min(students);
                    area.setText("java最大值为：" + max1 + "\t" + "\t" + "java最小值为：" + min1 + "\n" + "高数最大值为：" + max2 + "\t" + "\t" + "高数最小值为：" + min2 + "\n" + "英语最大值为：" + max3 + "\t" + "\t" + "英语最小值为：" + min3);
                    new TipDialog(frame, frame1, frame2);


                }
            });

            //优秀率
            jb10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Student> students = studentdao.queryAll();

                    ExcellentRates(students);
                    area.setText("java优秀率为：" + rate1 + "\n" + "高数优秀率为：" + rate2 + "\n" + "英语优秀率为：" + rate3);

                    new TipDialog(frame);


                }
            });


            /***
             * 将table内容下载到本地
             *
             */

            //下载
            jb11.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int value = JOptionPane.showConfirmDialog(frame, "您确定要下载表格内容到本地吗？", "提示", JOptionPane.INFORMATION_MESSAGE);
                    if (value == JOptionPane.YES_OPTION) {
                        try {
                            ExcelExporter exp = new ExcelExporter();
                            exp.exportTable(table1, new File("student.xls"));
                            JOptionPane.showMessageDialog(frame, "下载成功！请前往根目录查收", "提示", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            ex.printStackTrace();
                        }
                    }


                }
            });
        }

        init();

    }


    /**
     * 布局
     */
    void init() {

        //容器
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
            panel.setLayout(new GridLayout(0, 1, 0, 5));

            panel.add(jb3);
            panel.add(jb4);
            panel.add(jb5);
            panel.add(jb6);
            panel.add(jb7);
            panel.add(jb8);
            panel.add(jb9);
            panel.add(jb10);
            panel.add(jb11);
            frame.add(panel, BorderLayout.EAST);

        }
        //文本域布局
        {
            JPanel panel = new JPanel();
            ImageIcon bg = new ImageIcon("src/image/bg1.jpg");
            JLabel label = new JLabel(bg);
            label.setSize(bg.getIconWidth(), bg.getIconHeight());

            panel.add(label);


            panel.add(area);

            ImageIcon bg1 = new ImageIcon("src/image/bg2.jpg");
            JLabel label1 = new JLabel(bg1);
            label.setSize(bg1.getIconWidth(), bg1.getIconHeight());
            //bg.getIconWidth(),bg.getIconHeight()
            panel.add(label1);
            frame.add(panel, BorderLayout.SOUTH);
        }
    }

    /***
     * 方法
     * @param
     */
    //排序数组加到table
    public void fillsort() {
        List<com.student.domain.Student> students = studentServiceImpl.descByTotal();

        for (int i = 0; i < students.size(); i++) {
            com.student.domain.Student s = students.get(i);
            dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore(), s.getTotal()});
        }


    }

    //放入table
    public void fillTable() {
        List<com.student.domain.Student> allStudent = studentServiceImpl.getAllStudent();

        for (int i = 0; i < allStudent.size(); i++) {
            com.student.domain.Student s = allStudent.get(i);
            dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore(), s.getTotal()});

        }


    }

    //姓名查询
    public void fillTableByName() {
        /***
         * 根据输入框内容调用数据库查询
         * 将查询的值加入table显示
         */
        com.student.domain.Student s = studentServiceImpl.getStudentByName(field1.getText());

        if (s != null) {

            clearTable();
            dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore()});
//            }

        } else {
            JOptionPane.showMessageDialog(frame, "查询不到结果!", "", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    public void fillTableById() {

        /***
         * 根据输入框内容调用数据库查询
         * 将查询的值加入table显示
         */
//        Student s = new StudentDao().queryById(field2.getText());

        com.student.domain.Student s = studentServiceImpl.getStudentById(field2.getText());
        if (s != null) {
            clearTable();
            dtm.addRow(new Object[]{s.getId(), s.getName(), s.getSex(), s.getAge(), s.getJavascore(), s.getGaoshuscore(), s.getEnglishscore()});
        } else {
            JOptionPane.showMessageDialog(frame, "查询不到结果!", "", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    //清除表格
    public void clearTable() {
        dtm.getDataVector().clear();
        dtm.fireTableDataChanged();//更新

    }


    //比较器排序
    public class MyComparator implements Comparator<Student> {
        @Override
        public int compare(Student stu1, Student stu2) {

/**
 *    方法参数i1在i2前面，则理解成此方法定义的规则是i1 < i2
 *        此时若我们要定义升序，则使此方法的返回值为小于0，则可return i1 - i2；
 *        此时若我们要定义降序，则使此方法的返回值为大于0，则可return i2 - i1；
 */
            int v = (int) (stu2.getTotal() - stu1.getTotal());
            return v;

        }


    }

    public void average(ArrayList<Student> students) {

        double javaScoreAvg = studentServiceImpl.getJavaScoreAvg();
        double gaoshuScoreAvg = studentServiceImpl.getGaoshuScoreAvg();
        double englishScoreAvg = studentServiceImpl.getEnglishScoreAvg();
        this.average1 = javaScoreAvg;
        this.average2 = gaoshuScoreAvg;
        this.average3 = englishScoreAvg;

    }

    public void ExcellentRates(ArrayList<Student> students) {

        //java
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < students.size(); i++) {
            count1++;//总数


            if (students.get(i).getJavascore() >= 90) {
                count2++;//优秀人数

            }
        }
        double j = (double) count2 / count1;
        this.rate1 = (double) Math.round(j * 100) / 100;

        //高数
        int g_count1 = 0;
        int g_count2 = 0;
        for (int i = 0; i < students.size(); i++) {
            g_count1++;

            if (students.get(i).getGaoshuscore() >= 90) {
                g_count2++;

            }
        }
        double g = (double) g_count2 / g_count1;
        this.rate2 = (double) Math.round(g * 100) / 100;

        //英语
        int e_count1 = 0;
        int e_count2 = 0;
        for (int i = 0; i < students.size(); i++) {
            e_count1++;

            if (students.get(i).getEnglishscore() >= 90) {
                e_count2++;

            }
        }
        double e = (double) e_count2 / e_count1;
        this.rate3 = (double) Math.round(e * 100) / 100;
        // System.out.println("java优秀率："+javarate+"\t"+"高数优秀率："+gaoshurate+"\t"+"英语优秀率："+englishrate);

    }

    public void max(ArrayList<Student> students) {
        /**
         * 先令最大值为0
         * 遍历比较得到最大值
         */
        double javaMax = studentServiceImpl.getJavaMax();
        double gaoshuMax = studentServiceImpl.getGaoshuMax();
        double englishMax = studentServiceImpl.getEnglishMax();

        this.max1 = javaMax;
        this.max2 = gaoshuMax;
        this.max3 = englishMax;
        // System.out.println("java成绩最高分："+maxjava+"\t"+"高数最高分："+maxgaoshu+"\t"+"英语最高分"+maxenglish);

    }


    public void min(ArrayList<Student> students) {

        /**
         * 取第一个数据为初始值
         * 与后面数据比较得到最小值
         */
        double javaMin = studentServiceImpl.getJavaMin();
        double gaoshuMin = studentServiceImpl.getGaoshuMin();
        double englishMin = studentServiceImpl.getEnglishMin();

        this.min1 = javaMin;
        this.min2 = gaoshuMin;
        this.min3 = englishMin;
        //  System.out.println("java成绩最低分："+minjava+"\t"+"高数最低分："+mingaoshu+"\t"+"英语最低分"+minenglish);

    }

    /****
     * main
     * 用于测试
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();//启用BeautyEyeLNF
                    UIManager.put("RootPane.setupButtonVisible", false);

                    new Main().MainTest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}



