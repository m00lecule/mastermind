package to2.BoardElements;

import javafx.scene.control.Button;
import to2.main.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorfulButton extends Button {

    private int colorNumber = 0;

    public Color getCurrentColor() {
        return currentColor;
    }

    private Color currentColor = Color.WHITE;

    public ColorfulButton(){
        super();
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }

    public ColorfulButton(boolean clickable){
        super();
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
        if (!clickable){
            this.setDisable(true);
        }
    }

    public void setColor(Color c){
        this.currentColor = c;
        this.colorNumber = new ArrayList<Color>(Arrays.asList(Color.values())).indexOf(c);
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }

    @Override
    public void fire(){
        colorNumber = colorNumber < Color.values().length - 1 ? colorNumber + 1 : 0;
        currentColor = Color.values()[colorNumber];
        this.setStyle("-fx-background-color: " + Color.getColorHex(currentColor));
    }


}
