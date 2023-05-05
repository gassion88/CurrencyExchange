package com.gassion.currencyexchange.utils;

import com.gassion.currencyexchange.DB.MysqlConnection;
import com.gassion.currencyexchange.DB.SQLiteConnection;

import java.sql.Connection;
import java.util.Objects;

public class DBUtils {
    public static Connection connection = SQLiteConnection.getConnection();
    public static final String SELECT = "SELECT * FROM %s WHERE %s = ?;";
    public static final String SELECT_ALL = "SELECT * FROM %s;";
    public static final String INSERT = "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);";
    public static final String UPDATE = "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE ID = ?;";
    public static final String DELETE = "DELETE FROM %s WHERE ID = ?;";

    public static String getPathResources(String resources) {
        return SQLiteConnection.class.getClassLoader().getResource(resources).getFile();
    }
}
