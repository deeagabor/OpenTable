package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.loose.fis.DataBaseUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.loose.fis.DataBaseUtil.changeScene;

public class RestaurantController implements Initializable {
    @FXML
    private Label nameR;
    @FXML
    private ChoiceBox ora;

    @FXML
    private DatePicker calendar;
    @FXML
    private Button confirm, appointments, back;





    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ora.getItems().add("7:00");
        ora.getItems().add("8:00");
        ora.getItems().add("9:00");
        ora.getItems().add("10:00");
        ora.getItems().add("11:00");
        ora.getItems().add("12:00");
        ora.getItems().add("13:00");
        ora.getItems().add("14:00");
        ora.getItems().add("15:00");
        ora.getItems().add("16:00");
        ora.getItems().add("17:00");
        ora.getItems().add("18:00");
        ora.getItems().add("19:00");
        ora.getItems().add("20:00");
        ora.getItems().add("21:00");
        ora.getItems().add("22:00");



        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeScene(event, "/login.fxml", "LogIn!", null);
            }
        });

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate value = calendar.getValue();
                ConnectionDB connectNow = new ConnectionDB();
                Connection connectionDB = connectNow.getDBConnection();
                PreparedStatement psInsert = null;

                try {
                    Statement statement = connectionDB.createStatement();
                    ResultSet queryOutput = statement.executeQuery("SELECT * FROM appointments");

                    boolean ok = true;
                    while (queryOutput.next() && ok == true) {
                        String retrievedRestaurant = queryOutput.getString("restaurantName");
                        String retrievedOra = queryOutput.getString("ora");
                        String retrievedDate = queryOutput.getString("date");


                        if (retrievedRestaurant.equals(restaurant) && retrievedOra.equals(String.valueOf(ora.getValue())) &&
                                retrievedDate.equals(String.valueOf(calendar.getValue()))) ok = false;
                    }
                    if(ok)
                    {
                        psInsert = connectionDB.prepareStatement("INSERT INTO appointments (restaurantName,clientName,date,ora)  VALUES (?,?,?,?)");
                        psInsert.setString(1, restaurant);
                        psInsert.setString(2, client);
                        psInsert.setString(3, String.valueOf(calendar.getValue()));
                        psInsert.setString(4, String.valueOf(ora.getValue()));
                        psInsert.executeUpdate();

                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The Date is not available, please select anotherone!");
                        alert.show();
                    }

                } catch (
                        SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        appointments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DataBaseUtil.class.getResource("/appointmentsClient.fxml"));
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TableViewClientController tableController = loader.getController();
                tableController.setClientName(client);

                scene = new Scene(root);
                stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("MyAppointments");
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        });

    }

    String restaurant;
    public void setRestaurantName(String selectedItem) {
        restaurant=selectedItem;
    }

    String client;
    public void setClient(String name) {
        client=name;
    }

    public void setLabel(String selectedItem) {
        nameR.setText(selectedItem);
    }
}


