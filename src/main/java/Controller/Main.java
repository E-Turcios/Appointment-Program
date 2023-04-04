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
import java.util.Optional;

public class Main {

    @FXML
    private RadioButton allAppointments;

    @FXML
    private Button appointmentAdd;

    @FXML
    private TableColumn<?, ?> appointmentContact;

    @FXML
    private TableColumn<?, ?> appointmentCustomerId;

    @FXML
    private Button appointmentDelete;

    @FXML
    private TableColumn<?, ?> appointmentDescription;

    @FXML
    private TableColumn<?, ?> appointmentEnd;

    @FXML
    private TableColumn<?, ?> appointmentId;

    @FXML
    private TableColumn<?, ?> appointmentLocation;

    @FXML
    private TableColumn<?, ?> appointmentStart;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<?, ?> appointmentTitle;

    @FXML
    private TableColumn<?, ?> appointmentType;

    @FXML
    private Button appointmentUpdate;

    @FXML
    private TableColumn<?, ?> appointmentUserId;

    @FXML
    private RadioButton currentMonth;

    @FXML
    private RadioButton currentWeek;

    @FXML
    private Button customerAdd;

    @FXML
    private TableColumn<?, ?> customerAddress;

    @FXML
    private Button customerDelete;

    @FXML
    private TableColumn<?, ?> customerDivision;

    @FXML
    private TableColumn<?, ?> customerId;

    @FXML
    private TableColumn<?, ?> customerName;

    @FXML
    private TableColumn<?, ?> customerPhone;

    @FXML
    private TableColumn<?, ?> customerPostalCode;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private Button customerUpdate;

    @FXML
    private Button logoutButton;

    @FXML
    private Button reportsButton;

    @FXML
    private ToggleGroup toggleGroup;
    private static Appointment selectedAppointment;
    private static Customer selectedCustomer;

    @FXML
    void addAppointmentOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/add-appointment.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addCustomerOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/add-customer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void allAppointmentsSelected(ActionEvent event) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        appointmentTable.setItems(listOfAppointments);
    }

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

    @FXML
    void logoutOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/login.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void reportsOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/report.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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

    public static Appointment getSelectedAppointment(){
        return selectedAppointment;
    }

    public static Customer getSelectedCustomer(){
        return selectedCustomer;
    }

    public void alert(int input){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch(input){
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("No Appointment Selected!");
                alert.setContentText("Please select an appointment");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("No Customer Selected!");
                alert.setContentText("Please select a customer");
                alert.showAndWait();
                break;
        }
    }

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
        customerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivision.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerTable.setItems(listOfCustomers);
    }

}
