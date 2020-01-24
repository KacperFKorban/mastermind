package mastermind.model;

import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class GameSession {

    private String name;
    private int guessWordLength;
    private int coloursQuantity;
    private int maxGuessQuantity;
    private boolean guessed;
    private int guessesMade;
    private CodeWord solution;
    private List<Pair<String, Integer>> ranking;

    private int dispenserWidth;

    public GameSession(String name, int guessWordLength, int coloursQuantity, int maxGuessQuantity) {
        this.name = name;
        this.guessWordLength = guessWordLength;
        setColoursQuantity(coloursQuantity);
        this.maxGuessQuantity = maxGuessQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuessWordLength(int guessWordLength) {
        this.guessWordLength = guessWordLength;
    }

    public void setColoursQuantity(int coloursQuantity) {
        this.coloursQuantity = coloursQuantity;

        int maxHeight = 400;
        if (coloursQuantity > maxHeight / 100) {
            int h = maxHeight / 100;
            dispenserWidth = (coloursQuantity + h - 1) / h; // Round up
        } else {
            dispenserWidth = 1;
        }
    }

    public void setMaxGuessQuantity(int maxGuessQuantity) {
        this.maxGuessQuantity = maxGuessQuantity;
    }

    public String getName() {
        return name;
    }

    public int getGuessWordLength() {
        return guessWordLength;
    }

    public int getColoursQuantity() {
        return coloursQuantity;
    }

    public int getMaxGuessQuantity() {
        return maxGuessQuantity;
    }

    public int getDispenserWidth() {
        return dispenserWidth;
    }

    public int getDispenserHeight() {
        return (coloursQuantity + dispenserWidth - 1) / dispenserWidth;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public int getGuessesMade() {
        return guessesMade;
    }

    public void setGuessesMade(int guessesMade) {
        this.guessesMade = guessesMade;
    }

    public CodeWord getSolution() {
        return solution;
    }

    public void setSolution(CodeWord solution) {
        this.solution = solution;
    }

    public List<Pair<String, Integer>> getRanking() {
        return ranking;
    }

    public void setRanking(List<Pair<String, Integer>> ranking) {
        this.ranking = ranking;
    }

    public Integer getScore() {
        return (10 * this.getGuessWordLength()
                + 5 * this.getColoursQuantity())
                / this.getGuessesMade()
                * 100
                * (this.isGuessed() ? 1 : 0);
    }
}
