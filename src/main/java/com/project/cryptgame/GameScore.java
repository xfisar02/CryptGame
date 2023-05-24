package com.project.cryptgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author fisar
 */public class GameScore {

    GameScore gameScore;

    static class Player {

        String name;
        int score;

        public int getScore() {
            return score;
        }

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\t Score: " + score;
        }

        public void setScore(int score) {
            this.score = score;
        }

    }

    static List<Player> listPlayers = new ArrayList<>();
    static Player active;

    static void addPlayer(String name, int score) {
        active = new Player(name, score);
        listPlayers.add(active);
    }

    static int selectPlayer(String name) {
        int index = -1;
        for (int i = 0; i < listPlayers.size(); i++) {
            Player get = listPlayers.get(i);
            if (get.name.equalsIgnoreCase(name)) {
                index = i;
            }
        }
        return index;
    }

    static class PlayerRankingScoreComparator implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return Integer.compare(o1.getScore(), o2.getScore());
        }

    }

    static void sortForScore() {
        PlayerRankingScoreComparator prscom = new PlayerRankingScoreComparator();
        Collections.sort(listPlayers, prscom);
        Collections.reverse(listPlayers);
    }

    static void showListPlayers() throws FileNotFoundException {
        String s = "";
        for (int i = 0; i < listPlayers.size(); i++) {
            Player get = listPlayers.get(i);
            s += get + "\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leaderboard");
        ImageView imageView = new ImageView(new Image(GameScore.class.getResourceAsStream("pictures/leaderboard.png")));
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        alert.getDialogPane().setGraphic(imageView);
        alert.setHeaderText(
                "Active Player:\t\t" + ((active == null) ? "none" : active));
        alert.setContentText("TOP 10 Score:\n\n" + s);
        alert.showAndWait();
    }

    static void setClearListPlayersonTen() {
        for (int i = listPlayers.size() - 1; i >= 10; i--) {
            Player get = listPlayers.remove(i);
            System.out.println("remove:\t" + get);
        }
    }

    static void loadScoreFile() {
        try {
            Scanner sc = new Scanner(GameScore.class.getResourceAsStream("data/dataScore.txt"), "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Scanner scLine = new Scanner(line);
                String name = scLine.next();
                int score = scLine.nextInt();
                listPlayers.add(new Player(name, score));
            }
        } catch (Exception e) {
            System.out.println("not loaded... " + e);
        }
    }

    static void saveScoreFile() {
        try {
            System.out.println("save start...");
            var url = GameScore.class.getResource("data/dataScore.txt");
            File file = new File(url.getFile());
            if (file.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                System.out.println("save continue...");
                for (int i = 0; i < listPlayers.size(); i++) {
                    Player getPlayer = listPlayers.get(i);
                    String s = getPlayer.name + "\t" + getPlayer.score + "\n";
                    bw.write(s);
                }
                bw.close();
            }
            System.out.println("save end...");
        } catch (Exception e) {
            System.out.println("not saved....");
        }
    }
}

