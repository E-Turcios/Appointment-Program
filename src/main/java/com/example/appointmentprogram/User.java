package com.example.appointmentprogram;

/**
 * @author Abdoulaye Boundy Djikine
 * Appointment class representing a user object
 */
public class User {
    private int userID;
    private String userName;
    private String password;

    /**
     * Constructs a new User object with the specified user ID, username, and password
     * @param userID the user ID
     * @param userName the username
     * @param password the password
     */
    User(int userID, String userName, String password){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Sets the user ID of this User object
     * @param userID the new user ID
     */
    public void setUserID(int userID) { this.userID = userID; }

    /**
     * Sets the username of this User object
     * @param userName the new username
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * Sets the password of this User object
     * @param password the new password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Returns the user ID of this User object
     * @return the user ID
     */
    public int getUserID() { return userID; }

    /**
     * Returns the username of this User object
     * @return the username
     */
    public String getUsername() { return userName; }

    /**
     * Returns the password of this User object
     * @return the password
     */
    public String getPassword() { return password; }
}
