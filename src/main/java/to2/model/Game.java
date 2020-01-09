package to2.model;

import to2.BoardElements.Color;

import java.util.*;
import java.util.stream.IntStream;
import java.lang.Math;

/**
 * Game class - holds information about current game, checks guesses, returns games score, creates code
 */
public class Game {

    private int fields = 4;
    private List<Color> secretSequence = new LinkedList<>();

    public int getScore() {
        if (!this.won)
            return 0;
        return (int) (Math.pow(colors, fields) * (rows - attempts + 1) * Math.pow(1.2, (rows - attempts)) - /* TIME */0);
    }

    private int attempts;

    private int score;

    private int rows;

    //TODO: make this variable matter maybe
    private int colors;

    public boolean wonGame() {
        return won;
    }

    private boolean won = false;

    public Game(int rows, int colors) {
        this.rows = rows;
        this.colors = colors;
        this.reset();
    }

    public void reset() {
        this.score = 0;
        this.secretSequence.clear();
        IntStream.range(0, fields).forEach(i -> secretSequence.add(Color.randomColor()));

        System.out.println(secretSequence.toString());
        this.won = false;
        attempts = 0;
    }

    public List<Color> compareSequence(List<Color> guesses) {
        this.attempts++;
        List<Color> matchedColors = new LinkedList<>();
        Iterator<Color> secretIterator = secretSequence.iterator();
        Iterator<Color> guessesIterator = guesses.iterator();

        List<Color> allColorsInSecretCode = new LinkedList<>(secretSequence);
        List<Color> colorsInGuess = new LinkedList<>(guesses);

        Color guessedColor, secrectColor;
        int guessedCorrectly = 0;

        while (guessesIterator.hasNext() && secretIterator.hasNext()) {
            guessedColor = guessesIterator.next();
            secrectColor = secretIterator.next();
            if (secrectColor.equals(guessedColor)) {
                guessedCorrectly++;
                allColorsInSecretCode.remove(guessedColor);
                colorsInGuess.remove(guessedColor);
                matchedColors.add(Color.RED);
            }
        }

        guessesIterator = colorsInGuess.iterator();

        while (guessesIterator.hasNext()) {
            guessedColor = guessesIterator.next();
            if (allColorsInSecretCode.contains(guessedColor)) {
                matchedColors.add(Color.BLUE);
                allColorsInSecretCode.remove(guessedColor);
            }
        }

        Collections.shuffle(matchedColors);

        if (guessedCorrectly == fields) {
            this.won = true;
        }

        return matchedColors;
    }
}