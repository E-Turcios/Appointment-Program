package Controller;

import com.example.appointmentprogram.Appointment;
import com.example.appointmentprogram.Customer;
import com.example.appointmentprogram.DBAccess;
import com.example.appointmentprogram.FetchDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Abdoulaye Boundy Djikine
 * The Main class is the controller for the main view of the appointment program. It handles all user
 * interactions with the appointment and customer tables, as well as the creation of new appointments and customers.
 */
public class Main {
    /**
     * RadioButton representing all appointments in the appointment table
     */
    @FXML
    private RadioButton allAppointments;

    /**
     * Button for adding new appointments to the appointment table
     */

    @FXML
    private Button appointmentAdd;
    /**
     * TableColumn representing the contact associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentContact;
    /**
     * TableColumn representing the customer ID associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentCustomerId;
    /**
     * Button for deleting appointments from the appointment table
     */
    @FXML
    private Button appointmentDelete;
    /**
     * TableColumn representing the description associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentDescription;
    /**
     * TableColumn representing the end time associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentEnd;
    /**
     * TableColumn representing the ID associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentId;
    /**
     * TableColumn representing the location associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentLocation;
    /**
     * TableColumn representing the start time associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentStart;
    /**
     * TableView containing appointments
     */
    @FXML
    private TableView<Appointment> appointmentTable;
    /**
     * TableColumn representing the title associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentTitle;
    /**
     * TableColumn representing the type associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentType;
    /**
     * Button for updating existing appointments in the appointment table
     */
    @FXML
    private Button appointmentUpdate;
    /**
     * TableColumn representing the user ID associated with an appointment in the appointment table
     */
    @FXML
    private TableColumn<?, ?> appointmentUserId;
    /**
     * RadioButton representing appointments in the current month in the appointment table
     */
    @FXML
    private RadioButton currentMonth;
    /**
     * RadioButton representing appointments in the current week in the appointment table
     */
    @FXML
    private RadioButton currentWeek;
    /**
     * Button for adding new customers to the customer table
     */
    @FXML
    private Button customerAdd;
    /**
     * TableColumn representing the address associated with a customer in the customer table
     */
    @FXML
    private TableColumn<?, ?> customerAddress;
    /**
     * Button for deleting customers from the customer table
     */
    @FXML
    private Button customerDelete;

