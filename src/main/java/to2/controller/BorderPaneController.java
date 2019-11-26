package to2.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import to2.BoardElements.ColorfulButton;

public class BorderPaneController {

    final int BixButtonSize = 40;

    final int SmallButtonFix = 16;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data = 5;

    @FXML
    private VBox vboxCentral;

    @FXML
    private void initialize() {
        for (int rows = 0; rows<data; rows++){BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        ColorfulButton bt;
        for(int i = 0 ; i < 4 ; ++i){
            bt = new ColorfulButton();
            bt.setPrefHeight(BixButtonSize);
            bt.setPrefWidth(BixButtonSize);
            gp.add(bt,i,0);
        }
        GridPane ingp = new GridPane();
            ingp.setHgap(10);
            ingp.setVgap(10);
        for (int i =0; i<2; i++){
            for (int j =0; j<2; j++){
                bt = new ColorfulButton();
                bt.setPrefHeight(SmallButtonFix);
                bt.setMaxWidth(SmallButtonFix+3);
                bt.setPrefWidth(SmallButtonFix+3);
                bt.setMaxHeight(SmallButtonFix);
                ingp.add(bt,i,j);
            }
        }
        gp.add(ingp, 4, 0);

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(30);
        bp.setPadding(new Insets(10, 20, 10, 20));
        bp.setCenter(gp);
        vboxCentral.getChildren().add(bp);}

    }
}



