package to2.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle( "MasterMind Game" );
        final int ROWS = 7;

        BoardController boardController = new BoardController(primaryStage, ROWS);
        boardController.activateHole(6, 3);
        boardController.colorHole(6, 3, Color.CYDIAN);

    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
