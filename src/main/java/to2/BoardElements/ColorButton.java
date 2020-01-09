package to2.BoardElements;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a button that iterates on range of colors.
 * It's state change is triggered by clicking on.
 */
public class ColorButton extends Button {

    private int colorNumber = 0;
    private int colors;

    public Color getCurrentColor() {
        return currentColor;
    }

    private Color currentColor = Color.WHITE;

    ColorButton(int colors) {
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
        this.setOnMouseClicked(this::handleClick);
        this.colors = colors;
    }

    private void handleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            colorNumber = colorNumber < colors - 1 ? colorNumber + 1 : 0;
        } else if (event.getButton() == MouseButton.SECONDARY) {
            colorNumber = colorNumber > 0 ? colorNumber - 1 : colors - 1;
        }
        currentColor = Color.values()[colorNumber];
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }

    public ColorButton(int colors, boolean clickable) {
        this(colors);
        if (!clickable) {
            this.setDisable(true);
        }
    }

    Color getColor() {
        return this.currentColor;
    }

    public void setColor(Color c) {
        this.currentColor = c;
        this.colorNumber = new ArrayList<>(Arrays.asList(Color.values())).indexOf(c);
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }

    @Override
    public void fire() {

    }


}
