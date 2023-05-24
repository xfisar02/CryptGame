package com.project.cryptgame;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class VigenereCipher extends CiphersAbstract {

    char[][] alphabet;
    String mask;

    public VigenereCipher(Resource res) {
        super(res);
        this.mask = generateMask();
        this.alphabet = setAlphabet();
        this.encrypedKey = encrypt();
    }

    /*
    Method for creating aplhabet for vigenere cipher (move beggining letters and overflowing first letters to the end)
    */
    private char[][] setAlphabet() {
        char[][] alphabet = new char[26][26];
            int ascii = 65;
            int start = 65;
            for (int i = 0; i < alphabet.length; i++) {
                ascii = start++;     //start aplhabet letter (65 = A, then 66 = B,...)
                for (int j = 0; j < alphabet[i].length; j++) {
                    if (ascii > 90) {
                        int dif = ascii - 90;
                        ascii = 65 + dif - 1;
                    }
                    char c = alphabet[i][j] = (char) ascii++;
                }
            }
        return alphabet;
    }

    public String showAlphabet() {
        String s = "\n";
        for (int i = 0; i < alphabet.length; i++) {
            char[] cs = alphabet[i];
            for (int j = 0; j < cs.length; j++) {
                char c = cs[j];
                s += c + " ";
            }
            s += "\n";
        }
        return s;
    }

    private String generateMask() {
        int index = (int) Math.round(Math.random() * (res.cryptResource.size() - 1));
        mask = res.cryptResource.get(index);

        return mask;
    }

    @Override
    public String task() {
        String task = "";
        task += "WELCOME TO VIGENERE CIPHER!\n\n"
                + "Bellow this message you will see an encrypted word, mask word and an aplhabet,"
                + " your task is to decipher this word using the mask and the alphabet."
                + " As soon as you write down your answer to console line and confirm it with enter,"
                + " you will se if your answer is correct (please capitalize each letter).\n"
                + "If you have trouble guessing, press \"?\" button in the right corner of the screen...\n\n";
        task += "Encrypted word: [" + encrypedKey + "], Mask word: [" + mask + "] \n";
        task += showAlphabet();

        System.out.println("mask = " + mask);
        System.out.println("answer = " + key);
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
        solved = "CONGRATULATIONS, THE VIGENERE CIPHER HAS BEEN CRACKED!!!\nPress green button for play again.";
        return solved;
    }

    @Override
    public String hint() {
        String hint = "";
        hint = "Split the mask into individual letters and find these letters in the first column and first row of the table. "
                + "Then look at the first letter of the encrypted word. "
                + "This letter can be found in the line of the letter of the mask. "
                + "We repeat this for each letter of the mask and encrypted word."
                + "\nExample encrypted word KMB and mask KEY:\n"
                + "K ⇒ K ⇑ = A\t\tE ⇒ M ⇑ = I\t\tY ⇒ B ⇑ = D\nAID is the answer in this case...";
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

    @Override
    public String encrypt() {
        String encrypedKey = "";

        for (int i = 0; i < mask.length(); i++) {
            char r = mask.charAt(i);
            int row = r - 65;
            int column = 0;
            for (int j = 0; j < key.length(); j++) {
                char ch = key.charAt(i);
                column = ch - 65;
            }
            encrypedKey += alphabet[row][column];
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
        consoleOutTextArea.appendText("");
    }

}
