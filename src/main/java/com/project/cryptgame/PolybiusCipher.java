package com.project.cryptgame;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class PolybiusCipher extends CiphersAbstract {

    Resource res;
    char[][] alphabet;

    public PolybiusCipher(Resource res) {
        super(res);
        this.alphabet = setAlphabet();
        this.encrypedKey = encrypt();
    }

    private char[][] setAlphabet() {
        char[][] alphabet = new char[5][5];
        int ascii = 65;

        for (int i = 0; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet[i].length; j++) {
                char ch = (char) ascii++;
                if (ascii != 74) {  // for it to skip J = 74 in ascii
                    char c = alphabet[i][j] = ch;
                } else {
                    char c = alphabet[i][j] = 73;
                    ascii++;
                }

            }
        }
        return alphabet;
    }

    public String showAlphabet(char[][] aplhabet) {
        String s = "\n";
        s += "     1    2    3    4    5  \n";
        for (int row = 0; row < alphabet.length; row++) {
            s += (row + 1) + "  ";
            char[] cs = alphabet[row];
            for (int column = 0; column < cs.length; column++) {
                char c = cs[column];
                s += "  " + c + "  ";
            }
            s += "\n";
        }
        return s;
    }

    @Override
    public String task() {
        String task = "";
        task += "WELCOME TO POLYBIUS CIPHER!\n\nBellow this message you will see an aplhabet and an encrypted word,"
                + " your task is to decipher this word using the alphabet."
                + " As soon as you write down your answer to console line and confirm it with enter,"
                + " you will se if your answer is correct (please capitalize each letter).\n"
                + "If you have trouble guessing, press \"?\" button in the right corner of the screen...\n\n";
        task += "Encrypted word: [ " + encrypedKey + "] \n";
        task += showAlphabet(alphabet);
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
        solved = "CONGRATULATIONS, THE POLYBIUS CIPHER HAS BEEN CRACKED!!!\nPress green button for next cipher.";
        return solved;
    }

    @Override
    public String hint() {
        String hint = "";
        hint = "Find letter depending on given coordinates (row and column)."
                + "\nExample for 11 24 14:\n"
                + "1 1: A\t2 4: I\t1 4: D\nAID is the answer in this case...";
        return hint;
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

    /*
    Method for encrypting key using alphabet, it will go through each letter of
    key and encrypt in using the alphabet
    */
    @Override
    public String encrypt() {
        String encrypedKey = "";
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            for (int j = 0; j < alphabet.length; j++) {
                char[] cs = alphabet[j];
                for (int k = 0; k < cs.length; k++) {
                    char c = cs[k];
                    if (ch == c) {
                        encrypedKey += (j + 1) + "" + (k + 1) + " ";
                        break;
                    }
                }
            }
        }
        return encrypedKey;
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
    public void prepareOutArea(TextArea consoleOutTextArea) {

    }
}
