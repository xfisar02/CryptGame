package com.project.cryptgame;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class PasswdCrack extends CiphersAbstract {

    String key;
    Set<Character> characters = new HashSet<>();
    Set<Character> charTemp = new HashSet<>();

    public PasswdCrack(Resource res) {
        super(res);

        int index = (int) Math.round(Math.random()
                * (res.cryptResource.size() - 1));
        key = res.cryptResource.get(index);
    }

    /*
    Inner class for finding matching characters of guessed word and answer(key)
    */
    class CharMatchPasswd {

        int count;
        Set<Character> characters;

        public CharMatchPasswd(int count, Set<Character> characters) {
            this.count = count;
            this.characters = characters;
        }

        @Override
        public String toString() {
            return "CharMatchPasswd{" + "count="
                    + count + ", characters="
                    + characters + '}';
        }
    }

    public CharMatchPasswd findCharMatch(TextArea consoleInfTextArea, TextField consoleInTextField) {
        charTemp.clear();
        if (key.length() == consoleInTextField.getText().length()) {
            for (int i = 0; i < key.length(); i++) {
                char charPasswd = key.charAt(i);
                for (int j = 0; j < consoleInTextField.getText().length(); j++) {
                    char charIn = consoleInTextField.getText().charAt(j);
                    charIn = Character.toUpperCase(charIn);
                    if (charPasswd == charIn) {
                        charTemp.add(charPasswd); //add char to Set for matched characters
                        break;
                    }
                }
            }
            characters.addAll(charTemp);
            CharMatchPasswd szk = new CharMatchPasswd(charTemp.size(), charTemp);
            return szk;
        } else {
            CharMatchPasswd szk = new CharMatchPasswd(-1, charTemp);
            return szk;
        }
    }

    @Override
    public boolean insertAnswer(TextArea consoleOutTextArea, TextArea consoleInfTextArea, TextField consoleInTextField) {
        if (consoleInTextField.getText().equalsIgnoreCase(key)) {
            consoleInfTextArea.setText("");
            return true;
        } else {
            CharMatchPasswd cmp = findCharMatch(consoleInfTextArea, consoleInTextField);
            consoleInfTextArea.appendText(consoleInTextField.getText()
                    + "\t\t\t"
                    + cmp.count
                    + "\n");
            return false;
        }
    }

    @Override
    public void prepareOutArea(TextArea consoleOutTextArea) {
        for (int i = 0; i < res.cryptResource.size(); i++) {
            String get = res.cryptResource.get(i);
            consoleOutTextArea.appendText(" [" + get + "] ");
        }
    }

    @Override
    public String task() {
        String task = "";
        task = "WELCOME TO PASSWORD CRACKING!\n\nOne of these is a correct password,"
                + " you need to guess which one. As soon as you write one down to console line and confirm it with enter,"
                + " you will se how many letters of guessed word are in the correct password (please capitalize each letter).\n"
                + "If you have trouble guessing, press \"?\" (ONLY AFTER A FEW MATCHED LETTERS!) button in the right corner of the screen...\n\n";
        System.out.println("Answer " + key);
        return task;
    }

    @Override
    public String guess() {
        String guess = "";
        guess = "GUESSED\t\t\tMATCH\n\n";
        return guess;
    }

    @Override
    public String win() {
        String solved = "";
        solved = "CONGRATULATIONS, THE PASSWORD HAS BEEN CRACKED!!!\nPress green button for next cipher.";
        return solved;
    }

    @Override
    public String encrypt() {
        String encrypt = "not used there";
        return encrypt;
    }

    @Override
    public boolean solve(String answer) {
        boolean solve = false;

        return solve;
    }

    @Override
    public String hint() {
        String hint = "";
        hint = "Matched characters of your guessed words are:\n" + characters;
        return hint;
    }

}
