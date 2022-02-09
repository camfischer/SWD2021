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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Controller for the GUI in which the user updates their individual information in the database
 */
public class PersonUpdateController {

    @FXML
    private Button updateButton; //button that updates user info and sends them back to Organization Search Menu

    @FXML
    private TextField nameField; //field for user's name

    @FXML
    private TextField usernameField; //field for user's username

    @FXML
    private TextField passwordField; //field for user's password

    @FXML
    private TextField emailField; //field for user's email

    @FXML
    private TextField idField; //field for user's id card url

    @FXML
    private TextField phoneField; //field for user's phone number

    @FXML
    private TextField vaxField; //field for user's vax card url

    @FXML
    private TextField dateField; //field for user to enter date of their last COVID test

    @FXML
    private ChoiceBox<String> picChoice; //box for user to change their profile picture
 
    @FXML
    private ChoiceBox<String> vaxChoice; //field for user to change their vax status

    @FXML
    private ChoiceBox<String> testChoice; //field for user to change their test status

    @FXML
    private TextArea errorArea; //error box


    /**
     * Empty constructor for PersonUpdateController
     */
    public PersonUpdateController() {

    }

    /**
     * Intitializes choice boxes for proile picture, vaccination status, and whether user has been tested recently
     */
    public void initialize() {
        picChoice.setItems(FXCollections.observableArrayList("Valley", "Lake with Trees", "Lake at Sunset",
                "Lake at Sunrise", "Scenic Road", "Tree at Sunset"));
        vaxChoice.setItems(FXCollections.observableArrayList("Vaccinated", "Not Vaccinated"));
        testChoice.setItems(FXCollections.observableArrayList("Yes", "No"));
        errorArea.setEditable(false);
        errorArea.setWrapText(true);
        usernameField.setEditable(false);
    }

    /**
     * When the update button gets pressed, the database will update the user's information with any of the items changed within the GUI
     * @param actionEvent Update button is pressed
     * @throws IOException
     */
    @FXML
    public void updateButtonPressed(javafx.event.ActionEvent actionEvent) throws IOException {
        //Will add person to database and create new Person instance.
        try {
            if(usernameField.getText().isEmpty() || nameField.getText().isEmpty() || passwordField.getText().isEmpty() || idField.getText().isEmpty() || emailField.getText().isEmpty())
            {
                errorArea.setText("Please fill in all values");
            }

            PersonQueries changePerson = new PersonQueries();
            PersonQueries query = new PersonQueries();
            List<Person> list = query.getAllPersons();  //calls a list of all the people in the database
            for (Person person : list) {
                //checks the unique values for each person to find the user
                if (usernameField.getText().equals(person.getUsername()) || passwordField.getText().equals(person.getPassword()) || idField.getText().equals(person.getId()) || vaxField.getText().equals(person.getVaccineCard()) || phoneField.getText().equals(person.getPhone()) || emailField.getText().equals(person.getEmail())) {
                    boolean dateApproved = false; //checks if user correclty formatted date input
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //checks the format for date input
                    dateFormat.setLenient(false);
                    String dateTest = null; //string for the date
                    if(testChoice.getValue().equals("Yes")) {  //if user has gotten tested recently
                        try {
                            dateFormat.parse(dateField.getText());
                            dateApproved = true;
                            dateTest = dateField.getText(); 
                        } catch (ParseException e) {
                            errorArea.setText("Please enter valid date format");
                        }
                    }
                    boolean vax; //truth value for whether user is vaccinated or not
                    if (vaxChoice.getValue().equals("Vaccinated")) {
                        vax = true;
                    } else {
                        vax = false;
                    }

                    //updates user's info in the database
                    if(dateApproved || testChoice.getValue().equals("No")) {
                        changePerson.updatePerson(usernameField.getText(), nameField.getText(), emailField.getText(),
                                phoneField.getText(), picChoice.getValue(), passwordField.getText(), vax, dateTest, vaxField.getText(), idField.getText());
                        //sends user back to Organization Search Menu when update is pushed
                        FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/PersonMenu.fxml"));
                        AnchorPane root = load.load();

                        PersonMenuController personMenuController = load.getController();
                        personMenuController.setUsernameField(usernameField.getText()); //sets the username in Organization Search to user's username
                        Stage primaryStage = new Stage();
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Organization Search");
                        primaryStage.setScene(scene);

                        Stage stage = (Stage) updateButton.getScene().getWindow();
                        stage.close();
                        primaryStage.show();
                    }
                    else {
                        errorArea.setText("Please enter valid date format");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the fields according to the user's information when the update window opens
     * @param person the user's information that will be displayed
     */
    public void setTextFields(Person person) {
        nameField.setText(person.getName());
        usernameField.setText(person.getUsername());
        emailField.setText(person.getEmail());
        phoneField.setText(person.getPhone());
        picChoice.setValue(person.getProfilePic());
        vaxField.setText(person.getVaccineCard());
        if (person.isVaccine()) {
            vaxChoice.setValue("Vaccinated");
        } else {
            vaxChoice.setValue("Not Vaccinated");
        }
        if (person.getTest() == null) {
            testChoice.setValue("No");
        } else {
            testChoice.setValue("Yes");
        }
        usernameField.setEditable(false);
    }
}
