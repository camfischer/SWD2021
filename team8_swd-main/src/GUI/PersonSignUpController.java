package GUI;


import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
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
import pictures.PictureList;
import queries.OrganizationQueries;
import queries.PersonQueries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javafx.scene.text.Text;


public class PersonSignUpController {
    /**
     * Button that signs up User when pressed
     */
    @FXML
    private Button signUpButton;
    /**
     * Text Field that user enters their name in
     */
    @FXML
    private TextField nameText;
    /**
     * Text Field that user enters the date of their last test YYYY-mm--dd
     */
    @FXML
    private TextField dateTest;
    /**
     * Text Field that user enters their username in
     */
    @FXML
    private TextField usernameText;
    /**
     * Text Field that user enters their password in
     */
    @FXML
    private TextField passwordText;
    /**
     * Text Field that user enters their email in
     */
    @FXML
    private TextField emailText;
    /**
     * Text Field that user enters their phone number in
     */
    @FXML
    private TextField phoneNumString;
    /**
     * Choice box for person to choose profile picture from list
     */
    @FXML
    private ChoiceBox<String> profilePictureBox;
    /**
     * Vaccination choice box for user to pick if they've been vaccinated or not
     */
    @FXML
    private ChoiceBox<String> vaccinationChoice;
    /**
     * Text Field that user enters the url of the Vaccine card in
     */
    @FXML
    private TextField vaccineImageURL;
    /**
     * Text Field that user enters the url of their ID in
     */
    @FXML
    private TextField iDurl;
    /**
     * Text Area that displays any error in signing up
     */
    @FXML
    private TextArea errorText;
    /**
     * Choice box that holds if the user has been tested
     */
    @FXML
    private ChoiceBox<String> testedBox;
    /**
     * Text that displays format, variable here in case someone wanted to toggle it
     */
    @FXML
    private Text displayTextDate;

    /**
     * Initializing values in GUI, added logic for test date, if someone has been tested we should have their last test date
     * and if not we shouldn't ask for test date
     */
    public void initialize() {
        vaccinationChoice.setItems(FXCollections.observableArrayList("Yes", "No"));
        profilePictureBox.setItems(FXCollections.observableArrayList("Valley", "Lake with Trees", "Lake at Sunset",
                "Lake at Sunrise", "Scenic Road", "Tree at Sunset"));
        testedBox.setItems(FXCollections.observableArrayList("Yes", "No"));
        vaccinationChoice.setItems(FXCollections.observableArrayList("Yes", "No"));
        errorText.setEditable(false);
        errorText.setWrapText(true);
        displayTextDate.setVisible(true);
        dateTest.setVisible(true);
    }

    /**
     * When button is pressed create a new person in the Query and checking to make sure all required values are filled in
     * @param actionEvent
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @FXML
    public void signUpButtonPressed(javafx.event.ActionEvent actionEvent) throws NoSuchAlgorithmException, IOException {
        boolean approved = true;
        PersonQueries personQueries = new PersonQueries();
        if(nameText.getText().isEmpty() || usernameText.getText().isEmpty() || phoneNumString.getText().isEmpty() || passwordText.getText().isEmpty() || emailText.getText().isEmpty()){
            errorText.setText("Please fill in all values");
        }
        List<Person> persons = personQueries.getAllPersons();
         for(Person person:persons){
            if(usernameText.getText().equals(person.getUsername())){
                errorText.setText("Username Taken");
                approved = false;
            }
        }

        boolean dateApproved = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(dateTest.getText());
            dateApproved = true;
        }catch(ParseException e){
            errorText.setText("Please enter valid date format");
        }

        if(approved) {
            if(dateApproved || testedBox.getValue().equals("No")) {
                boolean test, vaccine;
                if (vaccinationChoice.getValue().equals("Yes")) {
                    vaccine = true;
                } else {
                    vaccine = false;
                }

                String dateTestText = null;
                if (testedBox.getValue().equals("Yes")) {
                    test = true;
                    dateTestText = dateTest.getText();
                } else {
                    test = false;
                    this.dateTest = null;
                }
                //Adding new person to query
                personQueries.addPerson(this.usernameText.getText(), this.nameText.getText(), this.emailText.getText(), this.phoneNumString.getText(), this.profilePictureBox.getValue(), this.passwordText.getText(), vaccine, dateTestText, this.vaccineImageURL.getText(), this.iDurl.getText());

                FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/PersonMenu.fxml"));
                AnchorPane root = load.load();

                PersonMenuController personMenuController = load.getController();
                personMenuController.setUsernameField(usernameText.getText());
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setTitle("Person Search Menu");
                primaryStage.setScene(scene);

                Stage stage = (Stage) signUpButton.getScene().getWindow();
                stage.close();
                primaryStage.show();
            }
            else {
                errorText.setText("Please enter valid date format");
            }
        }



        //Will add person to database and create new Person instance.
    }
}

