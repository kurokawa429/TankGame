package org.zjx.tankgame;

import lombok.NoArgsConstructor;

import javax.swing.*;

public class ZjxTankGame01 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        ZjxTankGame01 zjxTankGame01 = new ZjxTankGame01();

    }

    public ZjxTankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
