package com.student;


import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import com.student.view.Login;
import java.awt.*;

/**
 * 此类开始运行程序
 */
public class App {
    public static void main(String[] args) {
//引用美化包
        EventQueue.invokeLater(() -> {
            try {
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
                BeautyEyeLNFHelper.launchBeautyEyeLNF();//启用BeautyEyeLNF
                // UIManager.put("RootPane.setupButtonVisible",false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
