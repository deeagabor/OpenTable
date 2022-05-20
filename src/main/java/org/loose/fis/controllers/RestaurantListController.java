package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.loose.fis.DataBaseUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RestaurantListController implements Initializable {

    @FXML
    private ListView<String> list;
    @FXML
    private Button select;


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
                    list.getItems().add(username);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        select.setOnAction(new EventHandler<ActionEvent>() {
             private Parent root;
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/restaurant.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                RestaurantController restaurantController = loader.getController();
                restaurantController.setRestaurantName(list.getSelectionModel().getSelectedItem());
                restaurantController.setClient(name);
                restaurantController.setLabel(list.getSelectionModel().getSelectedItem());

                Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(list.getSelectionModel().getSelectedItem());
                stage.show();
            }
        });
    }

    String name;
    public void setUsername(String username) {
        name=username;
    }
}
