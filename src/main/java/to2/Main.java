package to2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import to2.controller.BorderPaneController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    private BorderPaneController appController;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        primaryStage.setTitle("My second JavaFX app");

        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/views/board.fxml"));

        Scene scene = null;
        try {
            scene = (Scene)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BorderPaneController bp = loader.getController();
        bp.setData(5);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}