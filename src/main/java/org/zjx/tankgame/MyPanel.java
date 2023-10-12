package org.zjx.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel implements KeyListener ,Runnable{
    //定义我的坦克
    Hero hero = null;

    //定义敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();

    int enemyTankSize = 3;

    public MyPanel() {
        //初始化自己坦克
        hero = new Hero(100, 100);
        hero.setSpeed(10);
        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);

        //画出坦克，封装方法
        //自己坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

        //画出hero发出的子弹
        if(hero.shot != null && hero.shot.isLive){
            g.fill3DRect(hero.shot.x, hero.shot.y, 3, 3, false);
        }


        //敌人坦克,遍历Vector
        for (int i = 0; i < enemyTankSize; i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g , enemyTank.getDirect(), 0);
        }
    }




    /**
     * @param x      坦克左上角x坐标
     * @param y      坦克左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型的坦克设置不同的颜色
        switch (type) {
            //敌人的坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            //我们的坦克
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克的方向绘制坦克
        //direct表示方向（0：向上 1：向右 2：向下 3：向左）
        switch (direct) {
            //向上
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            //向右
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时不处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wasd按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            //改变坦克的方向
            hero.setDirect(0);
            //修改坦克坐标
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
        //如果按下J,就发射子弹
        if(e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemyTank();
        }


        //面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }

    }
}
