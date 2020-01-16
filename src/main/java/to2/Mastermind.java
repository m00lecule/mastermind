package to2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import to2.controller.BoardController;
import to2.controller.MenuController;
import to2.persistance.Postgres;
import to2.persistance.User;

import java.io.IOException;
import java.util.List;


public class Mastermind extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mastermind");
        primaryStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Mastermind.class.getResource("/views/menu.fxml"));

        try {
            Scene scene = new Scene(loader.load());
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