package com.project.cryptgame;

import java.util.Arrays;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public class BifidCipher extends CiphersAbstract {
    char[][] alphabet;

    @Override
    public String toString() {
        return "Bifid{"
                + "\nalphabet=" + Arrays.deepToString(alphabet)
                + ",\n key=" + key
                + ",\n encrypedKey=" + encrypedKey
                + '}';
    }

    public BifidCipher(Resource res) {
        super(res);
        this.alphabet = setAlphabet();
        this.encrypedKey = encrypt();
    }

    private char[][] setAlphabet() {
        char[][] alphabet = new char[5][5];
        int ascii = 65; // A in ascii

        for (int i = 0; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet[i].length; j++) {
                char ch = (char) ascii++;
                if (ascii != 74) { // for it to skip J = 74 in ascii
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

    /*
    This method is used for conevrting 2 dimensional field to 1 dimensional.
    */
    private int[] convert2DTo1D(int[][] field2D) {
        int rows = field2D.length;
        int columns = field2D[0].length;
        int[] field1D = new int[rows * columns];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field1D[index] = field2D[i][j];
                index++;
            }
        }
        return field1D;
    }

    @Override
    public String task() {
        String task = "";
        task += "WELCOME TO BIFID CIPHER!\n\nBellow this message you will see an aplhabet and an encrypted word,"
                + " your task is to decipher this word using the alphabet."
                + " As soon as you write down your answer to console line and confirm it with enter,"
                + " you will se if your answer is correct (please capitalize each letter).\n"
                + "If you have trouble guessing, press \"?\" button in the right corner of the screen...\n\n";
        task += "Encrypted word: [" + encrypedKey + "] \n";
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
        solved = "CONGRATULATIONS, THE BIFID CIPHER HAS BEEN CRACKED!!!\nPress green button for next cipher.";
        return solved;
    }

    @Override
    public String hint() {
        String hint = "";
        hint = "Find coordinates of each letter (row and column), then write all coordinates in one line."
                + " Split the line in half, first half are x coordinates and second y coordinates of the letters. "
                + "\nExample for word BAT:\n"
                + "B: 1 2\tA: 1 1\tT: 4 4\n"
                + "then: 1 2 1 , 1 4 4 => 11 24 14 \n"
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

    @Override
    public String encrypt() {
        String encrypedKey = "";

        int[][] field2D = new int[2][key.length()];

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            for (int j = 0; j < alphabet.length; j++) {
                char[] cs = alphabet[j];
                for (int k = 0; k < cs.length; k++) {
                    char c = cs[k];
                    if (ch == c) {
                        field2D[0][i] = j;
                        field2D[1][i] = k;
                        break;
                    }
                }
            }
        }

        int[] field1D = convert2DTo1D(field2D);

        // seperate 1 dimensional field based on odd and even idex
        int[] odd = new int[field1D.length / 2];
        int o = 0;
        for (int i = 0; i < field1D.length; i++) {
            int c = field1D[i];
            if (i % 2 == 1) {
                odd[o++] = c;
            }
        }

        int[] even = new int[field1D.length / 2];
        int e = 0;
        for (int i = 0; i < field1D.length; i++) {
            int c = field1D[i];
            if (i % 2 == 0) {
                even[e++] = c;
            }
        }
        //ecryption based on even and odd coordinates
        for (int i = 0; i < even.length; i++) {
            int ev = even[i];
            int od = odd[i];
            encrypedKey += alphabet[ev][od];
        }
        return encrypedKey;
    }

    @Override
    public boolean insertAnswer(TextArea consoleOutTextArea, TextArea consoleInfTextArea, TextField consoleInTextField) {
        if (consoleInTextField.getText().equalsIgnoreCase(key.replace("J", "I"))) {
            consoleInfTextArea.setText("");
            return true;
        } else {
            consoleInfTextArea.appendText(consoleInTextField.getText() + "\n");
            return false;
        }
    }
    @Override
    public void prepareOutArea(TextArea consoleOutTextArea) {
        System.out.println("[" + this + "]");
    }
}
