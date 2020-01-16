package to2.BoardElements;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Color {
    WHITE,
    RED,
    BLUE,
    PURPLE,
    YELLOW,
    CYAN,
    GREEN;

    static Map<Color, String> coloursMap;

    static {
        coloursMap = new HashMap<>();
        coloursMap.put(Color.WHITE, "e0e0e0;");
        coloursMap.put(Color.RED, "#ff0000;");
        coloursMap.put(Color.BLUE, "#0000ff;");
        coloursMap.put(Color.PURPLE, "#ff00ff;");
        coloursMap.put(Color.YELLOW, "#ffff00;");
        coloursMap.put(Color.CYAN, "#00ffff;");
        coloursMap.put(Color.GREEN, "#00ff00;");
    }

    public static Color randomColor(int colors) { return Color.values()[new Random().nextInt(colors)];}
    public static String getColorHex(Color c) {
        return coloursMap.get(c);
    }

}
