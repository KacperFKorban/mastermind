package mastermind.model;

import java.util.List;

public class Board{
    private List<Guess> pastGuesses;
    private CodeWord solution;

    public Board(CodeWord solution) {
        this.solution = solution;
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