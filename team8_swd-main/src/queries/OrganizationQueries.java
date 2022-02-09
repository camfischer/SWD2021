package queries;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Organization;
import model.User;


public class OrganizationQueries {

    /**
     * URL of DB
     */
    private static final String URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db028";

    /**
     * Username to access DB
     */
    private static final String USERNAME = "swd_group028";

    /**
     * Password to access DB
     */
    private static final String PASSWORD = "swd_group028-xyz-21";

    /**
     * Connection to database server
     */
    private Connection connection;

    /**
     * Prepared sql statement to select all organizations in DB
     */
    private PreparedStatement selectAllOrganizations;

    /**
     * Prepared sql statement to select single organization by username
     */
    private PreparedStatement selectOrganizationByUsername;

    /**
     * Prepared sql statement to select organizations by type
     */
    private PreparedStatement selectOrganizationByType;

    /**
     * Prepared sql statement to select multiple organizations by name
     */
    private PreparedStatement selectOrganizationByName;

    /**
     * Prepared sql statement to insert new organization
     */
    private PreparedStatement insertNewOrganization;

    /**
     * Prepared sql statement to delete single organization by username
     */
    private PreparedStatement deleteOrganizationByUsername;

    /**
     * prepared sql statement to update single organization by username
     */
    private PreparedStatement updateOrganizationByUsername;

    /**
     * Class to call methods for sql queries
     */
    public OrganizationQueries() {
        try {
            connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);


            selectAllOrganizations = connection.prepareStatement(
                    "SELECT * FROM Organizations ORDER BY name");


            selectOrganizationByUsername = connection.prepareStatement(
                    "SELECT * FROM Organizations WHERE username LIKE ? " +
                            "ORDER BY name");

            selectOrganizationByType = connection.prepareStatement(
                    "Select * FROM Organizations WHERE organizationType LIKE ?" +
                            "ORDER BY name");

            selectOrganizationByName = connection.prepareStatement(
                    "Select * FROM Organizations WHERE name LIKE ?" +
                            "ORDER BY organizationType");

            // create insert that adds a new entry into the database
            insertNewOrganization = connection.prepareStatement(
                    "INSERT INTO Organizations " +
                            "(username, name, email, phone, " +
                            "profilePic, password, vaccine, mask, test, organizationType) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            deleteOrganizationByUsername = connection.prepareStatement(
                    "DELETE FROM Organizations WHERE username LIKE ?");

            updateOrganizationByUsername = connection.prepareStatement(
                    "UPDATE Organizations SET name = ?, email = ?," +
                            "phone = ?, profilePic = ?, password = ?, vaccine = ?," +
                            "mask = ?, test = ?, organizationType = ? WHERE username LIKE ?");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Executes Select query by username
     * @param username  Username of organization
     * @return  Single organization because usernames are unique
     */
    public Organization getOrganizationByUserName(String username) {
        try {
            selectOrganizationByUsername.setString(1, username); // set last name
        }
        catch (SQLException sqlException) {
            return null;
        }

        // executeQuery returns ResultSet containing matching entries
        try (ResultSet resultSet = selectOrganizationByUsername.executeQuery()) {

            resultSet.next();

            return new Organization(
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("profilePic"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("vaccine"),
                    resultSet.getBoolean("mask"),
                    resultSet.getBoolean("test"),
                    resultSet.getString("organizationType")
            );
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            return null;
        }
    }

    /**
     * Calls prepared statement to return all organizations
     * @return List of organizations
     */
    public List<Organization> getAllOrganizations() {
        // executeQuery returns ResultSet containing matching entries
        return getOrganizations(selectAllOrganizations);
    }

    /**
     * Gets organizations by type
     * @return List of organizations
     */
    public List<Organization> getOrganizationsByType(String type) throws SQLException {
        // executeQuery returns ResultSet containing matching entries
        selectOrganizationByType.setString(1, type);
        return getOrganizations(selectOrganizationByType);
    }

    /**
     * Get organizations by name
     * @return
     */
    public List<Organization> getOrganizationsByName(String name) throws SQLException {
        selectOrganizationByName.setString(1, name);
        // executeQuery returns ResultSet containing matching entries
        return getOrganizations(selectOrganizationByName);
    }

    /**
     * General method to get organizations from prepared statement
     * @param selectOrganizationByType  Prepared select statement
     * @return  List of organizations
     */
    private List<Organization> getOrganizations(PreparedStatement selectOrganizationByType) {
        try (ResultSet resultSet = selectOrganizationByType.executeQuery()) {
            List<Organization> results = new ArrayList<Organization>();

            while (resultSet.next()) {
                results.add(new Organization(
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("profilePic"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("vaccine"),
                        resultSet.getBoolean("mask"),
                        resultSet.getBoolean("test"),
                        resultSet.getString("organizationType")
                ));
            }

            return results;
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            return null;
        }
    }

    /**
     * Method to add organizations to table
     * @param username
     * @param name
     * @param email
     * @param phone
     * @param profilePic
     * @param password
     * @param vaccine
     * @param mask
     * @param test
     * @param organizationType
     * @return  Integer for success/failure
     */
    public int addOrganization(String username, String name, String email, String phone, String profilePic,
                               String password, boolean vaccine, boolean mask, boolean test,
                               String organizationType) {

        try {
            // set parameters
            insertNewOrganization.setString(1, username);
            insertNewOrganization.setString(2, name);
            insertNewOrganization.setString(3, email);
            insertNewOrganization.setString(4, phone);
            insertNewOrganization.setString(5, profilePic);
            insertNewOrganization.setString(6, User.hashPassword(password));
            insertNewOrganization.setBoolean(7, vaccine);
            insertNewOrganization.setBoolean(8, mask);
            insertNewOrganization.setBoolean(9, test);
            insertNewOrganization.setString(10, organizationType);

            return insertNewOrganization.executeUpdate();
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }

    }

    /**
     * Update organizations by username
     * @param username
     * @param name
     * @param email
     * @param phone
     * @param profilePic
     * @param password
     * @param vaccine
     * @param mask
     * @param test
     * @param organizationType
     */
    public void updateOrganization(String username, String name, String email, String phone, String profilePic,
                                   String password, int vaccine, int mask, int test,
                                   String organizationType) {
        try {
            updateOrganizationByUsername.setString(1, name);
            updateOrganizationByUsername.setString(2, email);
            updateOrganizationByUsername.setString(3, phone);
            updateOrganizationByUsername.setString(4, profilePic);
            updateOrganizationByUsername.setString(5, User.hashPassword(password));
            updateOrganizationByUsername.setInt(6,vaccine);
            updateOrganizationByUsername.setInt(7,mask);
            updateOrganizationByUsername.setInt(8, test);
            updateOrganizationByUsername.setString(9,organizationType);
            updateOrganizationByUsername.setString(10, username);

            updateOrganizationByUsername.executeUpdate();
        }
        catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Close database connection
     */
    public void close() {
        try {
            connection.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
