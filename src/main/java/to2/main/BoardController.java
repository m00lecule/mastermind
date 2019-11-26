package to2.main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BoardController {
    private int firstRow = 128;
    private int firstColumn = 128;
    private int rowHeight = 76;
    private int columnWidth = 64;
    private Stage primaryStage;
    private Group root;
    private Scene theScene;
    private Canvas canvas;
    private GraphicsContext gc;

    public BoardController(Stage stage, int rows) {
        this.primaryStage = stage;
        primaryStage.setTitle( "MasterMind Game" );

        root = new Group();
        theScene = new Scene( root );
        primaryStage.setScene( theScene );

        canvas = new Canvas( 512, 768 );
        root.getChildren().add( canvas );

        gc = canvas.getGraphicsContext2D();

        Image background = new Image( "file:images/background.png" );
        gc.drawImage(background, 0, 0);
        Image hole = new Image( "file:images/hole.png" );
        Image markers = new Image("file:images/markers.png");

        for (int row = 0; row < rows; row++){
            int currentRow = firstRow + row*rowHeight;
            gc.drawImage(markers, 32, currentRow);
            for (int column = 0; column < 4; column++){
                int currentColumn = firstColumn + column*columnWidth;
                gc.drawImage(hole, currentColumn, currentRow);
            }
        }


        primaryStage.show();
    }

    public int getFirstRow() {
        return firstRow;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    private int getRowPx(int row){
        return firstRow + row*rowHeight;
    }

    private int getColumnPx(int column){
        return firstColumn + column*columnWidth;
    }

    public void activateHole(int row, int column){
        int x = getColumnPx(column);
        int y = getRowPx(row);
        Image markedHole = new Image( "file:images/markedhole.png" );
        gc.drawImage(markedHole, x, y);
        primaryStage.show();
    }

    public void colorHole(int row, int column, Color color){
        int x = getColumnPx(column);
        int y = getRowPx(row);
        String file;
        switch (color){
            case RED:
                file = "file:images/red.png";
                break;
            case BLUE:
                file = "file:images/blue.png";
                break;
            case PURPLE:
                file = "file:images/purple.png";
                break;
            case YELLOW:
                file = "file:images/yellow.png";
                break;
            case CYDIAN:
                file = "file:images/cydian.png";
                break;
            case GREEN:
                file = "file:images/green.png";
                break;
        }
        Image markedHole = new Image( "file:images/red.png" );
        gc.drawImage(markedHole, x, y);
        primaryStage.show();
    }
}
