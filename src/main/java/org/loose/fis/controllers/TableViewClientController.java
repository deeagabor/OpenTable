package org.loose.fis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.loose.fis.model.appointmentsClient;
import org.loose.fis.model.appointmentsRestaurant;


import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class TableViewClientController implements Initializable {

    @FXML
    private TableView<appointmentsClient> table;
    @FXML
    private TableColumn<appointmentsRestaurant, String> id;
    @FXML
    private TableColumn<appointmentsRestaurant, String> restaurant;

    @FXML
    private TableColumn<appointmentsRestaurant, Date> data;
    @FXML
    private TableColumn<appointmentsRestaurant, String> ora;

    @FXML
    private TableColumn<appointmentsRestaurant, String> edit;

    appointmentsClient appointment = null;
    PreparedStatement preparedStatement = null ;

    ObservableList<appointmentsClient> AppointmentsList = FXCollections.observableArrayList();

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

                String retrievedClient = queryOutput.getString("clientName");

                if(retrievedClient.equals(numeC)){
                    AppointmentsList.add(new appointmentsClient(
                            queryOutput.getInt("idappointments"),
                            queryOutput.getString("restaurantName"),
                            queryOutput.getDate("date"),
                            queryOutput.getString("ora")));
                }


                table.setItems(AppointmentsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDate() {

        ConnectionDB connectNow = new ConnectionDB();
        Connection connectionDB = connectNow.getDBConnection();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        restaurant.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        ora.setCellValueFactory(new PropertyValueFactory<>("ora"));


        Callback<TableColumn<appointmentsRestaurant, String>, TableCell<appointmentsRestaurant, String>> cellFoctory = (TableColumn<appointmentsRestaurant, String> param) -> {

            final TableCell<appointmentsRestaurant, String> cell = new TableCell<appointmentsRestaurant, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button delete = new Button("delete");

                        delete.setStyle(
                                " -fx-cursor: hand ;"

                        );


                        delete.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                appointment = table.getSelectionModel().getSelectedItem();
                                ConnectionDB connectNow = new ConnectionDB();
                                Connection connectionDB = connectNow.getDBConnection();

                                preparedStatement = connectionDB.prepareStatement("DELETE FROM appointments  where idappointments="+appointment.getId());
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                        });

                        HBox managebtn = new HBox(delete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(delete, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        edit.setCellFactory(cellFoctory);
        table.setItems(AppointmentsList);
    }
    String numeC;
    public void setClientName(String client) {
        numeC=client;
    }
}

