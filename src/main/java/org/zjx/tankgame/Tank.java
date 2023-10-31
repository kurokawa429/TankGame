package org.zjx.tankgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tank {
    //坦克横坐标
    private int x;

    //坦克纵坐标
    private int y;

    //坦克的方向
    private int direct;

    //坦克的速度
    private int speed = 1;

    //坦克是否存活
    boolean isLive = true;
    //上下左右移动方法
    public void moveUp(){
        y -= speed;
    }

    public void moveRight(){
        x += speed;
    }

    public void moveDown(){
        y += speed;
    }

    public void moveLeft(){
        x -= speed;
    }

    public Tank(int x, int y){
        this.x = x;
        this.y = y;
    }
}
