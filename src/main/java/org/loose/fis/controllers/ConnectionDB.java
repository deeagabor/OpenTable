package org.loose.fis.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    public Connection databaseLink;

    public Connection getDBConnection()
    {
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String user = "root";
        String password = "casa2019";

        try{
            databaseLink = DriverManager.getConnection(url, user, password);
            System.out.println("Connection success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
