package org.zjx.tankgame;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class ZjxTankGame01 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ZjxTankGame01 zjxTankGame01 = new ZjxTankGame01();

    }

    public ZjxTankGame01() {
        System.out.println("1：新游戏  2：继续上局游戏");
        String key = sc.next();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Recorder.keepRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }
}
