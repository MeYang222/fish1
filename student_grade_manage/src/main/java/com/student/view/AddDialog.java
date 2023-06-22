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

/**
 * 添加窗口
 */
public class AddDialog extends JDialog {
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
    private final JPanel contentPanel = new JPanel();
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;


    public AddDialog(Frame frame) {


        super(frame, "添加学生信息", true);
        setSize(250, 350);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //setBounds(100, 100, 270, 354);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 25, 5, 25));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 10));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.WEST);
            panel.setLayout(new GridLayout(0, 1, 0, 0));
            {
                JLabel label = new JLabel("学号：");
                panel.add(label);
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
                textField = new JTextField();
                panel.add(textField);
                textField.setColumns(10);
            }
            {
                textField_1 = new JTextField();
                panel.add(textField_1);
                textField_1.setColumns(10);
            }
            {
                textField_2 = new JTextField();
                panel.add(textField_2);
                textField_2.setColumns(10);
            }
            {
                textField_3 = new JTextField();
                panel.add(textField_3);
                textField_3.setColumns(10);
            }
            {
                textField_4 = new JTextField();
                textField_4.setColumns(10);
                panel.add(textField_4);
            }
            {
                textField_5 = new JTextField();
                textField_5.setColumns(10);
                panel.add(textField_5);
            }
            {
                textField_6 = new JTextField();
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
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });


                okButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        /**
                         * 1.先判断输入框的值不为空（为空提示，不为空 2.）
                         * 2.判断输入的id是否存在（存在提示，不存在调用方法添加）
                         */


                        com.student.domain.Student s = new com.student.domain.Student();


                        if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || textField_3.getText().isEmpty() || textField_4.getText().isEmpty() || textField_5.getText().isEmpty() || textField_6.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(frame, "输入不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                        } else {
                            ArrayList<Student> students = new StudentDao().queryById(textField.getText());
                            System.out.println(students);
                            if (students == null) {
                                s.setId((textField.getText()));
                                s.setName(textField_1.getText());
                                s.setSex(textField_2.getText());
                                s.setAge(textField_3.getText());
                                s.setJavascore(Double.parseDouble(textField_4.getText()));
                                s.setGaoshuscore(Double.parseDouble(textField_5.getText()));
                                s.setEnglishscore(Double.parseDouble(textField_6.getText()));


                                studentServiceImpl.addStudent(s);
//                                Main.stu = s;
                                // System.out.println(s.getId() + s.getName());
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(frame, "学号已存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
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
