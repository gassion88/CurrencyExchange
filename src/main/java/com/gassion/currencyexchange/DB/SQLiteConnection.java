package com.gassion.currencyexchange.DB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;

public class SQLiteConnection extends DBConnection{
    private static String dbUrl;
    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            setProperties();

            try {
                connection =  DriverManager.getConnection(dbUrl);
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return connection;
        }
    }

    void setProperties() {
        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dbUrl = properties.getProperty("db.url");
    }

    public static void main(String[] args) {
        Connection connection1 = new SQLiteConnection().getConnection();

        System.out.println("123");
    }
}
