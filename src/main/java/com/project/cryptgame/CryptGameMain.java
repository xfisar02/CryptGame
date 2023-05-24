package com.project.cryptgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author fisar
 */
public class CryptGameMain extends Application {

    static Scene menuScene;
    static Stage stage;
    static Stage menuStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        menuStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(CryptGameMain.class.getResource("cryptmenu.fxml"));
        menuScene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("CryptGame!");
        primaryStage.setResizable(false);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
