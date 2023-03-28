package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.ZoneId;
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
    void exitOnClick(ActionEvent event) {

    }

    @FXML
    void loginOnClick(ActionEvent event) {

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
