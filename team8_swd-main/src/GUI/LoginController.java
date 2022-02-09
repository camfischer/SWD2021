package GUI;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/**
 * Controller for GUI in which user logs in or creates an acount
 */
public class LoginController {

    @FXML
    private Button loginButton; //button that logs user in and takes them to a menu depending on their user type

    @FXML
    private Button signupButton; //but for people/orgs not in database that takes them to sign up menu to create account

    @FXML
    private ChoiceBox<String> userChoiceBox; //box with choices for user type

    @FXML
    private TextField usernameField; //field for user to enter their username

    @FXML
    private TextField passwordField; //username for user to enter their password

    @FXML
    private TextArea errorArea; //error box

    /**
     * Empty constructor for LoginController
     */
    public LoginController() {

    }

    /**
     * Sets choices for user type and initializes it to show "Organization" when menu is opened, disables editing the error box, 
     */
    public void initialize() {
        userChoiceBox.setItems(FXCollections.observableArrayList("Organization", "Individual"));
        errorArea.setEditable(false);
        errorArea.setWrapText(true);
        userChoiceBox.setValue("Organization");
    }

    /**
     * When login button is pressed, queries are called to match
     * username and password to respective type of user and send user to search menu depending on the user type
     * @param event When login button is pressed
     * @throws IOException
     */
    @FXML
    private void loginButtonPressed(ActionEvent event) throws IOException {
        if(userChoiceBox.getValue().equals("Organization")) { //if the user is an organization
            OrganizationQueries organizationQueries = new OrganizationQueries();

            try {
                Organization organization = organizationQueries.getOrganizationByUserName(usernameField.getText()); 
                //looks for orgs by their username

                if(organization == null) {
                    throw new FileNotFoundException();
                }

                //if entered password matches password in database, user is taken to Person Search Menu
                if(organization.getPassword().equals(User.hashPassword(passwordField.getText()))) {
                    FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/OrganizationMenu.fxml"));
                    AnchorPane root = load.load();

                    //gets org's username to be referenced in other menus
                    OrganizationMenuController organizationMenuController = load.getController();
                    organizationMenuController.setUsernameField(usernameField.getText());
                    Stage primaryStage = new Stage();
                    Scene scene  = new Scene(root);
                    primaryStage.setTitle("Person Search");
                    primaryStage.setScene(scene);

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    primaryStage.show();
                }

            }
            catch(NoSuchAlgorithmException | FileNotFoundException exception) {
                if(exception.getClass() == FileNotFoundException.class) {
                    errorArea.setText("That username and password combination does not exist."); //if unique username and password dont match up
                }
                else {
                    System.out.println("That algorithm doesn't exist.");
                }
            }
        }
        else if(userChoiceBox.getValue().equals("Individual")) { //if the user is an individual
            PersonQueries personQueries = new PersonQueries();

            try {
                Person person = personQueries.getPersonByUserName(usernameField.getText()); //searches for person based on the username typed in

                if(person == null) {
                    throw new FileNotFoundException();
                }
                //if the entered password matches that of the database for the user, theya re taken to Organization Search Menu
                if(person.getPassword().equals(User.hashPassword(passwordField.getText()))) {
                    FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/PersonMenu.fxml"));
                    AnchorPane root = load.load();
                    PersonMenuController personMenuController = load.getController();
                    personMenuController.setUsernameField(usernameField.getText());
                    Stage primaryStage = new Stage();
                    Scene scene  = new Scene(root);
                    primaryStage.setTitle("Organization Search");
                    primaryStage.setScene(scene);

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    primaryStage.show();
                }
                else {
                    errorArea.setText("That username and password combination does not exist.");
                }
            }
            catch(NoSuchAlgorithmException | FileNotFoundException exception) {
                if(exception.getClass() == FileNotFoundException.class) {
                    errorArea.setText("That username and password combination does not exist.");
                }
                else {
                    System.out.println("That algorithm doesn't exist.");
                }
            }
        }
    }

    /**
     * When sign up button is pressed, the user is taken to the sign up menu if they do not have an account depending on user type
     * @param event When sign up button is pressed
     * @throws IOException
     */
    @FXML
    public void signupButtonPressed(ActionEvent event) throws IOException {
        //if user wants to make an organization account, they are taken to the Organization Signup Menu to make account
        if(userChoiceBox.getValue().equals("Organization")) {
            AnchorPane root = FXMLLoader.load(getClass().getResource("fxml/OrganizationSignUpMenu.fxml"));

            Stage primaryStage = new Stage();
            Scene scene  = new Scene(root);
            primaryStage.setTitle("New Organization");
            primaryStage.setScene(scene);

            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.close();
            primaryStage.show();
        }
        //if user wants to make an individual account, they are taken to the Person Signup Menu to make account
        else if(userChoiceBox.getValue().equals("Individual")) {
            AnchorPane root = FXMLLoader.load(getClass().getResource("fxml/PersonSignUpMenu.fxml"));

            Stage primaryStage = new Stage();
            Scene scene  = new Scene(root);
            primaryStage.setTitle("New Person");
            primaryStage.setScene(scene);

            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.close();
            primaryStage.show();
        }
        else {
            errorArea.setText("Please select type of user before signing up."); //if no user type is selected
        }

    }

}
