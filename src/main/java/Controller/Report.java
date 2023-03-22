package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Report {

    @FXML
    private TableView<?> appointmentByMonthTable;

    @FXML
    private TableColumn<?, ?> appointmentCustomerId;

    @FXML
    private TableColumn<?, ?> appointmentDescription;

    @FXML
    private TableColumn<?, ?> appointmentEnd;

    @FXML
    private TableColumn<?, ?> appointmentId;

    @FXML
    private TableColumn<?, ?> appointmentLocation;

    @FXML
    private TableColumn<?, ?> appointmentMonth;

    @FXML
    private TableColumn<?, ?> appointmentStart;

    @FXML
    private TableColumn<?, ?> appointmentTitle;

    @FXML
    private TableColumn<?, ?> appointmentType;

    @FXML
    private TableColumn<?, ?> appointmentTypeColumn;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> contactName;

    @FXML
    private TableView<?> contactScheduleTable;

    @FXML
    private TableView<?> customerByDivisionTable;

    @FXML
    private TableColumn<?, ?> divisionName;

    @FXML
    private TableColumn<?, ?> divisionTotal;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<?, ?> typeTotal;

    @FXML
    void backButtonOnClick(ActionEvent event) {

    }

    @FXML
    void contactScheduleDropDown(ActionEvent event) {

    }

    @FXML
    void logoutOnClick(ActionEvent event) {

    }

}
