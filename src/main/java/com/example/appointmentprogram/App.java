package com.example.appointmentprogram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * @author Abdoulaye Boundy Djikine
 * The App class is the main class of the Appointment Program. It extends the javafx.application.Application class and
 * provides the main entry point for the program. It starts the program by loading the login.fxml file and showing the login screen to the user.
 */
public class App extends Application {

    /**
     * The start method loads the login.fxml file and shows the login screen to the user.
     * @param stage the primary stage for this application.
     * @throws IOException if an I/O error occurs during the loading of the fxml file.
     */
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Appointment Program");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The main method starts the database connection and launches the application.
     * @param args the command line arguments.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     * @throws SQLException if there is an error in accessing the database.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Path path = Paths.get("src/main/java/Languages");
        DBAccess.startConnection();
        System.out.println(path.toAbsolutePath());
        launch();
        DBAccess.closeConnection();
    }
}