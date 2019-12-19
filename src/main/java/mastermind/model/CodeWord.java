package mastermind.model;

import javafx.scene.paint.Color;
import mastermind.controller.DispenserController;

import java.util.*;

public class CodeWord {
    private List<Color> colors;

    public CodeWord(List<Color> colors) {
        this.colors = colors;
    }

    public static CodeWord empty(int size) {
        ArrayList<Color> colors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            colors.add(Color.DIMGRAY);
        }
        return new CodeWord(colors);
    }

    public static CodeWord random(GameSession gameSession) {
        Random random = new Random();
        List<Color> colors = new ArrayList<>(gameSession.getGuessWordLength());
        for (int i = 0; i < gameSession.getGuessWordLength(); i++) {
            int color = random.nextInt(gameSession.getColoursQuantity());
            colors.add(DispenserController.COLORS.get(color));
        }
        return new CodeWord(colors);
    }

    public boolean checkIfEveryColorIsChosen(){
        return this.colors.stream().allMatch(color -> !color.equals(Color.DIMGRAY));
    }

    int countCorrectlyPlacedColors(CodeWord word){
        int result = 0;
        for(int i=0; i<this.colors.size(); i++){
            if(this.colors.get(i).equals(word.getColors().get(i)))
                result++;
        }
        return result;
    }

    int countAmountOfCorrectColors(CodeWord word){
        int result=0;
        Set<Color> guess = new HashSet<>(this.getColors());
        for (Color solution: guess) {
            long sameColours = countAmountOfTheSameColours(word,solution);
            long repeatedColours = countAmountOfTheSameColours(this,solution);
            result += Math.min(sameColours, repeatedColours);
        }
        return result;
    }

    private long countAmountOfTheSameColours(CodeWord word, Color color){
        return word.getColors().stream()
                .filter(c -> c.equals(color))
                .count();
    }

    int countIncorrectlyPlacedColors(CodeWord word){
        return this.colors.size() - countCorrectlyPlacedColors(word);
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public void setColor(int index, Color color) {
        colors.set(index, color);
    }
}
