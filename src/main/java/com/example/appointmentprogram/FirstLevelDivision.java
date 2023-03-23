package com.example.appointmentprogram;

/**
 * @author Abdoulaye Boundy Djikine
 * The FirstLevelDivision class represents a first level division in a country.
 */
public class FirstLevelDivision {
    private int divisionID;
    private String name;
    private int countryId;

    /**
     * Constructs a new FirstLevelDivision object with the specified ID, name, and country ID
     * @param divisionID the division ID
     * @param name the division name
     * @param countryId the country ID
     */
    FirstLevelDivision(int divisionID, String name, int countryId){
        this.divisionID = divisionID;
        this.name = name;
        this.countryId = countryId;
    }

    /**
     * Sets the division ID
     * @param divisionID the new division ID
     */
    public void setDivisionID(int divisionID) { this.divisionID = divisionID; }

    /**
     * Sets the division name
     * @param name the new division name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the country ID
     * @param countryId the new country ID
     */
    public void setCountryId(int countryId) { this.countryId = countryId; }

    /**
     * Gets the division ID
     * @return the division ID
     */
    public int getDivisionID() { return divisionID; }

    /**
     * Gets the division name
     * @return the division name
     */
    public String getName() { return name; }

    /**
     * Gets the country ID
     * @return the country ID
     */
    public int getCountryId() { return countryId; }
}

