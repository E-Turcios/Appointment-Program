package com.example.appointmentprogram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Locale;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Appointment Program");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Path path = Paths.get("src/main/java/Languages");
        DBAccess.startConnection();
        System.out.println(path.toAbsolutePath());
        launch();
        DBAccess.closeConnection();
    }
}