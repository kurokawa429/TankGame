package org.zjx.tankgame;

public class Shot implements Runnable{
    int x;
    int y;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    int direct = 0;
    int speed = 2;

    boolean isLive = true;
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据方向改变x，y坐标
            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            if(!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)){
                isLive = false;
                break;
            }
            System.out.println("x = " + x + "y = " + y);
        }
    }
}
