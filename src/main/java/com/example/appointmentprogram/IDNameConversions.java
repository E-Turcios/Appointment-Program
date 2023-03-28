package com.example.appointmentprogram;

import javafx.collections.ObservableList;
import java.sql.SQLException;

public class IDNameConversions {

    public static String convertContactIDToName(int contactID) throws SQLException {
        String contactName = "";
        ObservableList<Contact> allContacts = FetchDB.getContactsFromDatabase();
        for (Contact c: allContacts){
            if(c.getContactID() == contactID){
                contactName = c.getContactName();
            }
        }
        return contactName;
    }

    public static int convertContactNameToID(String contactName) throws SQLException{
        int contactID = 0;
        ObservableList<Contact> allContacts = FetchDB.getContactsFromDatabase();
        for (Contact c: allContacts){
            if(c.getContactName().equals(contactName)){
                contactID = c.getContactID();
            }
        }
        return contactID;
    }


    public static String convertDivisionIDToName(int divisionID) throws SQLException{
        String divisionName = "";
        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FetchDB.getDivisionsFromDatabase();
        for(FirstLevelDivision f: allFirstLevelDivisions){
            if(f.getDivisionID() == divisionID){
                divisionName = f.getName();
            }
        }
        return divisionName;
    }

    public static int convertDivisionNameToId(String divisionName) throws SQLException{
        int divisionId = 0;
        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FetchDB.getDivisionsFromDatabase();
        for(FirstLevelDivision f: allFirstLevelDivisions){
            if(f.getName().equals(divisionName)){
                divisionId = f.getDivisionID();
            }
        }
        return divisionId;
    }
    public static int convertCountryNameToID(String countryName) throws SQLException{
        int countryId = 0;
        ObservableList<Country> allCountries = FetchDB.getCountriesFromDatabase();
        for(Country c: allCountries){
            if(c.getName().equals(countryName)){
                countryId = c.getCountryID();
            }
        }
        return countryId;
    }


}
