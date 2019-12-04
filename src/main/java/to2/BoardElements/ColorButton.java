package to2.BoardElements;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a button that iterates on range of colors.
 * It's state change is triggered by clicking on.
 */
public class ColorButton extends Button {

    private int colorNumber = 0;

    public Color getCurrentColor() {
        return currentColor;
    }

    private Color currentColor = Color.WHITE;

    public ColorButton() {
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }

    public ColorButton(boolean clickable) {
        this();
        if (!clickable) {
            this.setDisable(true);
        }
    }

    public void setColor(Color c) {
        this.currentColor = c;
        this.colorNumber = new ArrayList<Color>(Arrays.asList(Color.values())).indexOf(c);
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }

    @Override
    public void fire() {
        colorNumber = colorNumber < Color.values().length - 1 ? colorNumber + 1 : 0;
        currentColor = Color.values()[colorNumber];
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }


}
