package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RestaurantListController implements Initializable {

    @FXML
    private ListView<String> accountListView;
    @FXML
    private Button selectBtn;



    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        try {

            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery("select name, role from restaurant_table");


            while (queryOutput.next()) {

                String username = queryOutput.getString("name");
                String role = queryOutput.getString("role");

                if (role.equalsIgnoreCase("restaurant")) {
                    accountListView.getItems().add(username);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        selectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBaseUtil.changeScene(event, "/restaurant.fxml", accountListView.getSelectionModel().getSelectedItem(), null);
            }
        });
    }
}
