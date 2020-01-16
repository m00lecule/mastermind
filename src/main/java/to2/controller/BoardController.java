package to2.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import to2.BoardElements.Color;
import to2.BoardElements.Row;
import to2.mail.JavaMail;
import to2.model.Game;
import to2.model.GameSettings;
import to2.persistance.GameScore;
import to2.persistance.Postgres;
import to2.persistance.User;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Mastermind board controller - manipulates view adding more rows to the board.
 */
public class BoardController {

    void setSettings(GameSettings settings) {
        this.rowsNumber = settings.getRows();
        this.colorsNumber = settings.getColors();
    }

    private int rowsNumber;
    private int colorsNumber;

    private Game game;
    private Stage menu;

    void setMenu(Stage menu) {
        this.menu = menu;
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vboxCentral;

    @FXML
    private Button nextStep;


    private List<Row> rowsList = new LinkedList<>();

    private ListIterator<Row> it;
    private Row currentRow;

    private void showPopup(String content, String header, String title) {
        this.nextStep.setDisable(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        anchorPane.setEffect(new GaussianBlur());
        alert.showAndWait();
        backToMainMenu();
    }


    private boolean notifyIfBestScoreAndPersist(int score) {
        SessionFactory sessionFactory = Postgres.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        GameScore gs = new GameScore();
        gs.setScore(score);
        gs.setUser(User.LOGGED_USER);

        boolean cond = false;

        String hql = "FROM GameScore gs WHERE gs.score > :score";
        Query query = session.createQuery(hql);
        query.setParameter("score", score);

        if (query.list().isEmpty()) {
            cond = true;
            if (User.LOGGED_USER != null) {
                hql = "FROM User u WHERE u.sendNotification IS true";
                query = session.createQuery(hql);

                try {
                    JavaMail.notifyUsers(query.list());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (User.LOGGED_USER != null){
            session.save(gs);
        }

        tx.commit();
        session.close();

        return cond;
    }


    @FXML
    private void handleNextStepAction(ActionEvent event) {
        currentRow.setDisable(true);

        List<Color> guesses = currentRow.getGuesses();

        List<Color> result = game.compareSequence(guesses);

        currentRow.updateCircles(result);

        if (game.wonGame()) {
            if (notifyIfBestScoreAndPersist(game.getScore()))
                showPopup("Your score: " + game.getScore() + "\n You are now at the top of leaderboard!", "You won!", "Congratulations!");
            else
                showPopup("Your score: " + game.getScore(), "You won!", "Congratulations!");
        } else {
            if (it.hasPrevious()) {
                currentRow = it.previous();
                currentRow.setDisable(false);
            } else {
                showPopup("Your score: 0", "You lost\nWinning sequence was: " + game.getSecretSequenceString(), "Not congratulations.");
            }
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        this.game.reset();
        vboxCentral.getChildren().clear();
        rowsList.clear();
        this.nextStep.setDisable(false);
        initialize();
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            game = new Game(rowsNumber, colorsNumber);
            for (int rows = 0; rows < rowsNumber; rows++) {
                Row r = new Row(colorsNumber, true);
                rowsList.add(r);
                vboxCentral.getChildren().add(r);
            }

            it = rowsList.listIterator(rowsList.size());
            currentRow = it.previous();
            currentRow.setDisable(false);
        });
    }


    @FXML
    private void handleAbandonAction(ActionEvent event) {
        backToMainMenu();
    }

    private void backToMainMenu() {
        Stage current = (Stage) anchorPane.getScene().getWindow();
        current.close();
        menu.show();
    }
}



