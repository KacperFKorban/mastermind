package mastermind.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.function.BiConsumer;

public class DragHandler {

    private double offsetX;
    private double offsetY;

    private Circle dragged = new Circle();

    private BiConsumer<MouseEvent, Color> onDropped = (e, c) -> {};

    public DragHandler() {
        dragged.setVisible(false);
    }

    public void startDrag(MouseEvent event, Circle circle) {
        offsetX = circle.getCenterX() - event.getSceneX();
        offsetY = circle.getCenterY() - event.getSceneY();
        dragged.setRadius(circle.getRadius());
        dragged.setFill(circle.getFill());
        dragged.setCenterX(circle.getCenterX());
        dragged.setCenterY(circle.getCenterY());
        dragged.setVisible(true);
    }

    public void onDrag(MouseEvent event) {
        dragged.setCenterX(event.getSceneX() + offsetX);
        dragged.setCenterY(event.getSceneY() + offsetY);
    }

    public void onDrop(MouseEvent event) {
        dragged.setVisible(false);
        onDropped.accept(event, (Color) dragged.getFill());
    }

    public void setOnDropped(BiConsumer<MouseEvent, Color> onDropped) {
        this.onDropped = onDropped;
    }

    public Circle getDragCircle() {
        return dragged;
    }
}
