package to2.BoardElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Row extends BorderPane {

    final int BixButtonSize = 40;
    final int SmallButtonFix = 16;
    final List<Circle> circleList = new ArrayList<>();
    final List<ColorfulButton> buttons = new LinkedList<>();

    public Row() {
        super();
        GridPane gp = new GridPane();
        ColorfulButton bt;
        Circle c;
        for(int i = 0 ; i < 4 ; ++i){
            bt = new ColorfulButton();
            bt.setPrefHeight(BixButtonSize);
            bt.setPrefWidth(BixButtonSize);
            buttons.add(bt);
            gp.add(bt,i,0);
        }
        GridPane ingp = new GridPane();
        ingp.setHgap(10);
        ingp.setVgap(6);
        for (int i =0; i<2; i++){
            for (int j =0; j<2; j++){
                c = new Circle();
                c.setRadius(10);
                c.setFill(i == 0 ? Color.RED : Color.WHITE);
                circleList.add(c);
                ingp.add(c,i,j);
            }
        }
        gp.add(ingp, 4, 0);

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(30);
        this.setPadding(new Insets(10, 20, 10, 20));
        this.setCenter(gp);
    }

    public void disable(boolean cond){
        this.getChildren().forEach(e -> e.setDisable(cond));
    }
}
