package org.loose.fis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
*/
import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {


    //private static Stage stage;

    public void start(Stage primaryStage) throws Exception {
        //stage = primaryStage;
        // primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /*Method for changing scenes
    public void changeScene(String fxml) throws IOException{

        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

     */




    public static void main(String[] args) {
        launch(args);
    }
}

