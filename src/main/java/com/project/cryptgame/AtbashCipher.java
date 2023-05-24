package com.project.cryptgame;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class AtbashCipher extends CiphersAbstract {
    ArrayList<Character> alphabet;
    Resource res;
    int offset;

    public AtbashCipher(Resource res) {
        super(res);
        alphabet = generateAlphabet();
        encrypedKey = encrypt();
    }

    private ArrayList< Character> generateAlphabet() {
        ArrayList<Character> alphabet = new ArrayList<>();
        int ascii = 65 - 1;
        for (int i = 0; i <= 25; i++) {
            ++ascii;
            alphabet.add((char) ascii);
        }
        return alphabet;
    }

    private String showAlphabet() {
        StringBuilder alphabet = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.append(c).append(" ");
        }
        return alphabet.toString();
    }

    private String showReverseAlphabet() {
        StringBuilder alphabet = new StringBuilder();
        for (char c = 'Z'; c >= 'A'; c--) {
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
        ArrayList<Character> orig = new ArrayList<>(alphabet);
        Collections.reverse(alphabet);
        for (int i = 0; i < key.length(); i++) {
            Character character = key.charAt(i);
            int index = orig.indexOf(character);
            encrypt += alphabet.get(index);
        }
        return encrypt;
    }

    @Override
    public String task() {
        String task = "";
        task += "WELCOME TO ATBASH CIPHER!\n\nBellow this message you will see an encrypted word, your task is to decipher this word."
                + " As soon as you write down your answer to console line and confirm it with enter,"
                + " you will se if your answer is correct (please capitalize each letter).\n"
                + "If you have trouble guessing, press \"?\" button in the right corner of the screen...\n\n";
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
        String solved = "";
        solved = "CONGRATULATIONS, THE ATBASH CIPHER HAS BEEN CRACKED!!!\nPress green button for next cipher.";
        return solved;
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
        hint = "Alphabet and reverse alphabet:\n"
                + showAlphabet() + "\n" + showReverseAlphabet();
        return hint;
    }

    @Override
    public void prepareOutArea(TextArea consoleOutTextArea) {
        consoleOutTextArea.appendText(" [" + this.encrypedKey + "] ");
    }
}
