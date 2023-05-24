package com.project.cryptgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    MenuController menu;
    EnumDifficulty difficulty;
    Resource res;
    CiphersAbstract gameObject;
    int level;

    @FXML
    private TextArea consoleOutTextArea;
    @FXML
    private TextArea consoleInfTextArea;
    @FXML
    private TextField consoleInTextField;
    @FXML
    private Button showHintButton;

    @FXML
    protected void goToNext() {
        if (level == 3) {       // cycling the ciphers
            level = 0;
        }
        level++;
        System.out.println("Current lvl: " + level);
        setGameObject();
        showCipher(consoleOutTextArea);
        showHintButton.setDisable(false);
        consoleInTextField.setEditable(true);
        consoleInTextField.setDisable(false);
    }

    @FXML
    protected void goToMenu() throws IOException {
        CryptGameMain.menuStage.setTitle("CryptGame!");
        CryptGameMain.menuStage.setResizable(false);
        CryptGameMain.menuStage.setScene(CryptGameMain.menuScene);
        CryptGameMain.menuStage.show();
        level = 1;
    }

    @FXML
    protected void showHint() throws IOException {
        consoleOutTextArea.appendText("\n\nHINT:\n" + gameObject.hint());
        showHintButton.setDisable(true);
    }

    /*
    Method for encreasing score and based on if the answer is correct, showing player that he won.
    */
    @FXML
    protected void checkAnswer() {
        int score = GameScore.listPlayers.get(GameScore.selectPlayer(menu.getUsernameTextField().getText())).score;
        switch (difficulty) {
            case EASY:
            case MEDIUM:
            case HARD:
                if (level == 1) {
                    if (gameObject.insertAnswer(consoleOutTextArea, consoleInfTextArea, consoleInTextField)) {
                        consoleOutTextArea.setText(gameObject.win());
                        showHintButton.setDisable(true);
                        consoleInTextField.setEditable(false);
                        consoleInTextField.setDisable(true);
                        score += difficulty.value;
                    }
                }
                if (level == 2) {
                    if (gameObject.insertAnswer(consoleOutTextArea, consoleInfTextArea, consoleInTextField)) {
                        consoleOutTextArea.setText(gameObject.win());
                        showHintButton.setDisable(true);
                        consoleInTextField.setEditable(false);
                        consoleInTextField.setDisable(true);
                        score += difficulty.value;
                    }
                }
                if (level == 3) {
                    if (gameObject.insertAnswer(consoleOutTextArea, consoleInfTextArea, consoleInTextField)) {
                        consoleOutTextArea.setText(gameObject.win());
                        showHintButton.setDisable(true);
                        consoleInTextField.setEditable(false);
                        consoleInTextField.setDisable(true);
                        score += difficulty.value;
                    }
                }
                break;
        }
        GameScore.listPlayers.get(GameScore.selectPlayer(menu.getUsernameTextField().getText())).setScore(score);
        consoleInTextField.setText("");
        System.out.println("score now: " + score);
        GameScore.saveScoreFile();  //save score to the file
    }

    public GameController(MenuController menu) {
        this.menu = menu;
        this.difficulty = menu.difficulty;
        level = 1;
    }

    public GameController() {
        this.consoleOutTextArea = new TextArea();
        this.consoleInfTextArea = new TextArea();
        this.consoleInTextField = new TextField();
    }

    public void setDifficulty(EnumDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    /*
    Method for setting game object (cipher) based on difficulty
    */
    public void setGameObject() {
        this.res = new Resource(difficulty);
        switch (difficulty) {
            case EASY:
                if (level == 1) {
                    this.gameObject = new PasswdCrack(res);
                }
                if (level == 2) {
                    this.gameObject = new MorseCipher(res);
                }
                if (level == 3) {
                    this.gameObject = new CaesarCipher(res);
                }
                break;
            case MEDIUM:
                if (level == 1) {
                    this.gameObject = new PasswdCrack(res);
                }
                if (level == 2) {
                    this.gameObject = new AtbashCipher(res);
                }
                if (level == 3) {
                    this.gameObject = new PolybiusCipher(res);
                }
                break;
            case HARD:
                if (level == 1) {
                    this.gameObject = new PasswdCrack(res);
                }
                if (level == 2) {
                    this.gameObject = new VigenereCipher(res);
                }
                if (level == 3) {
                    this.gameObject = new BifidCipher(res);
                }
                break;
        }
    }

    /*
    Method for showing ciphers in GUI.
    */
    public void showCipher(TextArea consoleOutTextArea) {
        Collections.shuffle(res.cryptResource);
        consoleOutTextArea.setText(gameObject.task());
        consoleInfTextArea.setText(gameObject.guess());
        gameObject.prepareOutArea(consoleOutTextArea);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("difficulty " + difficulty);
        consoleOutTextArea.setText(gameObject.task());
        consoleInfTextArea.setText(gameObject.guess());
        showCipher(consoleOutTextArea);
        consoleOutTextArea.setWrapText(true);
        consoleInfTextArea.setWrapText(true);
    }
}