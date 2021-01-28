package laborator_fx;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Spinner;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    @FXML
    Canvas drawingCanvas;
    @FXML
    Spinner sizeValue;
    @FXML
    Spinner sidesValue;
    @FXML
    ChoiceBox colorValue;
    @FXML
    ChoiceBox shapeType;
    @FXML
    ChoiceBox drawnShapes;
    private int shapeCounter=0;
    private List<Shape> shapes=new ArrayList<Shape>();

    public void save(ActionEvent actionEvent)  {
        FileChooser fileChooser =new FileChooser();
        FileChooser.ExtensionFilter extFilter=new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        fileChooser.getExtensionFilters().add(extFilter);
        File file=fileChooser.showSaveDialog(Main.getPrimaryStage());
        SnapshotParameters snapshot=new SnapshotParameters();
        snapshot.setFill(Color.WHITE);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(drawingCanvas.snapshot(snapshot,null), null), "png", file);
        }
        catch(IOException ioException)
        {
            System.out.println("Eroare la operatia de save,cauza:"+ioException.getMessage());
        }
    }

    public void reset(ActionEvent actionEvent)
    {
        GraphicsContext gc=drawingCanvas.getGraphicsContext2D();
        gc.clearRect(0,0,drawingCanvas.getWidth(),drawingCanvas.getHeight());
    }

    private double[] regularRectangeWithSides(int x0, int y0, int radius, int sides)
    {
        double[] points = new double[sides];
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            points[i]=x;
            points[i+1]=y;
        }
        return points;

    }
    public void load(ActionEvent actionEvent) {
        FileChooser fileChooser =new FileChooser();
        FileChooser.ExtensionFilter extFilter=new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        fileChooser.getExtensionFilters().add(extFilter);
        File file=fileChooser.showOpenDialog(Main.getPrimaryStage());
        try {
            Image loadedFile = new Image(new FileInputStream(file));
            drawingCanvas.getGraphicsContext2D().drawImage(loadedFile,0,0);
        }
        catch(IOException ioExcetion)
        {
            System.out.println("Nu a fost gasita nici o imagine la locatia indicata!:"+ioExcetion.getMessage());
        }
    }
    public void exit(ActionEvent actionEvent)
    {
        System.exit(0);
    }
    public void draw(MouseEvent mouseEvent) {
        int sides= ((Integer) sidesValue.getValue()).intValue();
        int radius=((Integer) sizeValue.getValue()).intValue();
        String color=colorValue.getSelectionModel().getSelectedItem().toString();
        String type=shapeType.getSelectionModel().getSelectedItem().toString();
        System.out.println(type+" "+sides+" "+radius);
        if ("Regular Polygon".equals(type)) {
            Polygon pol=new Polygon(drawingCanvas,mouseEvent.getX(),mouseEvent.getY(),sides,radius,color);
            pol.drawShapeOnCanvas();
            drawnShapes.getItems().add((shapeCounter++)+".RegularPolygon");
            shapes.add(pol);
        }
        else if("Oval".equals(type))
        {
            Oval oval=new Oval(drawingCanvas,mouseEvent.getX(),mouseEvent.getY(),radius,color);
            oval.drawShapeOnCanvas();
            drawnShapes.getItems().add((shapeCounter++)+".Oval");
            shapes.add(oval);
        }
    }

    public void delete(ActionEvent actionEvent) {
        int index=drawnShapes.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        shapes.remove(index);
        GraphicsContext gc=drawingCanvas.getGraphicsContext2D();
        gc.clearRect(0,0,drawingCanvas.getWidth(),drawingCanvas.getHeight());
        for(Shape shapeIterator:shapes)
        {
            shapeIterator.drawShapeOnCanvas();
        }
        drawnShapes.getItems().remove(index);
    }
}
