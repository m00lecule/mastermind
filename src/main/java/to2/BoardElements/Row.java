package to2.BoardElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents view of single row in Mastermind Game.
 * Its responsibility is to keep all childnodes grouped.
 */
public class Row extends BorderPane {

    final private int bigButtonSize = 40;
    final private int cricleRadix = 5;
    final private List<Circle> circleList = new ArrayList<>();
    final private List<ColorButton> buttons = new LinkedList<>();

    public Row() {
        this.setPadding(new Insets(10, 20, 10, 20));
        this.setCenter(this.initializeCentralGridPane());
    }

    public Row(boolean setDisabled) {
        this();
        this.setDisable(setDisabled);
    }

    private Node initializeCircle(){
        Circle c = new Circle();
        c.setRadius(cricleRadix);
        c.setStyle("-fx-fill: #000000");
        circleList.add(c);
        return c;
    }

    public void updateCircles(List<Color> results){
        Iterator<Color> resultIterator = results.iterator();
        Iterator<Circle> circleIterator = circleList.iterator();

        while(resultIterator.hasNext()){
            circleIterator.next().setStyle("-fx-fill: " + Color.getColorHex(resultIterator.next()));
        }
    }

    public List<Color> getGuesses(){
        return buttons.stream().map(ColorButton::getColor).collect(Collectors.toList());
    }

    private Node initializeButton(){
        ColorButton bt = new ColorButton();
        bt.setPrefHeight(bigButtonSize);
        bt.setPrefWidth(bigButtonSize);
        buttons.add(bt);
        return bt;
    }

    private Node initializeCentralGridPane(){
        GridPane gp = new GridPane();

        for(int i = 0 ; i < 4 ; ++i)
            gp.add(this.initializeButton(),i,0);

        GridPane ingp = new GridPane();
        ingp.setHgap(10);
        ingp.setVgap(6);

        for (int i =0; i<2; i++)
            for (int j =0; j<2; j++)
                ingp.add(this.initializeCircle(),i,j);

        gp.add(ingp, 4, 0);

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(30);
        return gp;
    }

}
