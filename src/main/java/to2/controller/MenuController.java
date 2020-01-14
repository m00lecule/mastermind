package to2.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import to2.Mastermind;
import to2.model.GameSettings;
import to2.persistance.GameScore;
import to2.persistance.Postgres;
import to2.persistance.User;

import java.io.IOException;

/**
 * Game menu controller
 */
public class MenuController {


    private GameSettings settings;
    //TODO: move somewhere else idk
    private GameSettings maxSettings = new GameSettings(12, 7);

    @FXML
    private Spinner<Integer> rowsSpinner;
    @FXML
    private Spinner<Integer> colorsSpinner;
    @FXML
    private Button okButton;
    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane menuAnchorPane;
    @FXML
    private Label playerName;
    @FXML
    private Button logInButton;
    @FXML
    private Button registerButton;


    @FXML
    private void initialize() {
        settings = new GameSettings();
        rowsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxSettings.getRows()));
        rowsSpinner.getValueFactory().setValue(settings.getRows());
        colorsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxSettings.getColors()));
        colorsSpinner.getValueFactory().setValue(settings.getColors());
        playerName.setText("Not logged in");
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        settings.setRows(rowsSpinner.getValue());
        settings.setColors(colorsSpinner.getValue());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Mastermind.class.getResource("/views/board.fxml"));
        Stage gameStage = new Stage();
        BoardController boardController;
        try {
            gameStage.setScene(loader.load());
            boardController = loader.getController();
            boardController.setSettings(settings);
            Stage current = (Stage) okButton.getScene().getWindow();
            boardController.setMenu(current);
            gameStage.show();
            current.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleQuitAction(ActionEvent event) {
        Stage current = (Stage) quitButton.getScene().getWindow();
        current.close();
    }

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        showLoginPopup();
    }

    @FXML
    private void handleScoresAction(ActionEvent event) {
        showHighscores();
    }

    @FXML
    private void handleRegisterAction(ActionEvent actionEvent) {
        showRegisterPopup();
    }

    private void showRegisterPopup() {

    }

    private void showHighscores() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("High scores");
        alert.setGraphic(null);
        alert.setHeaderText("High Scores:");
        DialogPane dp = alert.getDialogPane();
        TableView<GameScore> table = new TableView<>();
        TableColumn nickCol = new TableColumn("Nickname");
        TableColumn scoreCol = new TableColumn("Score");

        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        nickCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameScore, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<GameScore, String> param) {
                User u;
                if ((u = param.getValue().getUser()) != null) {
                    if (u.getNickname() != null) {
                        return new SimpleStringProperty(u.getNickname());
                    } else if (u.getEmail() != null) {
                        return new SimpleStringProperty(u.getEmail());
                    }
                }
                return new SimpleStringProperty("Unknown");
            }
        });

        table.getColumns().addAll(nickCol, scoreCol);
        SessionFactory sessionFactory = Postgres.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "FROM GameScore gs ORDER BY gs.score DESC";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(100);

        table.setItems(FXCollections.observableArrayList(query.list()));

        tx.commit();
        session.close();

        dp.setContent(table);
        menuAnchorPane.setEffect(new GaussianBlur());
        alert.showAndWait();
        menuAnchorPane.setEffect(null);
    }

    private void showLoginPopup() throws IOException {

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Mastermind.class.getResource("/views/login.fxml"));

        try {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            menuAnchorPane.setEffect(new GaussianBlur());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        menuAnchorPane.setEffect(null);

        if (User.LOGGED_USER != null) {

            String s = null;

            if((s = User.LOGGED_USER.getNickname()) != null || (s = User.LOGGED_USER.getEmail()) != null){
                playerName.setText(s);
            }
        }
    }
}



