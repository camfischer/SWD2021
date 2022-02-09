package GUI;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Person;
import queries.OrganizationQueries;
import queries.PersonQueries;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for GUI in which the user searches for a person in the database
 */
public class OrganizationMenuController {

    @FXML
    private Button updateButton; //button that takes the use to the update screen

    @FXML
    private TextField searchField; //field where user can search for the individual

    @FXML
    private ChoiceBox<String> searchBox; //box where the user can choose whether to search by name, username, or both

    @FXML
    private ChoiceBox<String> displayField; //box where the user can select a person based of the filters

    @FXML
    private TextField usernameField; //field that displays person's username

    @FXML
    private TextField nameField; //field that displays person's name

    @FXML
    private TextField emailField; //field that displays person's email

    @FXML
    private TextField phoneField; //field that displays person's phone number

    @FXML
    private TextField vaxField; //field that displays person's vax status

    @FXML
    private TextField dateField; //field that displays date of person's COVID test

    @FXML
    private ImageView vaxCard; //box that displays person's vax card

    @FXML
    private ImageView idCard; //box that displays person's ID card

    @FXML
    private ImageView profilePic; //box that displays person's profile picture

    private String userName;

    /**
     * Empty constructor for OrganizationMenuController
     */
    public OrganizationMenuController()
    {

    }


    /**
     * Sets items in the search choice box for the method of searching the user while initially displaying "All", disables
     * editing of display fields with person's information
     */
    public void initialize() {
        searchBox.setItems(FXCollections.observableArrayList("All", "Name", "Username"));
        searchBox.setValue("All");
        usernameField.setEditable(false);
        nameField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);
        dateField.setEditable(false);
        vaxField.setEditable(false);

    }

    /**
     * Sets the choice box for display based on what is in the search box
     * @param event When search button gets pressed
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void searchButtonPressed(ActionEvent event) throws IOException, SQLException {

        try {
            PersonQueries query = new PersonQueries(); //calls PersonQueries to get every person and their information
            if (searchBox.getValue().equals("All")) {
                List<Person> list = query.getAllPersons(); //list of all of the people and their information
                List<String> nameList = new ArrayList<>(); //list of all peoples' names and usernames

                assert list != null;
                for(Person person : list) {
                    nameList.add(person.getName()); //adds names and usernames to the list that fit criteria of filters
                }
                displayField.setItems(FXCollections.observableArrayList(nameList)); //displays items from nameList as choices
            } else if (searchBox.getValue().equals("Name")) { //if user searches person by their name
                List<Person> list = query.getPersonsByName(searchField.getText()); //list of names to choose from
                List<String> nameList = new ArrayList<>();
                for(Person person : list) {
                    nameList.add(person.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList)); //displays name choices
            } else {
                Person person= query.getPersonByUserName(searchField.getText()); //if user searches by username
                displayField.setItems(FXCollections.observableArrayList(person.getName()));  //displays username choices
            }
        }
        catch(SQLException sqlException) {
        }
    }

    /**
     * Displays all of the user's information including their name, username, email, phone number,
     * date of their last COVID test, vaccination status and card, their ID card, adn their profile picture
     * @param event When the display button gets pressed
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void displayButtonPressed(ActionEvent event) throws IOException, SQLException {
        PersonQueries query = new PersonQueries();
        List<Person> list = query.getAllPersons(); //list of all people in database with their information
        for(Person person : list) {
            if (displayField.getValue().equals(person.getName()) || displayField.getValue().equals(person.getUsername())) //if name or user name matches up with search
            {
                usernameField.setText(person.getUsername()); //displays person's username
                nameField.setText(person.getName()); //displays person's name
                emailField.setText(person.getEmail()); //displays person's email
                phoneField.setText(person.getPhone()); //displays person's phone number
                if (person.getTest() == null)
                {
                    dateField.setText("N/A"); //displays N/A if user did not get tested
                }else
                {
                    dateField.setText(String.valueOf(person.getTest())); //displays date of test
                }
                if (person.isVaccine()) //sets field depending on vax status
                {
                    vaxField.setText("Vaccinated");
                }else
                {
                    vaxField.setText("Not Vaccinated");
                }
                if (!(person.getVaccineCard() == null))
                {

                    Image vaxPic = new Image(person.getVaccineCard()); //shows image of vax card
                    vaxCard.setImage(vaxPic);
                }
                if (!(person.getId() == null))
                {

                    Image idPic = new Image(person.getId()); //shows image of ID card
                    idCard.setImage(idPic);
                }
                if (!(person.getProfilePic() == null))
                {
                    Image profPic = new Image(person.getProfilePic()); //shows image of profile picture
                    profilePic.setImage(profPic);
                }
            }
        }

    }

    /**
     * When update button gets pressed, user is sent to page to update their information in database
     * @param actionEvent when update button is pressed
     * @throws IOException
     */
    @FXML
    public void updateButtonPressed(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/OrganizationUpdate.fxml"));
        AnchorPane root = load.load();

        OrganizationUpdateController organizationUpdateController = load.getController();
        OrganizationQueries org = new OrganizationQueries();
        //gets user's information to be displayed when the update menu is opened
        organizationUpdateController.setTextFields(org.getOrganizationByUserName(userName));
        Stage primaryStage = new Stage();
        Scene scene  = new Scene(root);
        primaryStage.setTitle("Update Organization Account");
        primaryStage.setScene(scene);


        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
        primaryStage.show();
    }

    /**
     * Displays the user's username to be displayed when the Organization Menu is opened
     * @param org Organization who's username is displayed
     */
    public void setUsernameField(String org)
    {
        userName = org;
    }
}

