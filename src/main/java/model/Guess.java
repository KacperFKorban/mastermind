package model;
public class Guess{

    private CodeWord word;
    private int correctPlaces;
    private int correctColors;

    public Guess(CodeWord word, int correctPlaces, int correctColors) {
        this.word = word;
        this.correctPlaces = correctPlaces;
        this.correctColors = correctColors;
    }

    public CodeWord getWord() {
        return word;
    }

    public int getCorrectPlace() {
        return correctPlaces;
    }

    public void setCorrectPlaces(int correctPlaces) {
        this.correctPlaces = correctPlaces;
    }

    public int getCorrectColor() {
        return correctColors;
    }

    public void setCorrectColors(int correctColors) {
        this.correctColors = correctColors;
    }
}