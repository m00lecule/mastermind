package to2.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import to2.BoardElements.Color;
import to2.BoardElements.Row;
import to2.model.Game;
import to2.model.GameSettings;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Mastermind board controller - manipulates view adding more rows to the board.
 */
public class BoardController {

    void setSettings(GameSettings settings) {
        this.rowsNumber = settings.getRows();
        //TODO: make colors change influence the game
        this.colorsNumber = settings.getColors();
    }

//    private GameSettings settings;

    private int rowsNumber;
    private int colorsNumber;

    //TODO: decide whether we want 4 fields or to make it dynamic
    private Game game;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vboxCentral;

    @FXML
    private Button nextStep;

//    @FXML
//    private Button resetButton;

    private List<Row> rowsList = new LinkedList<>();

    private ListIterator<Row> it;
    private Row currentRow;

    private void showPopup(String content, String header, String title) {
        this.nextStep.setDisable(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //TODO: custom graphic (setGraphic)
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.setTitle(title);
        anchorPane.setEffect(new GaussianBlur());
        alert.showAndWait();
        anchorPane.setEffect(null);
    }

    @FXML
    private void handleNextStepAction(ActionEvent event) {
        currentRow.setDisable(true);

        List<Color> guesses = currentRow.getGuesses();

        List<Color> result = game.compareSequence(guesses);

        currentRow.updateCircles(result);

        if (game.wonGame()) {
            showPopup("Your score: " + game.getScore(), "You won!", "Congratulations!");
        } else {
            if (it.hasPrevious()) {
                currentRow = it.previous();
                currentRow.setDisable(false);
            } else {
                showPopup("Your score: 0", "You lost", "Not congratulations.");
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
            game = new Game(4, rowsNumber, colorsNumber);
            for (int rows = 0; rows < rowsNumber; rows++) {
                Row r = new Row(true);
                rowsList.add(r);
                vboxCentral.getChildren().add(r);
            }

            it = rowsList.listIterator(rowsList.size());
            currentRow = it.previous();
            currentRow.setDisable(false);
        });
    }

}



