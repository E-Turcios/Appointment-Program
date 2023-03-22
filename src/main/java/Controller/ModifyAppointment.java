package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ModifyAppointment {

    @FXML
    private Button appointmentCancelButton;

    @FXML
    private ComboBox<?> appointmentContact;

    @FXML
    private TextField appointmentDescription;

    @FXML
    private DatePicker appointmentEndDate;

    @FXML
    private ComboBox<?> appointmentEndTime;

    @FXML
    private TextField appointmentId;

    @FXML
    private TextField appointmentLocation;

    @FXML
    private Button appointmentSaveButton;

    @FXML
    private DatePicker appointmentStartDate;

    @FXML
    private ComboBox<?> appointmentStartTime;

    @FXML
    private TextField appointmentTitle;

    @FXML
    private TextField appointmentType;

    @FXML
    private ComboBox<?> customerId;

    @FXML
    private ComboBox<?> userId;

    @FXML
    void cancelOnClick(ActionEvent event) {

    }

    @FXML
    void saveOnClick(ActionEvent event) {

    }

}
