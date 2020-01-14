package to2.model;

public class GameSettings {
    private int rows;
    private int colors;

    //TODO: persist default values

    public GameSettings(){
        this.rows = 8;
        this.colors = 5;
        //(optional) TODO: add something like Boolean colorsCanRepeat and all the logic
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
