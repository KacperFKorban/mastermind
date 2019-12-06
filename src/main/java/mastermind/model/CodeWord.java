package mastermind.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

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
        List<Color> guess = new ArrayList<>(this.getColors());
        for (Color solution: guess) {
            long sameColours = word.getColors().stream()
                    .filter(color -> color.equals(solution)).count();
            long repeatedColours = this.getColors().stream()
                    .filter(color -> color.equals(solution)).count();
            result += Math.min(sameColours, repeatedColours);
            guess.removeIf(color -> color.equals(solution));
        }
        return result;
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