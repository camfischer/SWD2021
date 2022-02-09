import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class BaseChangeGUIFX extends Application {
    /**
     * Main method that JavaFX uses to launch the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start sets the scene and stage, start the GUI and gets access to the .fxml attached file. Is the "View"
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXapp.fxml"));
            Scene scene = new Scene(root);// attach scene graph to scene
            primaryStage.setTitle("Base Change Calculator"); // displayed in window's title bar
            primaryStage.setScene(scene); // attach scene to stage
            primaryStage.show();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
