package com.project.cryptgame;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 *
 * @author fisar
 */
public class Resource {
    EnumDifficulty difficulty;
    List<String> cryptResource;
    List<String> cryptWholeRes;
    public Resource(EnumDifficulty difficulty) {
        this.difficulty = difficulty;
        if (cryptWholeRes == null) {
            loadWholeData();
        }
        switch(difficulty){
            case EASY:
                loadData(5);
                break;
            case MEDIUM:
                loadData(10);
                break;
            case HARD:
                loadData(20);
                break;
        }
    }

    public void loadWholeData() {
        cryptWholeRes = new ArrayList<>();
        Scanner sc = new Scanner(GameController.class.getResourceAsStream("data/wordVomit.txt"), "UTF-8");
        while (sc.hasNext()) {
            String word = sc.next();
            cryptWholeRes.add(word);
        }
    }

    public void loadData(int difficulty) {
        Collections.shuffle(cryptWholeRes);
        cryptResource = new ArrayList<>();
        ListIterator<String> getThrowAll = cryptWholeRes.listIterator();
        while (getThrowAll.hasNext() && cryptResource.size() <= difficulty) {
            String word = getThrowAll.next();
            cryptResource.add(word);
        }
    }
}

