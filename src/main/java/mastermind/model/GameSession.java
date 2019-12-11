package mastermind.model;

public class GameSession {

    private String name;
    private int guessWordLength;
    private int coloursQuantity;
    private int maxGuessQuantity;
    private boolean guessed;
    private int guessesMade;

    public GameSession(String name, int guessWordLength, int coloursQuantity, int maxGuessQuantity) {
        this.name = name;
        this.guessWordLength = guessWordLength;
        this.coloursQuantity = coloursQuantity;
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
        return 1;
    }

    public int getDispenserHeight() {
        return coloursQuantity;
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

    public Integer getScore() {
        return (10 * this.getGuessWordLength()
                + 5 * this.getColoursQuantity())
                / this.getGuessesMade()
                * 100
                * (this.isGuessed() ? 1 : 0);
    }
}
