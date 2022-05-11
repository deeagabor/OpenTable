package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.loose.fis.DataBaseUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button buttonRegister;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameTextField.getText().isEmpty() && passwordField.getText().isEmpty())
                    loginMessage.setText("Please enter your data!");
                else {
                    DataBaseUtil.LoginUser(event, usernameTextField.getText(), passwordField.getText());
                }
            }
        });

        buttonRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/chooseRole.fxml", "Choose your role!", null);
            }
        });
    }
}
