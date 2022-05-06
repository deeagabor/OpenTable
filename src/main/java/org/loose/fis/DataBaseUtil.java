package org.loose.fis;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.loose.fis.controllers.LogInController;

import java.io.IOException;
import java.sql.*;

public class DataBaseUtil {

    /*public static void RegisterClient(ActionEvent event, String username, String password)
    {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaunrat","root", "rootpassword");
            psCheckUserExist = connection.prepareStatement("SELECT* FROM restaurant_table WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if(resultSet.isBeforeFirst())  //daca returneaza true inseamna ca exista deja userul
            {
                System.out.println("User already exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can't use this username!");
                alert.show();
            }
            else  //daca nu mai exista acest username se inregistreaza si trece la scena cu login
            {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password)  VALUES (?, ?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.executeUpdate();

                changeScenee(event,"login.fxml","Login",null);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally
        {
            if(resultSet != null)
            {
                try
                {
                    resultSet.close();
                }catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }

            if(psCheckUserExist != null)
            {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psInsert != null)
            {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    public static void LoginUser(ActionEvent event, String username, String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurnat","root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT password FROM restaurant_table WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) //daca nu e intordusa bine parola(care este in baza de date fata de cea introdusa de user) se afiseaza user not found
            {
                System.out.println("User not found in database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }
            else  //userul exista in database
            {
                while(resultSet.next())
                {
                    String retrievedPassword = resultSet.getString("password");
                    // String retrievedRole = resultSet.getString("role");   ///PENTRU ROL ATUNCI CAND FACI REGISTER????
                    if(retrievedPassword.equals(password))    //daca parola introdusa de user este aceeasi cu cea din bazade date
                        System.out.println("success");
                        /*changeScenee(event,"chooseRole.fxml","choose Role",null ); //DIN LOGIN SA MEARGA IN ROLE CHANGE*/
                    else
                    {
                        System.out.println("Password didn't match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Te provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            if(resultSet != null)
            {
                try
                {
                    resultSet.close();
                }catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