    /**
     * TableColumn representing the division associated with a customer in the customer table
     */
    @FXML
    private TableColumn<?, ?> customerDivision;
    /**
     * TableColumn representing the ID associated with a customer in the customer table
     */
    @FXML
    private TableColumn<?, ?> customerId;
    /**
     * TableColumn representing the name associated with a customer in the customer table
     */
    @FXML
    private TableColumn<?, ?> customerName;
    /**
     * TableColumn representing the phone number associated with a customer in the customer table
     */
    @FXML
    private TableColumn<?, ?> customerPhone;
    /**

     TableColumn representing the postal code associated with a customer in the customer table
     */
    @FXML
    private TableColumn<?, ?> customerPostalCode;
    /**
     * TableView containing customers
     */
    @FXML
    private TableView<Customer> customerTable;
    /**
     * Button for updating existing customers in the customer table
     */
    @FXML
    private Button customerUpdate;
    /**
     * Button for logging out of the application
     */
    @FXML
    private Button logoutButton;
    /**
     * Button for generating reports
     */
    @FXML
    private Button reportsButton;
    /**
     * ToggleGroup for the radio buttons controlling the appointment table view
     */
    @FXML
    private ToggleGroup toggleGroup;
    /**
     * The currently selected appointment in the appointment table
     */
    private static Appointment selectedAppointment;
    /**
     * The currently selected customer in the customer table
     */
    private static Customer selectedCustomer;
    /**
     * Handles the "Add Appointment" button click by displaying the "Add Appointment" view.
     * @param event the button click event
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    void addAppointmentOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/add-appointment.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the "Add Customer" button click by displaying the "Add Customer" view.
     * @param event the button click event
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    void addCustomerOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/add-customer.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method retrieves all appointments from the database and sets them in the appointmentTable view when "All Appointments" button is selected.
     * @param event An ActionEvent object representing the event of the "All Appointments" button being clicked.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    void allAppointmentsSelected(ActionEvent event) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        appointmentTable.setItems(listOfAppointments);
    }
    /**
     * This method retrieves all appointments from the database that are scheduled in the current month and sets them in the appointmentTable view when "Current Month" button is selected.
     * @param event An ActionEvent object representing the event of the "Current Month" button being clicked.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    void currentMonthSelected(ActionEvent event) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        ObservableList<Appointment> currentMonthAppointments = FXCollections.observableArrayList();
        LocalDateTime timeNow = LocalDateTime.now();
        for(Appointment a: listOfAppointments){
            if(a.getStartTime().getYear() == timeNow.getYear() && (a.getStartTime().getMonth().equals(timeNow.getMonth()) || a.getEndTime().getMonth().equals(timeNow.getMonth()))){
                currentMonthAppointments.add(a);
            }
            appointmentTable.setItems(currentMonthAppointments);
        }
    }

    /**
     * This method retrieves all appointments from the database that are scheduled in the current week and sets them in the appointmentTable view when "Current Week" button is selected.
     * @param event An ActionEvent object representing the event of the "Current Week" button being clicked.
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    void currentWeekSelected(ActionEvent event) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        ObservableList<Appointment> currentWeekAppointments = FXCollections.observableArrayList();
        LocalDate currentDate = LocalDate.now();
        LocalDate weekStart = currentDate;
        LocalDate weekEnd = currentDate;
        if(currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            weekEnd = currentDate.plusDays(6);
        }
        else if(currentDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            weekStart = currentDate.minusDays(1);
            weekEnd = currentDate.plusDays(5);
        }
        else if(currentDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)){
            weekStart = currentDate.minusDays(2);
            weekEnd = currentDate.plusDays(4);
        }
        else if(currentDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
            weekStart = currentDate.minusDays(3);
            weekEnd = currentDate.plusDays(3);
        }
        else if(currentDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)){
            weekStart = currentDate.minusDays(4);
            weekEnd = currentDate.plusDays(2);
        }
        else if(currentDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
            weekStart = currentDate.minusDays(5);
            weekEnd = currentDate.plusDays(1);
        }
        else if(currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            weekStart = currentDate.minusDays(6);
        }

        LocalDate finalWeekStart = weekStart;
        LocalDate finalWeekEnd = weekEnd;
        for(Appointment appointment: listOfAppointments){
            LocalDate startDate = appointment.getStartTime().toLocalDate();
            LocalDate endDate = appointment.getEndTime().toLocalDate();
            if ((appointment.getStartTime().getYear() == LocalDateTime.now().getYear() || appointment.getEndTime().getYear() == LocalDateTime.now().getYear()) && (startDate.isEqual(finalWeekStart) || startDate.isEqual(finalWeekEnd) || endDate.isEqual(finalWeekStart) || endDate.isEqual(finalWeekEnd) || (endDate.isAfter(finalWeekStart) && endDate.isBefore(finalWeekEnd)) || (startDate.isAfter(finalWeekStart) && startDate.isBefore(finalWeekEnd)))) {
                currentWeekAppointments.add(appointment);
            }
            appointmentTable.setItems(currentWeekAppointments);
        }

    }
    /**
     * Method called when the delete button for the appointment table is clicked.
     * Deletes the selected appointment from the database and removes it from the table view.
     * @param event the event that triggered the method call
     * @throws SQLException if there is an error accessing the database
     */
    @FXML
    void deleteAppointmentOnClick(ActionEvent event) throws SQLException {
        Appointment select = appointmentTable.getSelectionModel().getSelectedItem();
        if(select == null){
            alert(1);
        }
        else{
            Connection conn = DBAccess.getConnection();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure that you want to delete the selected appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                DBAccess.setPreparedStatement(conn, "DELETE FROM appointments WHERE Appointment_ID=?");
                PreparedStatement preparedStatement = DBAccess.getPreparedStatement();
                preparedStatement.setInt(1, select.getAppointmentId());
                preparedStatement.execute();
                ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
                appointmentTable.setItems(listOfAppointments);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Information");
                a.setContentText("The appointment with ID: " + select.getAppointmentId() + " and type " + select.getType() + " has been successfully deleted.");
                a.showAndWait();
            }
        }

    }
    /**
     * Method called when the delete button for the customer table is clicked.
     * Deletes the selected customer and all their appointments from the database and removes them from the table view.
     * @param event the event that triggered the method call
     * @throws SQLException if there is an error accessing the database
     */
    @FXML
    void deleteCustomerOnClick(ActionEvent event) throws SQLException {
        Customer select = customerTable.getSelectionModel().getSelectedItem();
        if(select == null){
            alert(2);
        }
        else{
            Connection conn = DBAccess.getConnection();
            ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure that you want to delete the selected customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                for(Appointment a: listOfAppointments){
                    if(select.getCustomerID() == a.getCustomerId()){
                        DBAccess.setPreparedStatement(conn, "DELETE FROM appointments WHERE Customer_ID = ?");
                        PreparedStatement appPrepared = DBAccess.getPreparedStatement();
                        appPrepared.setInt(1, a.getCustomerId());
                        appPrepared.execute();
                    }
                }
                DBAccess.setPreparedStatement(conn, "DELETE FROM customers WHERE Customer_ID=?");
                PreparedStatement preparedStatement = DBAccess.getPreparedStatement();
                preparedStatement.setInt(1, select.getCustomerID());
                preparedStatement.execute();
                ObservableList<Customer> listOfCustomers = FetchDB.getCustomersFromDatabase();
                ObservableList<Appointment> newlistOfAppointments = FetchDB.getAppointmentsFromDatabase();
                customerTable.setItems(listOfCustomers);
                appointmentTable.setItems(newlistOfAppointments);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Information");
                a.setContentText("The customer with ID: " + select.getCustomerID() + " along with all associated appointments has been successfully deleted.");
                a.showAndWait();
            }
        }
    }
    /**
    * Method called when the logout button is clicked.
    * Returns the user to the login screen.
    * @param event the event that triggered the method call
    * @throws IOException if there is
    */
    @FXML
    void logoutOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/login.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method navigates to the report page.
     * @param event The ActionEvent triggered by the user clicking the reports button
     * @throws IOException If there is an error loading the report page
     */
    @FXML
    void reportsOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/report.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method sets the selected appointment and navigates to the modify appointment page.
     * @param event The ActionEvent triggered by the user clicking the update appointment button
     * @throws IOException If there is an error loading the modify appointment page
     */
    @FXML
    void updateAppointmentOnClick(ActionEvent event) throws IOException {
        selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if(selectedAppointment == null){
            alert(1);
        }

        else {
            Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/modify-appointment.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * This method sets the selected customer and navigates to the modify customer page.
     * @param event The ActionEvent triggered by the user clicking the update customer button
     * @throws IOException If there is an error loading the modify customer page
     */
    @FXML
    void updateCustomerOnClick(ActionEvent event) throws IOException {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer == null){
            alert(2);
        }
        else {
            Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/modify-customer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * Returns the selected appointment object.
     * @return The selected Appointment object.
     */
    public static Appointment getSelectedAppointment(){
        return selectedAppointment;
    }
    /**
     * Returns the selected customer object.
     * @return The selected Customer object.
     */
    public static Customer getSelectedCustomer(){
        return selectedCustomer;
    }

    /**
     * This method displays an error alert message.
     * @param input The input code that determines the type of alert message to be displayed
     */
    public void alert(int input){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (input) {
            case 1 -> {
                alert.setTitle("Error");
                alert.setHeaderText("No Appointment Selected!");
                alert.setContentText("Please select an appointment");
                alert.showAndWait();
            }
            case 2 -> {
                alert.setTitle("Error");
                alert.setHeaderText("No Customer Selected!");
                alert.setContentText("Please select a customer");
                alert.showAndWait();
            }
        }
    }
    /**
     * This method initializes the appointment table and customer table.
     * @throws SQLException If there is an error fetching appointments or customers from the database
     */
    public void initialize() throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        ObservableList<Customer> listOfCustomers = FetchDB.getCustomersFromDatabase();
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        appointmentUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentTable.setItems(listOfAppointments);
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerTable.setItems(listOfCustomers);
    }

}
