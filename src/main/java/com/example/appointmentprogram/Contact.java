package com.example.appointmentprogram;

/**
 * @author Abdoulaye Boundy Djikine
 * Represents a contact in the appointment program.
 */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * Constructs a new Contact object
     * @param contactID the ID of the contact
     * @param contactName the name of the contact
     * @param email the email address of the contact
     */
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Sets the ID of the contact
     * @param contactID the ID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Sets the name of the contact
     * @param contactName the name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Sets the email address of the contact
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the ID of the contact
     * @return the ID of the contact
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Returns the name of the contact
     * @return the name of the contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Returns the email address of the contact
     * @return the email address of the contact
     */
    public String getEmail() {
        return email;
    }
}
