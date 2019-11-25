package mastermind.model;

import javafx.scene.paint.Color;

import java.util.List;

public class CodeWord {
    private List<Color> colors;

    public CodeWord(List<Color> colors) {
        this.colors = colors;
    }

    int countCorrectlyPlacedColors(CodeWord word){
        int result = 0;
        return result;
    }

    int countAmountOfCorrectColors(CodeWord word){
        int result=0;
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

}