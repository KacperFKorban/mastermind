package mastermind.model;

import java.util.List;

public class Board{
    private List<Guess> pastGuesses;
    private CodeWord solution;

    public Board(CodeWord solution) {
        this.solution = solution;
    }

    public boolean submitGuess(Guess actualGuess){
        if(actualGuess.getWord().checkIfEveryColorIsChosen()){
            actualGuess.setCorrectColors(actualGuess.getWord().countAmountOfCorrectColors(this.solution));
            actualGuess.setCorrectPlaces(actualGuess.getWord().countCorrectlyPlacedColors(this.solution));
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