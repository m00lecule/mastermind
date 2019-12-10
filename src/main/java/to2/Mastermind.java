package to2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import to2.controller.BoardControler;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Mastermind extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Mastermind");

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Mastermind.class.getResource("/views/board.fxml"));

        try {
            Scene scene = (Scene) loader.load();
            BoardControler bp = loader.getController();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


}