package laborator_fx;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Polygon implements Shape {

    List<Point> points=new ArrayList<>();
    Canvas canvas;
    String color;
    int sides;
    public Polygon(Canvas canvas,double x,double y,int sides,int radius,String color) {
        this.color=color.toUpperCase();
        this.sides=sides;
        this.canvas=canvas;
        generatePolygonPoints(x,y,sides,radius);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    private void generatePolygonPoints(double x0, double y0, int sides, int radius) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            points.add(new Point(x, y));
        }
    }


    @Override
    public void drawShapeOnCanvas() {
        canvas.getGraphicsContext2D().setFill(Color.web(color));
        double[] xCoord=points.stream().mapToDouble(d->d.getX()).toArray();
        double[] yCoord=points.stream().mapToDouble(d->d.getY()).toArray();
        canvas.getGraphicsContext2D().fillPolygon(xCoord,yCoord,sides);
    }
}
