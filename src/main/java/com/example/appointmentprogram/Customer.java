package com.example.appointmentprogram;

import java.time.LocalDateTime;
/**
 * @author Abdoulaye Boundy Djikine
 * Customer class representing a customer object
 */
public class Customer {
    int customerID;
    String customerName;
    String address;
    String postalCode;
    String phone;
    int divisionID;
    private String divisionName;
    /**
     * Constructs a new customer object with the specified ID, name, address, postal code, phone, and division ID
     * @param customerID the ID of the customer
     * @param customerName the name of the customer
     * @param address the address of the customer
     * @param postalCode the postal code of the customer
     * @param phone the phone number of the customer
     * @param divisionID the ID of the division where the customer is located
     * @param divisionName the name of the division where the customer is located
     */
    Customer(int customerID, String customerName, String address, String postalCode, String phone, int divisionID, String divisionName){
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this. postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * Sets the customer's ID.
     * @param customerID the ID of the customer
     */
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    /**
     * Sets the customer's name.
     * @param customerName the name of the customer
     */
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    /**
     * Sets the customer's address.
     * @param address the address of the customer
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Sets the customer's postal code.
     * @param postalCode the postal code of the customer
     */
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    /**
     * Sets the customer's phone number.
     * @param phone the phone number of the customer
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Sets the ID of the division where the customer is located
     * @param divisionID the ID of the division where the customer is located
     */
    public void setDivisionID(int divisionID) { this.divisionID = divisionID; }

    /**
     * Sets the name of the division where the customer is located
     * @param divisionName the name of the division where the customer is located
     */
    public void setDivisionID(String divisionName) { this.divisionName = divisionName; }

    /**
     * Returns the ID of the customer.
     * @return the ID of the customer
     */
    public int getCustomerID() { return customerID; }

    /**
     * Returns the name of the customer.
     * @return the name of the customer
     */
    public String getCustomerName() { return customerName; }

    /**
     * Returns the address of the customer.
     * @return the address of the customer
     */
    public String getAddress() { return address; }

    /**
     * Returns the postal code of the customer.
     * @return the postal code of the customer
     */
    public String getPostalCode() { return postalCode; }

    /**
     * Returns the phone number of the customer.
     * @return the phone number of the customer
     */
    public String getPhone() { return phone; }

    /**
     * Returns the ID of the division where the customer is located.
     * @return the ID of the division where the customer is located
     */
    public int getDivisionID() { return divisionID; }

    /**
     * Returns the name of the division where the customer is located.
     * @return the name of the division where the customer is located
     */
    public String getDivisionName() { return divisionName; }

}
