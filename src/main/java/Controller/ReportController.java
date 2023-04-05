package Controller;

import com.example.appointmentprogram.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
/**
 * @author Abdoulaye Boundy Djikine
 *This class serves as the controller for the Report page of the Appointment Program application.
 */

public class ReportController {

    /**
     * The table view to display the report of appointments by month and type.
     */
    @FXML
    private TableView<ReportByMonth> appointmentByMonthTable;

    /**
     * The table column to display the customer ID of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentCustomerId;

    /**
     * The table column to display the description of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentDescription;

    /**
     * The table column to display the end time of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentEnd;

    /**
     * The table column to display the ID of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentId;

    /**
     * The table column to display the location of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentLocation;

    /**
     * The table column to display the month of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentMonth;

    /**
     * The table column to display the start time of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentStart;

    /**
     * The table column to display the title of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentTitle;

    /**
     * The table column to display the type of the appointment.
     */
    @FXML
    private TableColumn<?, ?> appointmentType;

    /**
     * The table column to display the total count of appointment types.
     */
    @FXML
    private TableColumn<?, ?> appointmentTypeColumn;

    /**
     * The button to navigate back to the main menu.
     */
    @FXML
    private Button backButton;

    /**
     * The combo box to select a contact.
     */
    @FXML
    private ComboBox<String> contactName;

    /**
     * The table view to display the appointment schedule for a contact.
     */
    @FXML
    private TableView<Appointment> contactScheduleTable;

    /**
     * The table view to display the report of customers by division.
     */
    @FXML
    private TableView<Report> customerByDivisionTable;

    /**
     * The table column to display the name of the division.
     */
    @FXML
    private TableColumn<?, ?> divisionName;

    /**
     * The table column to display the total count of customers in the division.
     */
    @FXML
    private TableColumn<?, ?> divisionTotal;

    /**
     * The button to logout of the application.
     */
    @FXML
    private Button logoutButton;

    /**
     * The table column to display the total count of appointment types.
     */
    @FXML
    private TableColumn<?, ?> typeTotal;

/**
 * This method navigates back to the main menu when the Back button is clicked.
 *
 * @param event The ActionEvent object generated by the button click
 * @throws IOException
 */

 @FXML
    void backButtonOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/main.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void contactScheduleDropDown(ActionEvent event) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        ObservableList<Appointment> appointmentsFiltered = FXCollections.observableArrayList();
        String contactSelected = contactName.getSelectionModel().getSelectedItem();

        for(Appointment a: listOfAppointments){
            if(contactSelected.equals(a.getContactName())){
                appointmentsFiltered.add(a);
            }
        }
        contactScheduleTable.setItems(appointmentsFiltered);
    }
    /**
     * This method handles the action of the logout button being clicked. It loads the login.fxml file and sets the scene to it.
     * @param event The event that triggers the method, in this case an ActionEvent from the button being clicked.
     * @throws IOException If there is an issue loading the login.fxml file.
     */
    @FXML
    void logoutOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/LOGIN.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method generates a custom report by getting data from the database and creating an ObservableList of Reports with the data.
     * @return An ObservableList of Reports.
     * @throws SQLException If there is an issue with the SQL query.
     */
    public static ObservableList<Report> generateCustomReport() throws SQLException {

        ObservableList <Report> customReport = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        String sql1 = "SELECT first_level_divisions.Division, COUNT(*) AS divisionCount FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID WHERE first_level_divisions.Division_ID = customers.Division_ID GROUP BY first_level_divisions.Division_ID ORDER BY COUNT(*) DESC";
        DBAccess.setPreparedStatement(conn, sql1);
        PreparedStatement preparedStatement = DBAccess.getPreparedStatement();
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            String divisionName = resultSet.getString("Division");
            int divisionTotal = resultSet.getInt("divisionCount");
            Report report = new Report(divisionTotal, divisionName);
            customReport.add(report);
        }
        return customReport;
    }
    /**
     * This method generates a report based on the number of appointments by month and type.
     * @return an ObservableList of ReportByMonth objects containing the number of appointments by month and type.
     * @throws SQLException if there is an error executing the SQL query.
     */
    public static ObservableList<ReportByMonth> generateAppointmentByMonthAndType() throws SQLException{
        ObservableList<ReportByMonth> reportByMonths = FXCollections.observableArrayList();
        Connection conn = DBAccess.getConnection();
        String sql = "SELECT MONTHNAME(Start) AS month, \n" +
                "appointments.Type, COUNT(appointments.Type) AS typeCount \n" +
                "FROM appointments \n" +
                "GROUP BY MONTHNAME(Start), appointments.Type \n" +
                "ORDER BY month DESC";
        DBAccess.setPreparedStatement(conn, sql);
        PreparedStatement preparedStatement = DBAccess.getPreparedStatement();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String monthName = resultSet.getString("month");
            String typeName = resultSet.getString("Type");
            int typeTotal = resultSet.getInt("typeCount");
            ReportByMonth reportByMonth = new ReportByMonth(monthName, typeName, typeTotal);
            reportByMonths.add(reportByMonth);
        }
        return reportByMonths;
    }
    /**
     * This method filters appointments by the selected contact and updates the table accordingly.
     * @throws SQLException if there is an error executing the SQL query.
     */
    public void contactScheduleDropDown() throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        ObservableList<Appointment> appointmentsFiltered = FXCollections.observableArrayList();
        String contactSelected = contactName.getSelectionModel().getSelectedItem();
        for(Appointment a: listOfAppointments){
            if(contactSelected.equals(a.getContactName())){
                appointmentsFiltered.add(a);
            }
        }
        contactScheduleTable.setItems(appointmentsFiltered);
    }
    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     * It loads the data from the database and populates the table views with appointments and reports.
     * @throws SQLException if an error occurs while retrieving data from the database
     */
    public void initialize () throws SQLException {
        ObservableList<Contact> listOfContacts = FetchDB.getContactsFromDatabase();
        ObservableList<String> contactNamesList = FXCollections.observableArrayList();
        for(Contact c: listOfContacts){
            contactNamesList.add(c.getContactName());
        }
        contactName.setItems(contactNamesList);
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeTotal.setCellValueFactory(new PropertyValueFactory<>("typeTotal"));
        divisionName.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        divisionTotal.setCellValueFactory(new PropertyValueFactory<>("divisionTotal"));
        appointmentByMonthTable.setItems(generateAppointmentByMonthAndType());
        customerByDivisionTable.setItems(generateCustomReport());
    }
}
