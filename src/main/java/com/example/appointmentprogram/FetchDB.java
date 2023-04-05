package com.example.appointmentprogram;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDateTime;
/**
 * @author Abdoulaye Boundy DJikine
 * The FetchDB class retrieves data from the database and returns it as ObservableLists of relevant model objects.
 * This class has methods to retrieve appointments, contacts, countries, customers, divisions, and users from the database.
 * The retrieved data is returned as ObservableLists of the relevant model objects.
 */
public class FetchDB {
    /**
     * Retrieves all appointments from the database and returns them as an ObservableList of Appointment objects.
     * @return ObservableList of Appointment objects containing all appointments from the database.
     * @throws SQLException if there are errors with the database connections or queries.
     */
    public static ObservableList<Appointment> getAppointmentsFromDatabase() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        Connection connection = DBAccess.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, contacts.Contact_Name FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int id = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String type = resultSet.getString("Type");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("End").toLocalDateTime();
            int customerId = resultSet.getInt("Customer_ID");
            int userId = resultSet.getInt("User_ID");
            int contactId = resultSet.getInt("Contact_ID");
            String contactName = IDNameConversions.convertContactIDToName(contactId);
            Appointment currentAppointment = new Appointment(id, title, description, location, type, start, end, customerId, userId, contactId, contactName);
            allAppointments.add(currentAppointment);
        }
        return allAppointments;
    }

    /**
     * Retrieves all contacts from the database and returns them as an ObservableList of Contact objects.
     * @return ObservableList of Contact objects containing all contacts from the database.
     * @throws SQLException if there are errors with the database connections or queries.
     */
    public static ObservableList<Contact> getContactsFromDatabase() throws SQLException{
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM contacts");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("Contact_ID");
            String name = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");
            Contact currentContact = new Contact(id, name, email);
            allContacts.add(currentContact);
        }
        return allContacts;
    }

    /**
     * Retrieves all countries from the database and returns them as an ObservableList of Country objects.
     * @return ObservableList of Country objects containing all countries from the database.
     * @throws SQLException if there are errors with the database connections or queries.
     */
    public static ObservableList<Country> getCountriesFromDatabase() throws SQLException{
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM countries");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("Country_ID");
            String name = resultSet.getString("Country");
            Country currentCountry = new Country(id, name);
            allCountries.add(currentCountry);
        }
        return allCountries;
    }

    /**
     * Retrieves all customers from the database and returns them as an ObservableList of Customer objects.
     * @return ObservableList of Customer objects containing all customers from the database.
     * @throws SQLException if there are errors with the database connections or queries.
     */
    public static ObservableList<Customer> getCustomersFromDatabase() throws SQLException{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM customers");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("Customer_ID");
            String name = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            int divisionId = resultSet.getInt("Division_ID");
            String divisionName = IDNameConversions.convertDivisionIDToName(divisionId);
            Customer currentCustomer = new Customer(id, name, address, postalCode, phone, divisionId, divisionName);
            allCustomers.add(currentCustomer);
        }
        return allCustomers;
    }

    /**
     * Retrieves all first level divisions from the database and returns them as an ObservableList of FirstLevelDivision objects.
     * @return ObservableList of FirstLevelDivision objects containing all first level divisions from the database.
     * @throws SQLException if there are errors with the database connections or queries.
     */
    public static ObservableList<FirstLevelDivision> getDivisionsFromDatabase() throws SQLException{
        ObservableList<FirstLevelDivision> allFirstLevelDivisions = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM first_level_divisions");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("Division_ID");
            String name = resultSet.getString("Division");
            int countryId = resultSet.getInt("Country_ID");
            FirstLevelDivision currentDivision = new FirstLevelDivision(id, name, countryId);
            allFirstLevelDivisions.add(currentDivision);
        }
        return allFirstLevelDivisions;
    }

    /**
     * Retrieves all users from the database and returns them as an ObservableList of User objects.
     * @return ObservableList of User objects containing all users from the database.
     * @throws SQLException if there are errors with the database connections or queries.
     */
    public static ObservableList<User> getUsersFromDatabase() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("User_ID");
            String username = resultSet.getString("User_Name");
            String password = resultSet.getString("Password");
            User currentUser = new User(id, username, password);
            allUsers.add(currentUser);
        }
        return allUsers;
    }

}
