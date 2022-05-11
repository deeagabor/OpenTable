package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.encodePassword;

public class RegistrationRestaurantController implements Initializable {

    @FXML
    private TextField restaurant_name_registration;

    @FXML
    private TextField restaurant_username_registration;

    @FXML
    private PasswordField restaurant_password_registration;

    @FXML
    private TextField restaurant_phone_registration;

    @FXML
    private TextField restaurant_adress_registration;

    @FXML
    private Button restaurant_register;

    @FXML
    private Button SignInButton;

    @FXML
    private CheckBox admincheck;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admincheck.setSelected(true);
        restaurant_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!restaurant_name_registration.getText().trim().isEmpty() && !restaurant_username_registration.getText().trim().isEmpty() && !restaurant_phone_registration.getText().trim().isEmpty() && !restaurant_adress_registration.getText().trim().isEmpty() && !restaurant_password_registration.getText().isEmpty())
                    DataBaseUtil.RegisterRestaurant(event,restaurant_username_registration.getText(),encodePassword(restaurant_username_registration.getText(),restaurant_password_registration.getText()), restaurant_name_registration.getText(), restaurant_phone_registration.getText(), admincheck.getText(),restaurant_adress_registration.getText());

                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all information!");
                    alert.show();
                }
            }
        });

        SignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event,"/login.fxml","Login",null);
            }
        });
    }
}
