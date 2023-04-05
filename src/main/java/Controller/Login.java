package Controller;

import com.example.appointmentprogram.Appointment;
import com.example.appointmentprogram.FetchDB;
import com.example.appointmentprogram.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Abdoulaye Boundy Djikne
 * The Login class represents the controller for the login page of the appointment program.
 * It contains methods to handle the login button and exit button click events.
 * It also contains methods to check for upcoming appointments.
 */
public class Login {
    /**
     * JavaFX button for exiting the login screen.
     */
    @FXML
    private Button exitButton;
    /**
     * JavaFX button for submitting the login credentials.
     */
    @FXML
    private Button loginButton;
    /**
     * JavaFX label for displaying the login prompt.
     */
    @FXML
    private Label loginLabel;
    /**
     * JavaFX label for displaying the password prompt.
     */
    @FXML
    private Label passwordLabel;
    /**
     * JavaFX text field for entering the password.
     */
    @FXML
    private PasswordField passwordText;
    /**
     * JavaFX label for displaying the timezone information.
     */
    @FXML
    private Label timeZoneDisplay;
    /**
     * JavaFX label for displaying the timezone prompt.
     */
    @FXML
    private Label timeZoneLabel;
    /**
     * JavaFX label for displaying the username prompt.
     */
    @FXML
    private Label usernameLabel;
    /**
     * JavaFX text field for entering the username.
     */
    @FXML
    private TextField usernameText;

    /**
     * This method handles the login button click event. It first gets a list of users from the database, and then
     * uses lambda functions to add the usernames and passwords to separate observable lists. It then checks if the
     * username and password entered by the user match any of the usernames and passwords in the database. If there is
     * a match, the program switches to the main page. If there is no match, an error message is displayed.
     * The Lambda function is used as substitute for the for loops
     * @param event An ActionEvent representing the login button click event.
     * @throws SQLException if there is a problem accessing the database.
     * @throws IOException if there is a problem accessing the login_activity.txt file.
     */
    @FXML
    void loginOnClick(ActionEvent event) throws SQLException, IOException {
        ObservableList<User> listOfUsers = FetchDB.getUsersFromDatabase();
        ObservableList<String> usernameList = FXCollections.observableArrayList();
        ObservableList<String> passwordList = FXCollections.observableArrayList();

        //lambda functions
        listOfUsers.forEach(user -> usernameList.add(user.getUsername()));
        listOfUsers.forEach(user -> passwordList.add(user.getPassword()));

        //ResourceBundle localLanguage = ResourceBundle.getBundle("Language/language", Locale.getDefault());
        FileWriter txtLoggerFile = new FileWriter("login_activity.txt", true);

        if(usernameList.contains(usernameText.getText()) && passwordList.contains(passwordText.getText())){
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/main.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            txtLoggerFile.write("The user with username: " + usernameText.getText() + " successfully logged in at " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME) + " on " + LocalDate.now() + "\n\n");
            int appointmentId = 0;
            LocalDateTime upcomingStart = null;

            if(appointmentSoon()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The appointment with ID: " + appointmentUpcoming(appointmentId) + " starts within the next 15 minutes! This appointment starts at " + upcomingStart(upcomingStart));
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("There are no upcoming appointments");
                alert.showAndWait();
            }
        }
//        else if (!usernameList.contains(usernameText.getText()) || !passwordList.contains(passwordText.getText()) || usernameText.getText().isEmpty() || passwordText.getText().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle(language.getString("Error"));
//            a.setContentText(localLanguage.getString("IncorrectLogin"));
//            a.showAndWait();
//            txtLoggerFile.write("The user with username: " + usernameText.getText() + " failed to login at " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME) + " on " + LocalDate.now() + "\n\n");
//        }
        txtLoggerFile.close();
    }

    /**
     * This method checks for upcoming appointments within the next 15 minutes. It retrieves a list of appointments
     * from the database, and then iterates through each appointment to check if its start time is within the next 15 minutes.
     * @return A Boolean value indicating whether there are any upcoming appointments.
     * @throws SQLException if there is a problem accessing the database.
     */
    public Boolean appointmentSoon() throws SQLException {
        boolean upcoming = false;
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeAhead = LocalDateTime.now().plusMinutes(15);
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        for(Appointment a: listOfAppointments){
            if(a.getStartTime().isEqual(timeAhead) || (a.getStartTime().isAfter(timeNow) && a.getStartTime().isBefore(timeAhead))){
                upcoming = true;
            }
        }
        return upcoming;
    }

    /**
     * This method gets the ID of the upcoming appointment. It retrieves a list of appointments from the database, and then
     * iterates through each appointment to check if it is upcoming. If an upcoming appointment is found, its ID is returned.
     * @param appointmentId An integer representing the ID of the upcoming appointment.
     * @return An integer representing the ID of the upcoming appointment.
     * @throws SQLException if there is a problem accessing the database.
     */
    public int appointmentUpcoming(int appointmentId) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        for(Appointment a: listOfAppointments){
            if(appointmentSoon()){
                appointmentId = a.getAppointmentId();
            }
        }
        return appointmentId;
    }

    /**
     * This method gets the start time of the upcoming appointment. It retrieves a list of appointments from the database,
     * and then iterates through each appointment to check if it is upcoming. If an upcoming appointment is found, its start
     * time is returned.
     * @param start A LocalDateTime object representing the start time of the upcoming appointment.
     * @return A LocalDateTime object representing the start time of the upcoming appointment.
     * @throws SQLException if there is a problem accessing the database.
     */
    public LocalDateTime upcomingStart(LocalDateTime start) throws SQLException{
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        for(Appointment a: listOfAppointments){
            if(appointmentSoon()){
                start = a.getStartTime();
            }
        }
        return start;
    }
    /**
     * Exits the program when the exit button is clicked.
     * @param event the action event that triggered this method
     */
    @FXML
    void exitOnClick(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets the time zone display to the system's default time zone.
     * @throws Exception if an error occurs while initializing the controller
     */
    public void initialize() throws Exception{
        try{
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);
            ResourceBundle localLanguage = ResourceBundle.getBundle("language", Locale.getDefault());
            loginLabel.setText(localLanguage.getString("LoginLabel"));
            usernameLabel.setText(localLanguage.getString("UsernameLabel"));
            passwordLabel.setText(localLanguage.getString("PasswordLabel"));
            timeZoneLabel.setText(localLanguage.getString("TimeZoneLabel"));
            loginButton.setText(localLanguage.getString("LoginLabel"));
            exitButton.setText(localLanguage.getString("ExitButton"));
            timeZoneDisplay.setText(String.valueOf(ZoneId.systemDefault()));
        }
        catch(MissingResourceException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
