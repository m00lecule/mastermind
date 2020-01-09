package to2.model;

public class GameSettings {
    private int columns;
    private int rows;
    private int colors;

    //TODO: persist default values

    public GameSettings(){
        this.columns = 4;
        this.rows = 5;
        this.colors = 2;
        //(optional) TODO: add something like Boolean colorsCanRepeat and all the logic
    }

    public GameSettings(int columns, int rows, int colors){
        this.columns = columns;
        this.rows = rows;
        this.colors = columns;
    }


    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }
}
