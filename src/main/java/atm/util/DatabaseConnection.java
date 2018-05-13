package atm.util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    private Properties getProperties() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("databaseconnection.properties");

        Properties dataBaseProperties = new Properties();

        // load the inputStream using the PropertiesFile
        try {
            dataBaseProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBaseProperties;
    }

    public static Connection getConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();


        Connection connection = null;
        try {
            Class.forName(databaseConnection.getProperties().getProperty("dbDriver"));
            connection = DriverManager.getConnection(databaseConnection.getProperties().getProperty("dbUrl"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }


}