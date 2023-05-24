package com.project.cryptgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class CaesarCipher extends CiphersAbstract {


    int offset;
    Map<Integer, Character> alphabet;
    Resource res;

    public CaesarCipher(Resource res) {
        super(res);
        alphabet = generateAlphabet();
        offset = generateOffset();
        encrypedKey = encrypt();
    }

    private Map<Integer, Character> generateAlphabet() {
        Map<Integer, Character> alphabet = new HashMap<>();
        int ascii = 65 - 1;
        for (int i = 0; i <= 25; i++) {
            ++ascii;
            alphabet.put(i, (char) ascii);
        }
        return alphabet;
    }

    //random int number for offset
    private int generateOffset() {
        Random r = new Random();
        int offset = r.nextInt(25 - 1) + 1;
        return offset;
    }

    //String cointaining aplhabet
    private String showAlphabet() {
        StringBuilder alphabet = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.append(c).append(" ");
        }
        return alphabet.toString();
    }

    @Override
    public boolean solve(String answer) {
        boolean solve = false;
        if (answer.equalsIgnoreCase(key)) {
            solve = true;
        } else {
            solve = false;
        }
        return solve;
    }

    @Override
    public String encrypt() {
        String encrypt = "";
        for (int i = 0; i < key.length(); i++) {
            char object = key.charAt(i);
            int ascii = object + offset;    //move each letter in aplhabet depending on the offset
            if (ascii > 90) {   //fix alphabet overflow
                int dif = ascii - 90 - 1;
                ascii = 65 + dif;
            }
            encrypt += (char) ascii;
        }
        return encrypt;
    }

    @Override
    public String task() {
        String task = "";
        task += "WELCOME TO CEASAR CIPHER!\n\nBellow this message you will see an encrypted word,"
                + " your task is to decipher this word."
                + " As soon as you write down your answer to console line and confirm it with enter,"
                + " you will se if your answer is correct (please capitalize each letter).\n"
                + "If you have trouble guessing, press \"?\" button in the right corner of the screen...\n\n";
        task += "Alphabet:\n" + showAlphabet() + "\n\n";
        System.out.println("Answer " + key);
        return task;
    }

    @Override
    public String guess() {
        String hint = "";
        hint += "GUESSED\n\n";
        return hint;
    }

    @Override
    public String win() {
        String win = "";
        win = "CONGRATULATIONS, THE CAESAR CIPHER HAS BEEN CRACKED!!!\nPress green button for next cipher.";
        return win;
    }

    @Override
    public boolean insertAnswer(TextArea consoleOutTextArea, TextArea consoleInfTextArea, TextField consoleInTextField) {
        if (consoleInTextField.getText().equalsIgnoreCase(key)) {
            consoleInfTextArea.setText("");
            return true;
        } else {
            consoleInfTextArea.appendText(consoleInTextField.getText() + "\n");
            return false;
        }
    }

    @Override
    public String hint() {
        String hint = "";
        hint = "The offset of the correct answer in the alphabet is: " + offset;
        return hint;
    }
    @Override
    public void prepareOutArea(TextArea consoleOutTextArea) {
        consoleOutTextArea.appendText(" [" + this.encrypedKey + "] ");
    }
}
