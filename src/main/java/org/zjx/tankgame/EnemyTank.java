package org.zjx.tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    //增加成员，EnemyTank可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //判断当前坦克，是否和enemyTanks中的其他坦克发生碰撞
    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            case 0://上
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中取出第一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        //敌人坦克向上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                        }
                        //敌人坦克向左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }


                        }


                    }
                }
                break;
            case 1://右
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中取出第一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        //敌人坦克向上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                        }
                        //敌人坦克向左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中取出第一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        //敌人坦克向上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                        }
                        //敌人坦克向左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中取出第一个坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (this != enemyTank) {
                        //敌人坦克向上/下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60
                            ) {
                                return true;
                            }
                        }
                        //敌人坦克向左/右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40
                            ) {
                                return true;
                            }
                        }
                    }
                }
                break;

        }


        return false;
    }

    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            if (isLive && shots.size() == 0) {
                Shot s = null;

                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }


            //根据坦克方向继续移动
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && ! isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000 && ! isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && ! isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && ! isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }
                    break;
            }

            //随机改变坦克方向
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                break;
            }
        }
    }
}
