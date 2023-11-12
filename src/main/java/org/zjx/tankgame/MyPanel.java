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

    Vector<Node> nodes = new Vector<>();
    //定义Vector，存放Bomb
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;
    //定义三张炸弹图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {

        Recorder.setEnemyTanks(enemyTanks);
        //初始化自己坦克
        hero = new Hero(100, 500);
        hero.setSpeed(10);

        switch (key){
            case "1":
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(2);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给敌人坦克加一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                nodes = Recorder.getNodesAndEnemyTankRec();
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给敌人坦克加一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入有误");
        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.PNG"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.PNG"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.PNG"));
    }

    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);
        //画出坦克，封装方法
        //自己坦克

        if(hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        //画出hero发出的子弹
        if(hero.shot != null && hero.shot.isLive){
            g.fill3DRect(hero.shot.x, hero.shot.y, 3, 3, false);
        }

        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if(shot != null && shot.isLive){
                g.fill3DRect(shot.x, shot.y, 3, 3, false);
            }else {
                hero.shots.remove(shot);
            }

        }


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //如果bombs中有对象，画出炸弹
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.life > 6){
                g.drawImage(image1, bomb.x, bomb.y, 60, 60 ,this);
            }else if(bomb.life > 3){
                g.drawImage(image2, bomb.x, bomb.y, 60, 60 ,this);
            }else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60 ,this);
            }
            bomb.lifeDown();
            if(bomb.life == 0){
                bombs.remove(bomb);
            }
        }


        //敌人坦克,遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断坦克是否存活
            if(enemyTank.isLive){
                drawTank(enemyTank.getX(), enemyTank.getY(), g , enemyTank.getDirect(), 0);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if(shot.isLive){
                        g.draw3DRect(shot.x, shot.y, 2, 2,false);
                    }else {
                        //从Vector删除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
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

    public void hitEnemyTank(){

        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            //判断是否击中
        if(shot != null && shot.isLive){
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(shot, enemyTank);
            }
        }
    }
    }

    public void hitHero(){
        //遍历所有敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中了我们的坦克
                if(hero.isLive && shot.isLive){
                    hitTank(shot, hero);
                }

            }


        }
        
    }


    //编写方法，判断子弹是否击中
    public void hitTank(Shot s, Tank enemyTank){
        //判断s击中坦克
        switch (enemyTank.getDirect()){
            case 0:
            case 2:
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //当子弹击中坦克，从集合里移除
                    enemyTanks.remove(enemyTank);
                    enemyTankSize--;
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }

                    //加入Bomb到bombs集合中
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //当子弹击中坦克，从集合里移除
                    enemyTanks.remove(enemyTank);
                    enemyTankSize--;

                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //加入Bomb到bombs集合中
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
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
            if(hero.getY() > 0){
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if(hero.getX() +60 < 1000){
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if(hero.getY() + 60 < 750){
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if(hero.getX() > 0){
                hero.moveLeft();
            }
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

            hitEnemyTank();
            //判断敌人坦克是否击中我们
            hitHero();
            this.repaint();
        }

    }
}
