package model;

import java.security.NoSuchAlgorithmException;

/**
 * Class that represents organization as an entity in
 * a relational database.
 */
public class Organization extends User {

    /**
     * Status if organization requires being vaccinated or not
     */
    private boolean vaccine;

    /**
     * Status if organization requires being masked or not
     */
    private boolean mask;

    /**
     * Status if organization requires being tested or not
     */
    private boolean test;

    /**
     * Type of organization as defined by enum
     */
    private String organizationType;


    /**
     * Constructor
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
     * @throws NoSuchAlgorithmException
     */
    public Organization(String username, String name, String email, String phone, String profilePic, String password,
                        boolean vaccine, boolean mask, boolean test, String organizationType)
            throws NoSuchAlgorithmException {
        super(username, name, email, phone, profilePic, password);
        this.vaccine = vaccine;
        this.mask = mask;
        this.test = test;
        this.organizationType = organizationType;
    }

    /**
     * Empty constructor for organization
     */
    public Organization() {}

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isMask() {
        return mask;
    }

    public void setMask(boolean mask) {
        this.mask = mask;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", vaccine=" + vaccine +
                ", mask=" + mask +
                ", test=" + test +
                ", organizationType=" + organizationType +
                '}';
    }
}