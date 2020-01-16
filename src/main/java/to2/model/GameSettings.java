package to2.model;

public class GameSettings {
    private int rows;
    private int colors;

    public GameSettings(){
        this.rows = Config.getRows();
        this.colors = Config.getColorNumber();
    }

    public GameSettings(int rows, int colors){
        this.rows = rows;
        this.colors = colors;
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
