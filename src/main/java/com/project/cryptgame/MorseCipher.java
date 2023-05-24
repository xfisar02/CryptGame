package com.project.cryptgame;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class MorseCipher extends CiphersAbstract {

    Map<Character, String> alphabet;
    Resource res;

    public MorseCipher(Resource res) {
        super(res);
        alphabet = generateAlphabet();
        encrypedKey = encrypt();
    }

    private Map<Character, String> generateAlphabet() {
        Map<Character, String> alphabet = new HashMap<>();
        String[] alphabetMorse = alphabetMorse();
        int ascii = 65 - 1;
        for (int i = 0; i <= 25; i++) {
            ++ascii;
            alphabet.put((char) ascii, alphabetMorse[i]);
        }
        return alphabet;
    }

    private String[] alphabetMorse() {
        String[] morseAlphabet = new String[]{
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-", "-.--", "--..",};

        return morseAlphabet;
    }

    private String showAlphabet() {
        StringBuilder alphabet = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.append(c).append(" ");
        }
        return alphabet.toString();
    }

    private String morseToString(String[] morseAlphabet) {
        String convertedMorseAlphabet = "";
        for (String morse : morseAlphabet) {
            convertedMorseAlphabet += morse + " ";
        }
        return convertedMorseAlphabet;
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
        String s = "";
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            String morse = alphabet.get(ch);
            s += morse + " ";
        }
        return s;
    }

    @Override
    public String task() {
        String task = "";
        task += "WELCOME TO MORSE CIPHER!\n\nBellow this message you will see an encrypted word,"
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
        String solved = "";
        solved = "CONGRATULATIONS, THE MORSE CIPHER HAS BEEN CRACKED!!!\nPress green button for next cipher.";
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
    public void prepareOutArea(TextArea consoleOutTextArea) {
        consoleOutTextArea.appendText(" [" + this.encrypedKey + "] ");
    }

    @Override
    public String hint() {
        String hint = "";
        hint = "Morse aplhabet:\n" + morseToString(alphabetMorse());
        return hint;
    }

}
