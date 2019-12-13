package to2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import to2.controller.BoardController;

import java.io.IOException;


public class Mastermind extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Mastermind");
        primaryStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Mastermind.class.getResource("/views/board.fxml"));

        try {
            Scene scene = (Scene) loader.load();
            BoardController bp = loader.getController();
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