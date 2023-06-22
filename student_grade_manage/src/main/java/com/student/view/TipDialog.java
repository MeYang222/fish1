package com.student.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.student.view.Main.*;


/***
 * 优秀率
 * 最值
 * 平均数
 * 窗口
 */


public class TipDialog extends JDialog {
    //优秀率窗口
    public TipDialog(Frame frame) {
        super(frame, "优秀率", true);
        Container contentPane = getContentPane();
        JPanel contentPanel = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPane.add(contentPanel, BorderLayout.CENTER);

        setSize(300, 250);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 5, 5));
            ImageIcon bg = new ImageIcon("src/image/bg3.jpg");
            JLabel label = new JLabel(bg);
            label.setSize(bg.getIconWidth(), bg.getIconHeight());
            p.add(label);
            JLabel area = new JLabel("查询成功！");

            area.setForeground(Color.RED);
            p.add(area);
            contentPanel.add(p, BorderLayout.WEST);

        }

        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 0, 20));
            JLabel bel = new JLabel( "java优秀率为：" + rate1);
            //bel.setEditable(false);
            bel.setForeground(Color.BLUE);
            p.add(bel);

            JLabel bel1 = new JLabel( "高数优秀率为：" + rate2);
           // bel1.setEditable(false);
            bel1.setForeground(Color.MAGENTA);
            p.add(bel1);

            JLabel bel2 = new JLabel( "英语优秀率为：" + rate3);
          //  bel2.setEditable(false);
            bel2.setForeground(Color.BLUE);
            p.add(bel2);
            contentPanel.add(p, BorderLayout.EAST);
        }
        setVisible(true);
    }

    //平均值窗口
    public TipDialog(Frame frame, Frame frame1) {
        super(frame1, "平均值", true);
        Container contentPane = getContentPane();
        JPanel contentPanel = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPane.add(contentPanel, BorderLayout.CENTER);

        setSize(300, 250);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 5, 5));
            ImageIcon bg = new ImageIcon("src/image/bg4.jpg");
            JLabel label = new JLabel(bg);
            label.setSize(bg.getIconWidth(), bg.getIconHeight());
            p.add(label);
            JLabel area = new JLabel("查询成功！");

            area.setForeground(Color.RED);
            p.add(area);
            contentPanel.add(p, BorderLayout.WEST);

        }

        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 0, 20));
            JLabel bel = new JLabel("java平均值为：" + average1);
            bel.setForeground(Color.BLUE);
            //bel.setEditable(false);
            p.add(bel);

            JLabel bel1 = new JLabel("高数平均值为：" + average2);
            bel1.setForeground(Color.MAGENTA);
            //bel1.setEditable(false);
            p.add(bel1);

            JLabel bel2 = new JLabel("英语平均值为：" + average3);
            bel2.setForeground(Color.BLUE);
           // bel2.setEditable(false);
            p.add(bel2);
            contentPanel.add(p, BorderLayout.EAST);
            setVisible(true);
        }
    }

    //最值窗口
    public TipDialog(Frame frame, Frame frame1, Frame frame2) {
        super(frame2, "最大最小值", true);
        Container contentPane = getContentPane();
        JPanel contentPanel = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPane.add(contentPanel, BorderLayout.CENTER);

        setSize(300, 280);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 5, 5));
            ImageIcon bg = new ImageIcon("src/image/bg5.jpg");
            JLabel label = new JLabel(bg);
            label.setSize(bg.getIconWidth(), bg.getIconHeight());
            p.add(label);
            JLabel area = new JLabel("查询成功！");

            area.setForeground(Color.RED);
            p.add(area);
            contentPanel.add(p, BorderLayout.WEST);

        }
        {
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(0, 1, 0, 5));
            JLabel bel = new JLabel("java最大值为：" + max1);
            bel.setForeground(Color.BLUE);
            // bel.setEditable(false);
            p.add(bel);

            JLabel bel1 = new JLabel("高数最大值为：" + max2);
            bel1.setForeground(Color.MAGENTA);
           // bel1.setEditable(false);

            p.add(bel1);

            JLabel bel2 = new JLabel("英语最大值为：" + max3);
            bel2.setForeground(Color.BLUE);
           // bel2.setEditable(false);
            p.add(bel2);

            JLabel bel3 = new JLabel("java最小值为：" + min1);
            bel3.setForeground(Color.MAGENTA);
           // bel3.setEditable(false);
            p.add(bel3);

            JLabel bel4 = new JLabel("高数最小值为：" + min2);
            bel4.setForeground(Color.BLUE);
           // bel4.setEditable(false);
            p.add(bel4);

            JLabel bel5 = new JLabel("英语最小值为：" + min3);
            bel5.setForeground(Color.MAGENTA);
           // bel5.setEditable(false);
            p.add(bel5);
            contentPanel.add(p, BorderLayout.EAST);
        }
        setVisible(true);
    }
}

