package org.zjx.tankgame;

import lombok.Getter;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static BufferedWriter bw = null;

    private static BufferedReader br = null;
    private static String recordFile = "d:\\myRecord.txt";

    @Getter
    private static int allEnemyTankNum = 0;

    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    private static Vector<EnemyTank> enemyTanks = null;

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static Vector<Node> getNodesAndEnemyTankRec() {

        try (BufferedReader bufferedReader = br = new BufferedReader(new FileReader(recordFile))) {

            allEnemyTankNum = Integer.parseInt(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return nodes;
    }

    public static void keepRecord() throws IOException {


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(recordFile))) {
            bw.write(String.valueOf(allEnemyTankNum));
            bw.newLine();
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(String.valueOf(record));
                    bw.newLine();
                }
            }


        }


    }

    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
