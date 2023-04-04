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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class AddCustomer {

    @FXML
    private TextField address;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> country;

    @FXML
    private TextField customerId;

    @FXML
    private ComboBox<String> division;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField postalCode;

    @FXML
    private Button saveButton;

    @FXML
    void cancelOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/main.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loadDivisions(ActionEvent event) throws SQLException {
        int countryId = 0;
        ObservableList<Country> listOfCountries = FetchDB.getCountriesFromDatabase();
        ObservableList<FirstLevelDivision> listOfDivisions = FetchDB.getDivisionsFromDatabase();
        ObservableList<String> USDivisionsList = FXCollections.observableArrayList();
        ObservableList<String> UKDivisionsList = FXCollections.observableArrayList();
        ObservableList<String> CanadaDivisionsList = FXCollections.observableArrayList();
        for(FirstLevelDivision f: listOfDivisions){
            if(f.getCountryId() == 1){
                USDivisionsList.add(f.getName());
            }
            else if(f.getCountryId() == 2){
                UKDivisionsList.add(f.getName());
            }
            else if(f.getCountryId() == 3){
                CanadaDivisionsList.add(f.getName());
            }
        }
        String countrySelected = country.getSelectionModel().getSelectedItem();
        for(Country c: listOfCountries) {
            if(countrySelected.equals(c.getName())) {
                countryId = IDNameConversions.convertCountryNameToID(countrySelected);
                if (countryId == 1) {
                    division.setItems(USDivisionsList);
                } else if (countryId == 2) {
                    division.setItems(UKDivisionsList);
                } else if (countryId == 3) {
                    division.setItems(CanadaDivisionsList);
                }
            }
        }
    }

    @FXML
    void saveOnClick(ActionEvent event) throws SQLException, IOException {
        if(allFieldsFilled()){
            int customerId = (int) (Math.random() *100);
            Connection conn = DBAccess.getConnection();
            DBAccess.setPreparedStatement(conn, "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Phone, Postal_Code, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement preparedStatement = DBAccess.getPreparedStatement();
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, name.getText());
            preparedStatement.setString(3, address.getText());
            preparedStatement.setString(4, phoneNumber.getText());
            preparedStatement.setString(5, postalCode.getText());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(7, "admin");
            preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(9, "admin");
            preparedStatement.setInt(10, IDNameConversions.convertDivisionNameToId(division.getValue()));
            preparedStatement.execute();

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/main.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("A customer with ID: " + customerId + " was successfully added");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("One or more fields empty!");
            alert.setContentText("Please fill out all required fields");
            alert.showAndWait();
        }

    }
    public Boolean allFieldsFilled(){
        if(!name.getText().isEmpty() && !address.getText().isEmpty() && !phoneNumber.getText().isEmpty() && country.getValue() != null && division.getValue() != null && !postalCode.getText().isEmpty()){
            return true;
        }
        return false;
    }
    public void initialize() throws SQLException {
        ObservableList<Country> listOfCountries = FetchDB.getCountriesFromDatabase();
        ObservableList<String> countryNamesList = FXCollections.observableArrayList();
        for(Country c: listOfCountries){
            countryNamesList.add(c.getName());
        }
        country.setItems(countryNamesList);
        /*
        Lambda expression to handle click event for the save button
         */
        saveButton.setOnAction(actionEvent -> {
            try {
                saveOnClick(actionEvent);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(actionEvent -> {
            try {
                cancelOnClick(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
