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

//        primaryStage.setTitle("Mastermind");
//        primaryStage.setResizable(false);
//
//        FXMLLoader loader = new FXMLLoader();
//
//        loader.setLocation(Mastermind.class.getResource("/views/board.fxml"));
//
//        try {
//            Scene scene = (Scene) loader.load();
//            BoardController bp = loader.getController();
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        primaryStage.setTitle("Mastermind");
        primaryStage.setResizable(false);

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Mastermind.class.getResource("/views/menu.fxml"));

        try {
            Scene scene = new Scene(loader.load());
            MenuController menuController = loader.getController();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
//        SessionFactory sessionFactory = Postgres.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
////        Query query = session.createQuery("from User where id = :id ");
////        query.setParameter("id", 1);
//
//        // You can replace the above to commands with this one
//        // Query query = session.createQuery("from Student where studentId = 1 ");
//
//        User student = new User();
//        student.setEmail("sdfsa");
//        student.setNickname("ksfsf");
//
//        session.save(student);
//
//        tx.commit();
//        session.close();
        launch(args);
    }


}