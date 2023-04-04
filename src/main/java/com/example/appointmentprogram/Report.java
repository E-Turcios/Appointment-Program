package com.example.appointmentprogram;

/**
 * @author Abdoulaye Boundy Djikine
 * The Report class represents a report objecy
 */
public class Report {
    private int divisionTotal;
    private String divisionName;

    /**
     * Constructs a new Report instance
     * @param divisionTotal the total number of divisions
     * @param divisionName the name of the division
     */
    public Report(int divisionTotal, String divisionName){
        this.divisionTotal = divisionTotal;
        this.divisionName = divisionName;
    }

    /**
     * Sets the total number of divisions
     * @param divisionTotal the total number of divisions
     */
    public void setDivisionTotal(int divisionTotal) { this.divisionTotal = divisionTotal; }

    /**
     * Sets the name of the division
     * @param divisionName the name of the division
     */
    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    /**
     * Returns the total number of divisions
     * @return the total number of divisions
     */
    public int getDivisionTotal() { return divisionTotal; }

    /**
     * Returns the name of the division
     * @return the name of the division
     */
    public String getDivisionName() { return divisionName; }
}
