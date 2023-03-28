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
import java.util.ResourceBundle;

public class Login {

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label timeZoneDisplay;

    @FXML
    private Label timeZoneLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameText;
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
            Parent parent = FXMLLoader.load(getClass().getResource("../com/example/appointmentprogram/main.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

//            txtLoggerFile.write("The user with username: " + usernameText.getText() + " successfully logged in at " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME) + " on " + LocalDate.now() + "\n\n");
//            int appointmentId = 0;
//            LocalDateTime upcomingStart = null;
//
//            if(appointmentSoon()){
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("The appointment with ID: " + appointmentUpcoming(appointmentId) + " starts within the next 15 minutes! This appointment starts at " + upcomingStart(upcomingStart));
//                alert.showAndWait();
//            }
//            else{
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("There are no upcoming appointments");
//                alert.showAndWait();
//            }
        }
//        else if (!usernameList.contains(usernameText.getText()) || !passwordList.contains(passwordText.getText()) || usernameText.getText().isEmpty() || passwordText.getText().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle(localLanguage.getString("Error"));
//            a.setContentText(localLanguage.getString("IncorrectLogin"));
//            a.showAndWait();
//            txtLoggerFile.write("The user with username: " + usernameText.getText() + " failed to login at " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME) + " on " + LocalDate.now() + "\n\n");
//        }
//        txtLoggerFile.close();
    }

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

    public int appointmentUpcoming(int appointmentId) throws SQLException {
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        for(Appointment a: listOfAppointments){
            if(appointmentSoon()){
                appointmentId = a.getAppointmentId();
            }
        }
        return appointmentId;
    }

    public LocalDateTime upcomingStart(LocalDateTime start) throws SQLException{
        ObservableList<Appointment> listOfAppointments = FetchDB.getAppointmentsFromDatabase();
        for(Appointment a: listOfAppointments){
            if(appointmentSoon()){
                start = a.getStartTime();
            }
        }
        return start;
    }

    @FXML
    void exitOnClick(ActionEvent event) {
        System.exit(0);
    }
    public void initialize() throws Exception{
        try{
//            Locale locale = Locale.getDefault();
//            Locale.setDefault(locale);
//            ResourceBundle localLanguage = ResourceBundle.getBundle("Language/language", Locale.getDefault());
//            loginLabel.setText(localLanguage.getString("LoginLabel"));
//            usernameLabel.setText(localLanguage.getString("UsernameLabel"));
//            passwordLabel.setText(localLanguage.getString("PasswordLabel"));
//            timeZoneLabel.setText(localLanguage.getString("TimeZoneLabel"));
//            loginButton.setText(localLanguage.getString("LoginLabel"));
//            exitButton.setText(localLanguage.getString("ExitButton"));
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
