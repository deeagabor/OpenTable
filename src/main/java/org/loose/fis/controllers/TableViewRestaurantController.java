package org.loose.fis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.loose.fis.model.appointmentsRestaurant;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TableViewRestaurantController implements Initializable {

    @FXML
    private TableView<appointmentsRestaurant> table;
    @FXML
    private TableColumn<appointmentsRestaurant, String> id;
    @FXML
    private TableColumn<appointmentsRestaurant, String> client;
    @FXML
    private TableColumn<appointmentsRestaurant, Date> data;
    @FXML
    private TableColumn<appointmentsRestaurant, String> ora;


    appointmentsRestaurant appointment = null;
    PreparedStatement preparedStatement = null ;


    ObservableList<appointmentsRestaurant> AppointmentsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDate();

    }



    @FXML
    private void refreshTable() {
        AppointmentsList.clear();
        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();


        try {

            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery("SELECT * FROM appointments");


            while (queryOutput.next()) {


                String retrievedRestaurant = queryOutput.getString("restaurantName");

                if (retrievedRestaurant.equals(restaurantN)) {
                    AppointmentsList.add(new appointmentsRestaurant(
                            queryOutput.getInt("idappointments"),
                            queryOutput.getString("clientName"),
                            queryOutput.getDate("date"),
                            queryOutput.getString("ora")));
                }
                table.setItems(AppointmentsList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDate() {

        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        refreshTable();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        ora.setCellValueFactory(new PropertyValueFactory<>("ora"));

        table.setItems(AppointmentsList);
    }

    String restaurantN;
    public void setRestaurantName(String nameR) {
        restaurantN = nameR;
    }
}
