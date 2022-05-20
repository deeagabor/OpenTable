package org.loose.fis.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.loose.fis.DataBaseUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class editDetailsController implements Initializable {
    @FXML
    private Button save;
    @FXML
    private TextField nume;

    @FXML
    private TextField adresa;
    @FXML
    private TextField telefon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                ConnectionDB connectNow = new ConnectionDB();
                Connection connectionDB = connectNow.getDBConnection();
                PreparedStatement psInsert = null;
                PreparedStatement psInsert1 = null;


                try {
                    psInsert = connectionDB.prepareStatement("UPDATE restaurant_table set username = ?, name = ?, adresa = ?, telefon = ? where username ='"+user+"'");
                    psInsert.setString(1,user);
                    psInsert.setString(2,nume.getText());
                    psInsert.setString(3,adresa.getText());
                    psInsert.setString(4,telefon.getText());
                    psInsert.executeUpdate();



                } catch (
                        SQLException e) {
                    e.printStackTrace();
                }


                DataBaseUtil.changeScene(event, "/login.fxml","LogIn!",null);

            }

        });
    }


    boolean ok;
    public void setUpdate(boolean b) {
        ok=b;
    }

    String user;
    String aux;
    public void setTextField(String name, String adresa, String telefon, String nameR) {
        this.nume.setText(name);
        this.adresa.setText(adresa);
        this.telefon.setText(telefon);
        this.user = nameR;
        this.aux=name;
    }



}

