package com.gassion.currencyexchange.DB;

import com.gassion.currencyexchange.utils.DBUtils;

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
                Class.forName("org.sqlite.JDBC");
                connection =  DriverManager.getConnection(dbUrl);
                return connection;
            } catch (SQLException | ClassNotFoundException  e) {
                throw new RuntimeException(e);
            }


        } else {
            return connection;
        }
    }

    static void setProperties() {
        FileInputStream fis;
        Properties properties = new Properties();
        String path = DBUtils.getPathResources("properties/config.properties");

        try {
            fis = new FileInputStream(path);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        dbUrl = "jdbc:sqlite:" + DBUtils.getPathResources(properties.getProperty("db.url"));
    }
}
