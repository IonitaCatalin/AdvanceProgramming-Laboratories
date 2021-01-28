package laborator_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));
        primaryStage.setScene(new Scene(root, 850 , 800));
        primaryStage.show();
    }
    public static Stage getPrimaryStage()
    {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
