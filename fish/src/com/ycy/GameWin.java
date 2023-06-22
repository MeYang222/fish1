package com.ycy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    /*游戏状态
    * 0 未开始
    * 1 游戏中
    * 2 通关失败
    * 3 通关成功
    * 4 暂停
    * */

    //游戏默认状态
    static int state = 0;

    //双缓存解决文字闪问题
    Image offScreenImage;

    //宽高
    int width = 1440;
    int height = 900;

    double random;
    //计数器
    int time = 0;

    //背景
    Bg bg = new Bg();
    //敌方鱼类
    Enamy enamy;
    //boss类
    Enamy boss;
    //是否生成boss
    boolean isboss = false;
    //我方鱼类
    MyFish myFish = new MyFish();


    //设置一个方法
    public void launch(){
        //设置可见
        this.setVisible(true);
        //设置大小
        this.setSize(width,height);
        //设置居中位置
        this.setLocationRelativeTo(null);
        //设置窗口不可改变
//        this.setResizable(false);
        //设置标题
        this.setTitle("大鱼吃小鱼");
        //关闭窗口按钮
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //鼠标点击事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //判断进入游戏运行状态
                if(e.getButton()==1&&state==0){
                    state=1;
                    repaint();
                }
                if(e.getButton()==1&&(state==2||state==3)){
                    reGame();
                    state = 1;
                }

            }
        });

        //键盘移动
        this.addKeyListener(new KeyAdapter() {
            /*
            * W 87 S 83 A 65 D 68
            * */
            @Override//按压
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==87) {
                    GameUtils.UP = true;
                }
                if(e.getKeyCode()==83){
                    GameUtils.DOWN = true;
                }
                if(e.getKeyCode()==65){
                    GameUtils.LEFT = true;
                }
                if(e.getKeyCode()==68){
                    GameUtils.RIGHT = true;
                }
                //空格键为32
                if(e.getKeyCode()==32){
                    switch (state){
                        case 1:
                            state =4;
                            GameUtils.drawWord(getGraphics(),"游戏暂停!!",Color.red,50,600,400);
                            break;
                        case 4:
                            state = 1;
                            break;
                    }
                }
            }
            @Override//抬起
            public void keyReleased(KeyEvent e){
                super.keyReleased(e);
                if(e.getKeyCode()==87) {
                    GameUtils.UP = false;
                }
                if(e.getKeyCode()==83){
                    GameUtils.DOWN = false;
                }
                if(e.getKeyCode()==65){
                    GameUtils.LEFT = false;
                }
                if(e.getKeyCode()==68){
                    GameUtils.RIGHT = false;
                }
            }
        });


        while (true){
            repaint();
            time++;
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //重写paint方法
    @Override
    public void paint(Graphics g){
        //类加载模式初始化对象
        offScreenImage = createImage(width,height);
        Graphics gImage = offScreenImage.getGraphics();
        bg.paintSelf(gImage,myFish.level);

        switch(state){
            case 0:
                break;
            case 1:
                myFish.paintSelf(gImage);
                logic();
                for(Enamy enamy:GameUtils.EnamyList){
                    enamy.paintSelf(gImage);
                }
                if(isboss){
                    boss.x = boss.x+boss.dir*boss.speed;
                    boss.paintSelf(gImage);
                    if(boss.x<0){
                        gImage.setColor(Color.red);
                        gImage.fillRect(boss.x,boss.y,2400,boss.height/30);
                    }
                }
                break;
            case 2:
                for(Enamy enamy:GameUtils.EnamyList){
                    enamy.paintSelf(gImage);
                }
                break;
            case 3:
                myFish.paintSelf(gImage);
                break;
            case 4:
                return;
            default:
        }
        g.drawImage(offScreenImage,0,0,null);
    }

    //窗口类
    void logic() {
        //关卡难度
        if (GameUtils.count < 5) {
            GameUtils.level = 0;
            myFish.level = 1;
        } else if (GameUtils.count <= 15) {
            GameUtils.level = 1;
        } else if (GameUtils.count <= 50) {
            GameUtils.level = 2;
            myFish.level = 2;
        } else if (GameUtils.count <= 150) {
            GameUtils.level = 3;
            myFish.level = 3;
        } else if (GameUtils.count <= 300) {
            GameUtils.level = 4;
            myFish.level = 3;
        } else if (GameUtils.count > 300) {
            state = 3;
        }

        random = Math.random();


        switch (GameUtils.level) {
            case 4:
                if(time%60==0){
                    if(random>0){
                        boss = new Enamy_Boss();
                        isboss = true;
                    }
                }
            case 3:
            case 2:
                if(time%30 == 0){
                    if(random>0.5){
                        enamy = new Enamy_3_L();
                    }else {
                        enamy = new Enamy_3_R();
                    }
                    GameUtils.EnamyList.add(enamy);
                }
            case 1:
                if(time%20==0){
                    if(random>0.5){
                        enamy = new Enamy_2_L();
                    }else {
                        enamy = new Enamy_2_R();
                    }
                    GameUtils.EnamyList.add(enamy);
                }
            case 0:
            if (time % 10 == 0) {
                if (random > 0.5) {
                    enamy = new Enamy_1_L();
                } else {
                    enamy = new Enamy_1_R();
                }
                GameUtils.EnamyList.add(enamy);
            }
            break;
            default:
        }
        //移动方向
        for(Enamy enamy:GameUtils.EnamyList){
            enamy.x = enamy.x+enamy.dir*enamy.speed;
            if(isboss){
                if(boss.getRec().intersects(enamy.getRec())){
                    enamy.x = -200;
                    enamy.y = -200;
                }
                if(boss.getRec().intersects(myFish.getRec())){
                    state = 2;
                }
            }
            //我方鱼与敌方鱼碰撞检测
            if(myFish.getRec().intersects(enamy.getRec())) {
                if (myFish.level >= enamy.type) {
                    System.out.println("碰撞了");
                    enamy.x = -200;
                    enamy.y = -200;
                    GameUtils.count = GameUtils.count + enamy.count;
                }else {
                    state = 2;

                }
            }

        }
    }

    //重新开始
    void reGame(){
        GameUtils.EnamyList.clear();
        time = 0;
        myFish.level = 1;
        GameUtils.count = 0;
        myFish.x = 700;
        myFish.y = 500;
        myFish.width = 50;
        myFish.height = 50;
        boss = null;
        isboss = false;
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
