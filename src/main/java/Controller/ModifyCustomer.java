package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModifyCustomer {

    @FXML
    private TextField address;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> country;

    @FXML
    private TextField customerId;

    @FXML
    private ComboBox<?> division;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField postalCode;

    @FXML
    private Button saveButton;

    @FXML
    void cancelOnClick(ActionEvent event) {

    }

    @FXML
    void loadDivisions(ActionEvent event) {

    }

    @FXML
    void saveOnClick(ActionEvent event) {

    }

}
