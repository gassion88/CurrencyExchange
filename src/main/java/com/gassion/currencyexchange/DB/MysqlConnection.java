package com.gassion.currencyexchange.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

public class MysqlConnection {
    private static String dbUrl;
    private static String dbName;
    private static String dbPassword;
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            //setProperties();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
                return connection;
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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
            fis = new FileInputStream("src/main/resources/properties/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dbUrl = properties.getProperty("db.url");
    }
}
