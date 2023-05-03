package com.gassion.currencyexchange.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;

public class SQLiteConnection extends DBConnection{
    private static String dbUrl;
    private static Connection connection;

    public static Connection getConnection() {
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

    static void setProperties() {
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
}