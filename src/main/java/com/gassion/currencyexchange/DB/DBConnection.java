package com.gassion.currencyexchange.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;

public class DBConnection {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private static Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            setProperties();

            connection =  DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            return connection;
        } else {
            return connection;
        }
    }

    private static void setProperties() {
        FileInputStream fis;
        Properties properties = new Properties();

        try {
           fis = new FileInputStream("src/main/resources/config.properties");
           properties.load(fis);
        } catch (IOException e) {
           throw new RuntimeException(e);
       }

        dbUrl = properties.getProperty("db.host");
        dbUser = properties.getProperty("db.user");
        dbPassword = properties.getProperty("db.password");
    }
}
