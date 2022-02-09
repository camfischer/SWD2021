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
import model.Organization;
import model.Person;
import pictures.PictureList;
import queries.OrganizationQueries;
import queries.PersonQueries;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonMenuController {
    /**
     * Text Field that allows user to search for Organization
     */
    @FXML
    private TextField searchField;
    /**
     * Search button looks through Organization Query on press
     */
    @FXML
    private Button searchButton;
    /**
     * Choice Box that displays more parameters for the user to search for such as Type and Name
     */
    @FXML
    private ChoiceBox<String> searchBox;
    /**
     * Display Field holds the values of the Organizations that match the search criteria of the user
     */
    @FXML
    private ChoiceBox<String> displayField;
    /**
     * Button that displays the profile of the Organization the user searched for 
     */
    @FXML
    private Button displayButton;
    /**
     * Field that holds matched Organization's username
     */
    @FXML
    private TextField usernameField;
    /**
     * Field that holds matched Organization's Name
     */
    @FXML
    private TextField nameField;
    /**
     * Field that holds matched Organization's email
     */
    @FXML
    private TextField emailField;
    /**
     * Field that holds matched Organization's phone number
     */
    @FXML
    private TextField phoneField;
    /**
     * Field that holds matched Organization's vaccine required status
     */
    @FXML
    private TextField vaxField;
    /**
     * Field that holds matched Organization's vaccine card image if ever needed
     */
    @FXML
    private ImageView vaxCard;
    /**
     * Field that holds matched Organization's mask required status
     */
    @FXML
    private TextField MaskRequired;
    /**
     * Image View that displays Organizations Profile picture 
     */
    @FXML
    private ImageView profilePic;
    /**
     * When Pressed it will take Individual to Update their profile
     */
    @FXML
    private Button updateButton;
    /**
     * Field that holds matched Organization's Test Required Status
     */
    @FXML
    private TextField TestRequired;

    private String userName;

    /**
     * Initialize GUI and set values
     */
    public void initialize() {
        //Person person = new Person();
        //setUsernameField(person);
        searchBox.setItems(FXCollections.observableArrayList("All", "Name", "Username" ,"Retail", "Healthcare", "Leisure", "Office", "Government"));
        searchBox.setValue("All");
        usernameField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);
        MaskRequired.setEditable(false);
        vaxField.setEditable(false);
        TestRequired.setEditable(false);
        nameField.setEditable(false);


    }

    /**
     * Takes an event, in this case searching, and populates choicebox based off of search criteria.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
       @FXML
       private void searchButtonPressed(ActionEvent event) throws IOException, SQLException {
        //Checking search critiera and adding them to choicebox for info to be displayed
        try {
            OrganizationQueries query = new OrganizationQueries();
            if (searchBox.getValue().equals("All")) {
                List<Organization> list = query.getAllOrganizations();
                List<String> nameList = new ArrayList<>();

                for(Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            } else if (searchBox.getValue().equals("Name")) {
                List<Organization> list = query.getOrganizationsByName(searchField.getText());
                List<String> nameList = new ArrayList<>();

                for(Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            }
            else if(searchBox.getValue().equals("Retail")) {
                List<Organization> list = query.getOrganizationsByType(searchBox.getValue());
                List<String> nameList = new ArrayList<>();
                for (Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            }
            else if(searchBox.getValue().equals("Healthcare")) {
                List<Organization> list = query.getOrganizationsByType(searchBox.getValue());
                List<String> nameList = new ArrayList<>();
                for (Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            }
            else if(searchBox.getValue().equals("Leisure")) {
                List<Organization> list = query.getOrganizationsByType(searchBox.getValue());
                List<String> nameList = new ArrayList<>();
                for (Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            }
            else if(searchBox.getValue().equals("Office")) {
                List<Organization> list = query.getOrganizationsByType(searchBox.getValue());
                List<String> nameList = new ArrayList<>();
                for (Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            }
            else if(searchBox.getValue().equals("Government")) {
                List<Organization> list = query.getOrganizationsByType(searchBox.getValue());
                List<String> nameList = new ArrayList<>();
                for (Organization organization : list) {
                    nameList.add(organization.getName());
                }
                displayField.setItems(FXCollections.observableArrayList(nameList));
            }
            else {
                Organization organizations = query.getOrganizationByUserName(searchBox.getValue());
                displayField.getItems().add(organizations.getUsername());
            }
        }
        catch(SQLException sqlException) {
        }
    }


    /**
     * Once search criteria is met and display button is pressed it will display the profile of the searched Organization
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void displayButtonPressed(ActionEvent event) throws IOException, SQLException {
        OrganizationQueries query = new OrganizationQueries();
        List<Organization> list = query.getAllOrganizations();
        for(Organization organization : list) {
            if (displayField.getValue().equals(organization.getName()) || displayField.getValue().equals(organization.getUsername()) || displayField.getValue().equals(organization.getOrganizationType()))
            {
                usernameField.setText(organization.getUsername());
                nameField.setText(organization.getName());
                emailField.setText(organization.getEmail());
                phoneField.setText(organization.getPhone());
                vaxField.setText(String.valueOf(organization.isVaccine()));
                MaskRequired.setText(String.valueOf(organization.isMask()));
                TestRequired.setText(String.valueOf(organization.isTest()));
                File prof = new File(PictureList.map.get(organization.getProfilePic()));
                Image profPic = new Image(String.valueOf(prof));
                profilePic.setImage(profPic);
            }
        }

    }

    /**
     * Updates current user
     * @param actionEvent
     * @throws IOException
     */
    public void updateButtonPressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("fxml/PersonUpdateMenu.fxml"));
        AnchorPane root = load.load();

        PersonQueries personQueries = new PersonQueries();
        PersonUpdateController personUpdateController = load.getController();
        personUpdateController.setTextFields(personQueries.getPersonByUserName(userName));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Update Profile Menu");
        primaryStage.setScene(scene);

        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
        primaryStage.show();

    }

    /**
     * Pass in username to move across GUI screens.
     * @param person
     */
    public void setUsernameField(String person){
        userName = person;
    }
}
