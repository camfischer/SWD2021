import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.awt.event.ActionEvent;

/**
 * Controller class that gets the values from the GUI
 */
public class Controller {

    @FXML
    private AnchorPane screen;

    @FXML
    private Button convert;

    @FXML
    private TextField desireBase;

    @FXML
    private TextField currBase;

    @FXML
    private TextField inputNum;

    @FXML
    private TextField outputNum;


    /*void btnConvertClicked(ActionEvent event) {
        try {
            BaseChangeModel model = new BaseChangeModel(currBase.getText(), Integer.parseInt(currBase.getText()), Integer.parseInt(desireBase.getText()));
            outputNum.setText(model.convertBase());
        } catch (NumberFormatException e ){
            currBase.setText("Current Base");
            desireBase.setText("Desired Base");
            inputNum.setText("Enter Number");
        }
    }*/

    /**
     * Fxml method that takes the press of the button and attempts to convert the number if it cant it resets.
     * @param actionEvent
     */
    @FXML
    public void btnConvertClicked(javafx.event.ActionEvent actionEvent) {
        try {
            BaseChangeModel model = new BaseChangeModel(inputNum.getText(), Integer.parseInt(currBase.getText()), Integer.parseInt(desireBase.getText()));
            outputNum.setText(model.convertBase());
        } catch (NumberFormatException e ){
            currBase.setText("Current Base");
            desireBase.setText("Desired Base");
            inputNum.setText("Enter Number");
        }
    }
}





