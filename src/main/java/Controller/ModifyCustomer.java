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

/**
 * @author Abdoulaye Boundy Djikine
 *The ModifyCustomer class is a controller for the modify customer screen of the Appointment Program application.
 *It is responsible for handling user input from the modify customer screen, such as updating a customer's information
 *and returning to the main screen. It uses the FetchDB, DBAccess, and IDNameConversions classes to access and modify
 *the appointment program's MySQL database.
 */
public class ModifyCustomer {
    /**
     * The address TextField.
     */
    @FXML
    private TextField address;

    /**
     * The cancelButton Button.
     */
    @FXML
    private Button cancelButton;

    /**
     * The country ComboBox.
     */
    @FXML
    private ComboBox<String> country;

    /**
     * The customerId TextField.
     */
    @FXML
    private TextField customerId;

    /**
     * The division ComboBox.
     */
    @FXML
    private ComboBox<String> division;

    /**
     * The name TextField.
     */
    @FXML
    private TextField name;

    /**
     * The phoneNumber TextField.
     */
    @FXML
    private TextField phoneNumber;

    /**
     * The postalCode TextField.
     */
    @FXML
    private TextField postalCode;

    /**
     * The saveButton Button.
     */
    @FXML
    private Button saveButton;

    /**
     * The static Customer object for the currently selected customer.
     */
    private static Customer select;
    /**
     * This method loads the main page when the cancel button is clicked
     * @param event The event object that triggered this method.
     * @throws IOException Thrown if the FXML file for the main screen cannot be loaded.
     */
    @FXML
    void cancelOnClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/main.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Called when the user selects a country from the "Country" combo box. This method loads the list of divisions
     * for the selected country into the "Division" combo box.
     * @param event The event object that triggered this method.
     * @throws SQLException Thrown if there is an error communicating with the database.
     */
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
    /**
     * Called when the user clicks the "Save" button. This method updates the customer information in the database with
     * the information entered by the user. If any fields are missing, an error message is displayed and the update is
     * not performed.
     * @param event The event object that triggered this method.
     * @throws SQLException Thrown if there is an error communicating with the database.
     * @throws IOException Thrown if the FXML file for the main screen cannot be loaded.
     */
    @FXML
    void saveOnClick(ActionEvent event) throws SQLException, IOException {

        if(allFieldsFilled()){
            Connection conn = DBAccess.getConnection();
            DBAccess.setPreparedStatement(conn, "UPDATE customers SET Customer_ID = ?, Customer_Name = ?, Address = ?, Phone = ?, Postal_Code = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?");
            PreparedStatement preparedStatement = DBAccess.getPreparedStatement();
            preparedStatement.setInt(1, Integer.parseInt(customerId.getText()));
            preparedStatement.setString(2, name.getText());
            preparedStatement.setString(3, address.getText());
            preparedStatement.setString(4, phoneNumber.getText());
            preparedStatement.setString(5, postalCode.getText());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(7, "admin");
            preparedStatement.setInt(8, IDNameConversions.convertDivisionNameToId(division.getValue()));
            preparedStatement.setInt(9, Integer.parseInt(customerId.getText()));
            preparedStatement.execute();

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../com/example/appointmentprogram/main.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The customer with ID: " + customerId.getText() + " was successfully updated");
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
    /**
     * cChecks if all required fields in the form are filled.
     * @return true if all fields are filled, false otherwise.
    */
     public Boolean allFieldsFilled(){
        if(!name.getText().isEmpty() && !address.getText().isEmpty() && !phoneNumber.getText().isEmpty() && country.getValue() != null && division.getValue() != null && !postalCode.getText().isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * Initializes the ModifyCustomer controller.
     * Populates the country ComboBox with a list of countries and populates the division ComboBox with the
     * appropriate divisions based on the selected country.
     * @throws SQLException if there is an error accessing the appointment program's MySQL database
     */
    public void initialize() throws SQLException{

        select = Main.getSelectedCustomer();
        ObservableList<Country> listOfCountries = FetchDB.getCountriesFromDatabase();
        ObservableList<String> countryNamesList = FXCollections.observableArrayList();
        for(Country c: listOfCountries){
            countryNamesList.add(c.getName());
        }
        country.setItems(countryNamesList);
        ObservableList<FirstLevelDivision> listOfDivisions = FetchDB.getDivisionsFromDatabase();
        ObservableList<String> USDivisionsList = FXCollections.observableArrayList();
        ObservableList<String> UKDivisionsList = FXCollections.observableArrayList();
        ObservableList<String> CanadaDivisionsList = FXCollections.observableArrayList();
        ObservableList<Integer> USDivisionIDList = FXCollections.observableArrayList();
        ObservableList<Integer> UKDivisionIDList = FXCollections.observableArrayList();
        ObservableList<Integer> CanadaDivisionIDList = FXCollections.observableArrayList();
        for(FirstLevelDivision f: listOfDivisions){
            if(f.getCountryId() == 1){
                USDivisionsList.add(f.getName());
                USDivisionIDList.add(f.getDivisionID());
            }
            else if(f.getCountryId() == 2){
                UKDivisionsList.add(f.getName());
                UKDivisionIDList.add(f.getDivisionID());
            }
            else if(f.getCountryId() == 3){
                CanadaDivisionsList.add(f.getName());
                CanadaDivisionIDList.add(f.getDivisionID());
            }
        }
        if(USDivisionIDList.contains(select.getDivisionID())){
            country.setValue("US");
            division.setItems(USDivisionsList);
        }
        else if(UKDivisionIDList.contains(select.getDivisionID())) {
            country.setValue("UK");
            division.setItems(UKDivisionsList);
        }
        else if(CanadaDivisionIDList.contains(select.getDivisionID())) {
            country.setValue("Canada");
            division.setItems(CanadaDivisionsList);
        }
        customerId.setText(String.valueOf(select.getCustomerID()));
        name.setText(select.getCustomerName());
        address.setText(select.getAddress());
        phoneNumber.setText(select.getPhone());
        division.setValue(select.getDivisionName());
        postalCode.setText(select.getPostalCode());
    }
}
