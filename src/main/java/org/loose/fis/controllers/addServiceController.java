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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.loose.fis.DataBaseUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.changeScene;


public class addServiceController implements Initializable {

    @FXML
    private Label nume, adresa, telefon;

    @FXML
    private Button edit, appointments, back, delete;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeScene(event, "/login.fxml", "LogIn!", null);
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nume.setText(null);
                adresa.setText(null);
                telefon.setText(null);

            }
        });

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editDetails.fxml"));

                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                editDetailsController editdetailsController = loader.getController();
                editdetailsController.setUpdate(true);
                editdetailsController.setTextField(nume.getText(),adresa.getText(),telefon.getText(),nameR);

                Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();



            }
        });

        appointments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DataBaseUtil.class.getResource("/appointmentsRestaurant.fxml"));
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TableViewRestaurantController tableController = loader.getController();
                tableController.setRestaurantName(nameR);

                Scene scene=new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.setTitle("MyAppointments");
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        });

    }

    String nameR;

    public void setRestaurant(String username) {
        nameR=username;
    }

    public void setLabel(String username) {
        nume.setText(username);
    }

    public void setTelefon(String retrievedTelRestaurant) {
        telefon.setText(retrievedTelRestaurant);
    }

    public void setAdresa(String retrievedAdressRestaurant) {
        adresa.setText(retrievedAdressRestaurant);
    }


}
