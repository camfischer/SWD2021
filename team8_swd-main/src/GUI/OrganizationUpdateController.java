package GUI;
import java.awt.event.ActionEvent;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Organization;
import model.Person;
import model.User;
import queries.OrganizationQueries;
import queries.PersonQueries;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Controller for the GUI in whcih the user can change their organization information in the database
 */
public class OrganizationUpdateController {

    @FXML
    private Button updateButton; //button that updates the user's information when pressed and sends them to Person Search Menu

    @FXML
    private TextField nameField; //field for user's name

    @FXML
    private TextField usernameField; //field for user's username

    @FXML
    private TextField passwordField; //field for user's password

    @FXML
    private TextField emailField; //field for user's email

    @FXML
    private TextField phoneField; //field for user's phone number

    @FXML
    private ChoiceBox<String> picChoice; //box for organization's choice of profile picture

    @FXML
    private ChoiceBox<String> vaxChoice; //box for user's choice of vax requirement

    @FXML
    private ChoiceBox<String> maskChoice; //box for user's choice of mask requirement

    @FXML
    private ChoiceBox<String> testChoice; //box for user's choice of test requirement

    @FXML
    private ChoiceBox<String> orgChoice; //box for user's choice of organization type

    @FXML
    private TextArea errorArea; //error box

    /**
     * Empty constructor of OrganizationUpdateController
     */
    public OrganizationUpdateController() {
    }

    /**
     * Initializes choice boxes for profile picture, vax requirement, mask requirement, test requiremnt, and organization type
     */
    public void initialize() {
        picChoice.setItems(FXCollections.observableArrayList("Valley", "Lake with Trees", "Lake at Sunset",
                "Lake at Sunrise", "Scenic Road", "Tree at Sunset"));
        vaxChoice.setItems(FXCollections.observableArrayList("Must Be Vaccinated", "Vaccine Not Mandated"));
        maskChoice.setItems(FXCollections.observableArrayList("Mask Required", "Mask Not Required"));
        testChoice.setItems(FXCollections.observableArrayList("COVID Test Required", "COVID Test Not Required"));
        orgChoice.setItems(FXCollections.observableArrayList("Retail", "Healthcare", "Leisure", "Office", "Government"));
        errorArea.setEditable(false);
        errorArea.setWrapText(true);
    }

    /**
     * When the update button gets pressed, the database will update the user's information with any of the items changed within the GUI
     * @param actionEvent Update button is pressed
     * @throws IOException
     */
    @FXML
    public void updateButtonPressed(javafx.event.ActionEvent actionEvent) throws IOException {
        //Will add organization to database and create new Person instance.
        try {
            if(usernameField.getText().isEmpty() || nameField.getText().isEmpty() || passwordField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || orgChoice.getValue().isEmpty())
            {
                errorArea.setText("Please fill in all required values");
            }

            OrganizationQueries changeOrg = new OrganizationQueries();
            OrganizationQueries query = new OrganizationQueries();
            List<Organization> list = query.getAllOrganizations(); //calls list of all organizations in the database
            for (Organization org : list) {
                if (usernameField.getText().equals(org.getUsername()) || passwordField.getText().equals(org.getPassword()) || emailField.getText().equals(org.getEmail()) || phoneField.getText().equals(org.getPhone())) {
                    int vax, mask, test; //int values for vax, mask, and test requirement truth values
                    if (vaxChoice.getValue().equals("Must Be Vaccinated")) {
                        vax = 1;
                    } else {
                        vax = 0;
                    }
                    if (maskChoice.getValue().equals("Mask Required")) {
                        mask = 1;
                    } else {
                        mask = 0;
                    }
                    if (testChoice.getValue().equals("COVID Test Required")) {
                        test = 1;
                    } else {
                        test = 0;
                    }
                    //update's user's info in the database
                    changeOrg.updateOrganization(usernameField.getText(), nameField.getText(), emailField.getText(), phoneField.getText(), picChoice.getValue(), passwordField.getText(), vax, mask, test, orgChoice.getValue());
                }
            }
            //sends user back to the Person Search Menu when update is pushed
            FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/OrganizationMenu.fxml"));
            AnchorPane root = load.load();

            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Person Search");
            primaryStage.setScene(scene);

            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets fields according to user's information when the window is diplayed
     * @param org organization who's information is being displayed
     */
    public void setTextFields(Organization org) {
        nameField.setText(org.getName());
        usernameField.setText(org.getUsername());
        emailField.setText(org.getEmail());
        phoneField.setText(org.getPhone());
        picChoice.setValue(org.getProfilePic());
        orgChoice.setValue(org.getOrganizationType());
        if (org.isVaccine()) {
            vaxChoice.setValue("Must Be Vaccinated");
        } else {
            vaxChoice.setValue("Vaccine Not Mandated");
        }
        if (org.isMask()) {
            maskChoice.setValue("Mask Required");
        } else {
            maskChoice.setValue("Mask Not Required");
        }
        if (org.isTest()) {
            maskChoice.setValue("COVID Test Required");
        } else {
            maskChoice.setValue("COVID Test Not Required");
        }
        usernameField.setEditable(false); //disables editing username
    }
}
