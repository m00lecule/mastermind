package to2.BoardElements;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import to2.main.Color;

import java.awt.event.ActionEvent;

public class ColorfulButton extends Button {

    int colourNumber = 0;

    Color currentColour;




    @Override
    public void fire(){


        this.setStyle("-fx-background-color: #00ff00");

        System.out.println(this.getStyle());
    }




}
