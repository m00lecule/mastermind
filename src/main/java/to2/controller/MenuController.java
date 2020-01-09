package to2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import to2.BoardElements.Color;
import to2.BoardElements.Row;
import to2.Mastermind;
import to2.model.Game;
import to2.model.GameSettings;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
    private Button loginButton;
    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private void initialize() {
        settings = new GameSettings();
        rowsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxSettings.getRows()));
        rowsSpinner.getValueFactory().setValue(settings.getRows());
        colorsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxSettings.getColors()));
        colorsSpinner.getValueFactory().setValue(settings.getColors());
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
    private void handleLoginAction(ActionEvent event){
        showLoginPopup();
    }

    @FXML
    private void handleScoresAction(ActionEvent event){
        showHighscores();
    }

    private void showHighscores(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("High scores");
        alert.setGraphic(null);
        alert.setHeaderText("High Scores:");
        DialogPane dp = alert.getDialogPane();
        TableView<String> table = new TableView<>();
        TableColumn nickCol = new TableColumn("Nickname");
        TableColumn mailCol = new TableColumn("email");
        TableColumn scoreCol = new TableColumn("Score");
        table.getColumns().addAll(nickCol, mailCol, scoreCol);
        //TODO: populate table with DB content

        dp.setContent(table);
        menuAnchorPane.setEffect(new GaussianBlur());
        alert.showAndWait();
        menuAnchorPane.setEffect(null);
    }

    private void showLoginPopup() {
        //TODO: Replace with a view with auto-fill
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("TODO: ADD STUFF");
        alert.setHeaderText("PEPSI MAX");
        alert.setTitle("Log in");
        menuAnchorPane.setEffect(new GaussianBlur());
        alert.showAndWait();
        menuAnchorPane.setEffect(null);
    }
}



