package mastermind.model;

import java.util.ArrayList;
import java.util.List;

public class Board{
    private List<Guess> pastGuesses = new ArrayList<>();
    private CodeWord solution;

    public Board(CodeWord solution) {
        this.solution = solution;
    }

    public boolean submitGuess(Guess actualGuess){
        if(actualGuess.getWord().checkIfEveryColorIsChosen()){
            int correctColors = actualGuess.getWord().countAmountOfCorrectColors(solution);
            int correctPlaces = actualGuess.getWord().countCorrectlyPlacedColors(solution);
            actualGuess.setCorrectColors(correctColors - correctPlaces);
            actualGuess.setCorrectPlaces(correctPlaces);
            this.pastGuesses.add(actualGuess);
            return true;
        }
        return false;
    }

    public boolean isGameOver(){
        int size = this.pastGuesses.size();
        return this.pastGuesses.get(size-1).getCorrectPlace() == this.solution.getColors().size();
    }

    public List<Guess> getPastGuesses() {
        return pastGuesses;
    }

    public void setPastGuesses(List<Guess> pastGuesses) {
        this.pastGuesses = pastGuesses;
    }

    public CodeWord getSolution() {
        return solution;
    }

    public void setSolution(CodeWord solution) {
        this.solution = solution;
    }
}