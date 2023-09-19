package org.zjx.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel {
    //定义我的坦克
    Hero hero = null;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);

        //画出坦克，封装方法
        //自己坦克
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
        //敌人坦克
//        drawTank(hero.getX() + 60, hero.getY(), g, 0, 1);
    }

    public MyPanel(){
        hero = new Hero(100, 100);
    }


    /**
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direct 坦克方向（上下左右）
     * @param type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        //根据不同类型的坦克设置不同的颜色
        switch (type){
            //我们的坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            //敌人的坦克
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克的方向绘制坦克
        switch (direct){
            //向上
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            default:
                System.out.println("暂时不处理");
        }
    }
}
