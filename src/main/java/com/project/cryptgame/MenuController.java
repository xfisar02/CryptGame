package com.project.cryptgame;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

enum EnumDifficulty {
    EASY(10),
    MEDIUM(20),
    HARD(50);
    int value;

    private EnumDifficulty(int value) {
        this.value = value;
    }
}

public class MenuController implements Initializable {

    static Scene gameScene;

    GameController game;

    EnumDifficulty difficulty;

    public MenuController() {
        difficulty = (EnumDifficulty.EASY);
        game = new GameController(this);
    }

    @FXML
    private ComboBox difficultyComboBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button playButton;

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    @FXML
    protected void chooseDifficulty() {
        difficulty = (EnumDifficulty) difficultyComboBox.getValue();
        game.setDifficulty(difficulty);
    }

    @FXML
    protected void onGamePlayClick() throws IOException {
        game.setGameObject();

        FXMLLoader fxmlLoader = new FXMLLoader(CryptGameMain.class.getResource("cryptgame.fxml"));
        fxmlLoader.setController(game);
        Scene gameScene = new Scene(fxmlLoader.load());

        //String css = this.getClass().getResource("/res/css/style.css").toExternalForm();
        //gameScene.getStylesheets().add(css);
        CryptGameMain.stage.setTitle("CryptGame!");
        CryptGameMain.stage.setResizable(false);
        CryptGameMain.stage.setScene(gameScene);
        CryptGameMain.stage.show();
    }

    @FXML
    protected void selectUserName() {
        GameScore.addPlayer(usernameTextField.getText(), 0);
        playButton.setVisible(true);
    }

    @FXML
    protected void onAccessUsername() {
        playButton.setVisible(false);
    }

    @FXML
    protected void showLeaderBoard() throws FileNotFoundException{
        GameScore.sortForScore();
        GameScore.showListPlayers();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyComboBox.getItems().setAll(EnumDifficulty.values());
        difficultyComboBox.setValue(EnumDifficulty.EASY);

        playButton.setVisible(false);

        GameScore.loadScoreFile();
        GameScore.sortForScore();
        GameScore.setClearListPlayersonTen();
    }

}
