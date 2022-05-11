package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseRole implements Initializable {

    @FXML
    private Button client;

    @FXML
    private Button restaurant;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/registerClient.fxml", "Client Registration ", null);
            }
        });

        restaurant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/registrationRestaurant.fxml", "Restaurant Registration", null);
            }
        });
    }
}

