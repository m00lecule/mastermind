package to2.model;

import jdk.internal.net.http.common.Pair;
import to2.BoardElements.Color;

import java.util.*;
import java.util.stream.IntStream;

public class Game {

    private int fields;
    private List<Color> secretSequence = new LinkedList<>();

    public int getScore() {
        if (!this.won)
            return 0;
        return /* difficulty factor, will change */1 * (rows - attempts + 1);
    }

    private int attempts;

    private int score;

    private int rows;

    public boolean wonGame() {
        return won;
    }

    private boolean won = false;

    public Game(int fields, int rows) {
        this.fields = fields;
        this.rows = rows;
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
        List<Color> matchedColors = new LinkedList<>();
        // Iterator<Color> secretIterator = secretSequence.iterator();
        Iterator<Color> guessesIterator = guesses.iterator();
        this.attempts++;

        List<Color> allColorsInSecretCode = new LinkedList<>(secretSequence);
        List<Color> colorsInGuess = new LinkedList<>(guesses);

        Color guessedColor;

        int guessedCorrectly = 0;

        int position = 0;

        while (guessesIterator.hasNext()) {
            guessedColor = guessesIterator.next();

            if (secretSequence.get(position).equals(guessedColor)) {
                guessedCorrectly++;
                allColorsInSecretCode.remove(guessedColor);
                colorsInGuess.remove(guessedColor);
                matchedColors.add(Color.RED);
            }
//            if (guessColor.equals(secretIterator.next())) {
//                score +=1;
//                matchedColors.add(Color.RED);
//            } else if (secretSequence.contains(guessColor)) {
//                matchedColors.add(Color.BLUE);
//                score +=2;
//            }
            position++;
        }

        guessesIterator = colorsInGuess.iterator();

        while (guessesIterator.hasNext()) {
            guessedColor = guessesIterator.next();
            if (allColorsInSecretCode.contains(guessedColor)){
                matchedColors.add(Color.BLUE);
                allColorsInSecretCode.remove(guessedColor);
            }

        }


        Collections.shuffle(matchedColors);

        if (guessedCorrectly == fields) {
            won = true;
        }

        return matchedColors;
    }


}
