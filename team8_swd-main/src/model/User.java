package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class User {

    private String username;

    /**
     * Name of organization
     */
    private String name;

    /**
     * Email of organization
     */
    private String email;

    /**
     * Phone number of organization
     */
    private String phone;

    /**
     * Profile Pictures
     */
    private String profilePic;

    /**
     * Password for organization account that is hashed.
     */
    private String password;

    /**
     * Constructor for User
     * @param name  name of org
     * @param email email of org
     * @param phone phone # of org
     * @param password  hashed password for user
     * @throws NoSuchAlgorithmException Exception for if hashing algorithm doesn't exist
     */
    public User(String username, String name, String email, String phone,String profilePic, String password)
            throws NoSuchAlgorithmException {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profilePic = profilePic;
    }

    /**
     * Empty constructor for organization
     */
    public User() {}

    /**
     * Algorithm for using sha-512 for hashing password.
     * Code used from https://www.baeldung.com/java-password-hashing
     * @param password  input actual password
     * @return String as hashed password
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < hashedPassword.length; i++) {
            sb.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", password='" + password + '\'';
    }
}