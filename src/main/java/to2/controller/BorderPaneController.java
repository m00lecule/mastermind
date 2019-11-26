package to2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import to2.BoardElements.Row;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BorderPaneController {

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data = 8;

    @FXML
    private VBox vboxCentral;

    @FXML
    private Button nextStep;

    private List<Row> rowsList = new LinkedList<>();

    private ListIterator<Row> it;
    private Row currentRow;

    @FXML
    private void handleNextStepAction(ActionEvent event) {
        currentRow.setDisable(true);
        if(it.hasPrevious()) {
            currentRow = it.previous();
            currentRow.setDisable(false);
        }
    }

    @FXML
    private void initialize() {

        for (int rows = 0; rows<data; rows++){
            Row r = new Row();
            rowsList.add(r);
            vboxCentral.getChildren().add(r);
            r.setDisable(true);
        }

        it = rowsList.listIterator(rowsList.size());
        currentRow = it.previous();
        currentRow.setDisable(false);
    }
}



