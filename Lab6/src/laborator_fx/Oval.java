package laborator_fx;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Random;

public class Oval implements Shape{
    private Canvas canvas;
    private Point center;
    private int radius;
    private String color;

    public Oval(Canvas canvas, double x, double y,int radius,String color) {
        this.canvas = canvas;
        center=new Point(x,y);
        this.radius=radius;
        this.color=color.toUpperCase();
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void drawShapeOnCanvas() {
        canvas.getGraphicsContext2D().setFill(Color.web(color));
        canvas.getGraphicsContext2D().fillOval(center.getX(),center.getY(),radius*3,radius);
    }
}
