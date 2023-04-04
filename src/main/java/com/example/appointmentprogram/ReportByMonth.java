package com.example.appointmentprogram;

/**
 * @author Abdoulaye Boundy Djikine
 * The ReportByMonth class represents a ReportByMonth objecy
 */
public class ReportByMonth {
    private String month;
    private String type;
    private int typeTotal;

    /**
     * Constructs a new ReportByMonth object with the given month, type, and type total
     * @param month the month associated with the report
     * @param type the type of appointment associated with the report
     * @param typeTotal the total number of appointments of the given type in the given month
     */
    public ReportByMonth(String month, String type, int typeTotal){
        this.month = month;
        this.type = type;
        this.typeTotal = typeTotal;
    }

    /**
     * Sets the month associated with the report
     * @param month the month to set
     */
    public void setMonth(String month) { this.month = month; }

    /**
     * Sets the type of appointment associated with the report
     * @param type the type to set
     */
    public void setType(String type) { this.type = type; }

    /**
     * Sets the total number of appointments of the given type in the given month
     * @param typeTotal the total to set
     */
    public void setTypeTotal(int typeTotal) { this.typeTotal = typeTotal; }

    /**
     * Returns the month associated with the report
     * @return the month
     */
    public String getMonth() { return month; }

    /**
     * Returns the type of appointment associated with the report
     * @return the type
     */
    public String getType() { return type; }

    /**
     * Returns the total number of appointments of the given type in the given month
     * @return the total
     */
    public int getTypeTotal() { return typeTotal; }
}
