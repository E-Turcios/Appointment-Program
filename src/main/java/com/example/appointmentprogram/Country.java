package com.example.appointmentprogram;

/**
 * @author Abdoulaye Boundy Djikine
 * The Country class represents a country object.
 */
public class Country {
    private int countryID;
    private String name;
    /**
     * Constructs a country object with the given ID and name
     * @param countryID the ID of the country
     * @param name the name of the country
     */
    Country(int countryID, String name){
        this.countryID = countryID;
        this.name = name;
    }

    /**
     * Sets the ID of the country
     * @param countryID the ID of the country
     */
    public void setCountryID(int countryID) { this.countryID = countryID; }

    /**
     * Sets the name of the country
     * @param name the name of the country
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the ID of the country
     * @return the ID of the country
     */
    public int getCountryID() { return countryID; }

    /**
     * Returns the name of the country
     * @return the name of the country
     */
    public String getName() { return name; }

}
