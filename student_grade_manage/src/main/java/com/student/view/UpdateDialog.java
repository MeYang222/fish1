package com.student.view;

import com.student.dao.StudentDao;
import com.student.entity.Student;
import com.student.service.Impl.StudentServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.student.view.Main.row;


public class UpdateDialog extends JDialog {
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
  ArrayList<Student> students=new StudentDao().queryAll();

    String id=Main.id;


    private final JPanel contentPanel = new JPanel();
    private static final long serialVersionUID = 1L;

    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    /**
     * Create the dialog.
     */
    public UpdateDialog(Frame frame) {

        super(frame, "修改学生信息" , true);
        setSize(250,350);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.WEST);
            panel.setLayout(new GridLayout(0, 1, 0, 0));
            {
                /*JLabel label = new JLabel("学号：");
                panel.add(label);*/
            }
            {
                JLabel label = new JLabel("姓名：");
                panel.add(label);
            }
            {
                JLabel label = new JLabel("性别：");
                panel.add(label);
            }
            {
                JLabel label = new JLabel("年龄：");
                panel.add(label);
            }
            {
                JLabel label = new JLabel("java：");
                panel.add(label);
            }
            {
                JLabel label = new JLabel("高数：");
                panel.add(label);
            }
            {
                JLabel label = new JLabel("英语：");
                panel.add(label);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(0, 1, 0, 10));

            {
                textField_1 = new JTextField();
                textField_1.setText(students.get(row).getName());
                panel.add(textField_1);
                textField_1.setColumns(10);
            }
            {
                textField_2 = new JTextField();
                textField_2.setText(students.get(row).getSex());
                panel.add(textField_2);
                textField_2.setColumns(10);
            }
            {
                textField_3 = new JTextField();
                textField_3.setText(students.get(row).getAge());
                panel.add(textField_3);
                textField_3.setColumns(10);
            }
            {
                textField_4 = new JTextField();
                textField_4.setText(String.valueOf((Double) students.get(row).getJavascore()));
                textField_4.setColumns(10);
                panel.add(textField_4);
            }
            {
                textField_5 = new JTextField();
                textField_5.setText(String.valueOf((Double) students.get(row).getGaoshuscore()));
                textField_5.setColumns(10);
                panel.add(textField_5);
            }
            {
                textField_6 = new JTextField();
                textField_6.setText(String.valueOf((Double) students.get(row).getEnglishscore()));
                textField_6.setColumns(10);
                panel.add(textField_6);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        /***
                         * 修改学生信息
                         * 1.将该id的信息读取出来
                         * 调用dao层添加
                         */





                        // Student s=new Student();
                        com.student.domain.Student s = new com.student.domain.Student();
                        //if(textField.getText()==null)
                        s.setId(id);

                        s.setName(textField_1.getText());
                        s.setSex(textField_2.getText());
                        s.setAge(textField_3.getText());
                        s.setJavascore(Double.parseDouble(textField_4.getText()));
                        s.setGaoshuscore(Double.parseDouble(textField_5.getText()));
                        s.setEnglishscore(Double.parseDouble(textField_6.getText()));

//                        Main.stu = s;

                        System.out.println(s);
                        studentServiceImpl.updateStudent(s);
                        System.out.println(s.getId() + s.getName()+s.getAge()+s.getEnglishscore());
                        dispose();
                    }

                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
        setVisible(true);
    }

}

