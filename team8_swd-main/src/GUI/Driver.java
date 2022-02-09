package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Driver class that runs program and takes user to the log in screen GUI, where they begin process of searching for person/org
 * in database, or create their account in the database
 */
public class Driver extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LoginMenu.fxml"));
        //When program begins runner, login menu is opened
        Driver.stage = stage;
        Scene scene  = new Scene(root);
        stage.setTitle("Login Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void closeWindow() {
        stage.close();
    } //stops program when user closes GUI window
}
