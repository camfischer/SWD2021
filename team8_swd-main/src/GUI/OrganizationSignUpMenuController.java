package GUI;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Organization;
import queries.OrganizationQueries;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class OrganizationSignUpMenuController {
    /**
     * Button that signs up the user
     */
    @FXML
    private Button signUpButton;
    /**
     * Text field that holds the Name of the Organization
     */
    @FXML
    private TextField nameText;
    /**
     * Text field that holds the Username of the Organization
     */
    @FXML
    private TextField usernameText;
    /**
     * Text field that holds password for the Organization, gets encrypted when signed up
     */
    @FXML
    private TextField passwordText;
    /**
     * Text field for the users email address
     */
    @FXML
    private TextField emailText;
    /**
     * Text field for the users phone number
     */
    @FXML
    private TextField phoneNumString;
    /**
     * ChoiceBox of strings that hold VaccinationChoice, Yes for requiring vaccines and No for not required
     */
    @FXML
    private ChoiceBox<String> vaccinationChoice;
    /**
     * Text area that displays any error that occurred during the sign up process.
     */
    @FXML
    private TextArea errorText;
    /**
     * Choice Box for Organization to choose if they require being tested
     */
    @FXML
    private ChoiceBox<String> testedBox;
    /**
     *  Choice Box that holds images for desired profile picture
     */
    @FXML
    private ChoiceBox<String> profilePictureBox;
    /**
     * Choice Box for the organization to determine if they require masks
     */
    @FXML
    private ChoiceBox<String> masksRequired;
    /**
     * Choice Box for organization to determine what type they are which include Retail", "Healthcare", "Leisure", "Office", "Government"
     */
    @FXML  
    private ChoiceBox<String> orgType;
    /**
     * Initialize values in GUI
     */
    public void initialize(){
        vaccinationChoice.setItems(FXCollections.observableArrayList("Yes", "No"));
        profilePictureBox.setItems(FXCollections.observableArrayList("Valley", "Lake with Trees" , "Lake at Sunset",
                "Lake at Sunrise", "Scenic Road", "Tree at Sunset"));
        testedBox.setItems(FXCollections.observableArrayList("Yes", "No"));
        orgType.setItems(FXCollections.observableArrayList("Retail", "Healthcare", "Leisure", "Office", "Government"));
        vaccinationChoice.setItems(FXCollections.observableArrayList("Yes", "No"));
        masksRequired.setItems(FXCollections.observableArrayList("Yes", "No"));
        errorText.setEditable(false);
        errorText.setWrapText(true);
    }

    /**
     * When signup button is pressed check to make sure all values are filled in, if they are add person to Query.
     * @param actionEvent
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @FXML
    public void SignUpButtonPressed(javafx.event.ActionEvent actionEvent) throws NoSuchAlgorithmException, IOException {
        boolean approved = true;
        OrganizationQueries organizationQueries = new OrganizationQueries();
        if(nameText.getText().isEmpty() || usernameText.getText().isEmpty() || phoneNumString.getText().isEmpty() || passwordText.getText().isEmpty() || emailText.getText().isEmpty() || orgType.getValue().isEmpty()){
            errorText.setText("Please fill in all required values");
        }
        List<Organization> organizations = organizationQueries.getAllOrganizations();
        for(Organization organization:organizations){
            if(usernameText.getText().equals(organization.getUsername())){
                errorText.setText("Username Taken");
                approved = false;
            }

        }
        if(approved) {
            boolean test, vaccine, mask;
            if (vaccinationChoice.getValue().equals("Yes")) {
                vaccine = true;
            } else {
                vaccine = false;
            }

            if (testedBox.getValue().equals("Yes")) {
                test = true;
            } else {
                test = false;
            }

            if(masksRequired.getValue().equals("Yes")){
                mask = true;
            }else{
                mask = false;
            }
            //Add to query
            organizationQueries.addOrganization(this.usernameText.getText(), this.nameText.getText(), this.emailText.getText(), this.phoneNumString.getText(), this.profilePictureBox.getValue(), this.passwordText.getText(), vaccine, mask, test, orgType.getValue() );

            FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/OrganizationMenu.fxml"));
            AnchorPane root = load.load();

            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Person Search Menu");
            primaryStage.setScene(scene);

            Stage stage = (Stage) signUpButton.getScene().getWindow();
            stage.close();
            primaryStage.show();

        }
    }
}
