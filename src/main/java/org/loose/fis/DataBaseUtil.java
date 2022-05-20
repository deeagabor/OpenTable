package org.loose.fis;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.loose.fis.controllers.RestaurantListController;
import org.loose.fis.controllers.addServiceController;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;



public class DataBaseUtil {


    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DataBaseUtil.class.getResource(fxmlFile));
                root = loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else   // username == null => will move from one scene to another
        {
            try {
                root = FXMLLoader.load(DataBaseUtil.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


    public static void RegisterClient(ActionEvent event, String username, String password, String name, String telefon, String role) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "rootpassword");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM restaurant_table WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst())  //if this username already exists
            {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exist!");
                alert.show();
            } else  //if this username does not exist, register and go to the scene with login
            {
                psInsert = connection.prepareStatement("INSERT INTO restaurant_table (username,password,name,telefon,role)  VALUES (?,?,?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, name);
                psInsert.setString(4, telefon);
                psInsert.setString(5, role);
                psInsert.executeUpdate();

                changeScene(event, "/login.fxml", "Login", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void RegisterRestaurant(ActionEvent event, String username, String password, String name, String telefon, String role, String adresa) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "rootpassword");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM restaurant_table WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User already exist!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO restaurant_table (username, password,name,telefon,role,adresa)  VALUES (?,?,?,?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, name);
                psInsert.setString(4, telefon);
                psInsert.setString(5, role);
                psInsert.setString(6, adresa);
                psInsert.executeUpdate();

                changeScene(event, "/login.fxml", "Login", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void LoginUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT password, role, name, telefon, adresa FROM restaurant_table WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) //if this username does not exist in the database
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Incorrect username!");
                alert.show();
            } else  //if this username exists in the database then it is checked if the password entered is the same as the one in the database
            {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(encodePassword(username,password))) {
                        String retrievedRole = resultSet.getString("role");
                        if (retrievedRole.equalsIgnoreCase("restaurant")) {
                            String retrievedTelRestaurant = resultSet.getString("telefon");
                            String retrievedAdressRestaurant = resultSet.getString("adresa");
                            String retrievedNameRestaurant = resultSet.getString("name");

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(DataBaseUtil.class.getResource("/addService.fxml"));
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            addServiceController addController = loader.getController();
                            addController.setRestaurant(username);
                            addController.setLabel(retrievedNameRestaurant);
                            addController.setTelefon(retrievedTelRestaurant);
                            addController.setAdresa(retrievedAdressRestaurant);


                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle(retrievedNameRestaurant);
                            stage.show();
                        }
                        else if (retrievedRole.equalsIgnoreCase("client"))
                        {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(DataBaseUtil.class.getResource("/restaurantList.fxml"));
                            try {
                                root=loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            RestaurantListController restaurantListController = loader.getController();
                            restaurantListController.setUsername(username);

                            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Choose Restaurant!");
                            stage.show();
                        }

                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Incorrect password");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}

