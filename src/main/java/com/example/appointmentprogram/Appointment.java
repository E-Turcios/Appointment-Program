package com.example.appointmentprogram;

import java.time.LocalDateTime;
/**
 * @author Abdoulaye Boundy Djikine
 * Appointment class representing an appointment object
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;

    /**
     * Constructor for creating an Appointment object with the specified properties
     * @param appointmentId ID of the appointment
     * @param title title of the appointment
     * @param description description of the appointment
     * @param location location of the appointment
     * @param type type of the appointment
     * @param startTime start time of the appointment
     * @param endTime end time of the appointment
     * @param customerId ID of the customer associated with the appointment
     * @param userId ID of the user associated with the appointment
     * @param contactId ID of the contact associated with the appointment
     * @param contactName name of the contact associated with the appointment
     */
    Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId, String contactName){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * Sets the ID of the appointment
     * @param appointmentId ID of the appointment
     */
    public void setAppointmentId(int appointmentId){ this.appointmentId = appointmentId; }

    /**
     * Sets the title of the appointment
     * @param title title of the appointment
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Sets the description of the appointment
     * @param description description of the appointment
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Sets the location of the appointment
     * @param location location of the appointment
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Sets the type of the appointment
     * @param type type of the appointment
     */
    public void setType(String type) { this.type = type; }

    /**
     * Sets the start time of the appointment
     * @param startTime start time of the appointment
     */
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    /**
     * Sets the end time of the appointment
     * @param endTime end time of the appointment
     */
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    /**
     * Sets the ID of the customer associated with the appointment
     * @param customerId ID of the customer associated with the appointment
     */
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    /**
     * Sets the ID of the user associated with the appointment
     * @param userId ID of the user associated with the appointment
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Sets the ID of the contact associated with the appointment
     * @param contactId ID of the contact associated with the appointment
     */
    public void setContactId(int contactId) { this.contactId = contactId; }

    /**
     * Sets the name of the contact associated with the appointment
     * @param contactName ID of the contact associated with the appointment
     */
    public void setContactName(String contactName) { this.contactName = contactName; }

    /**
     * Gets the appointment ID
     * @return the appointment ID
     */
    public int getAppointmentId(){ return appointmentId; }

    /**
     * Gets the appointment title
     * @return the appointment title
     */
    public String getTitle(){return title; }

    /**
     * Gets the appointment description
     * @return the appointment description
     */
    public String getDescription(){ return description; }

    /**
     * Gets the appointment location
     * @return the appointment location
     */
    public String getLocation(){ return location; }

    /**
     * Gets the appointment type
     * @return the appointment type
     */
    public String getType(){return type; }

    /**
     * Gets the appointment start time
     * @return the appointment start time
     */
    public LocalDateTime getStartTime(){ return startTime; }

    /**
     * Gets the appointment end time
     * @return the appointment end time
     */
    public LocalDateTime getEndTime(){ return endTime; }

    /**
     * Gets the customer ID associated with the appointment
     * @return the customer ID
     */
    public int getCustomerId(){ return customerId; }

    /**
     * Gets the user ID associated with the appointment
     * @return the user ID
     */
    public int getUserId(){ return userId; }

    /**
     * Gets the contact ID associated with the appointment
     * @return the contact ID
     */
    public int getContactId(){return contactId; }

    /**
     * Gets the contact name associated with the appointment
     * @return the contact name
     */
    public String getContactName(){return contactName; }
}
