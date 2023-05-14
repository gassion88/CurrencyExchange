package com.gassion.currencyexchange.utils;

import com.gassion.currencyexchange.DB.SQLiteConnection;

import java.sql.Connection;

public class DBUtils {
    public static Connection connection = SQLiteConnection.getConnection();
    public static final String SELECT = "SELECT * FROM %s WHERE %s = ?;";
    public static final String SELECT_EXCHANGE_RATE = """
            Select * FROM ExchangeRates
                join Currencies as D on D.ID = ExchangeRates.BaseCurrencyId
                join Currencies as C on C.ID = ExchangeRates.TargetCurrencyId
            WHERE  D.Code = substr(?, 1, 3) and
                   C.Code = substr(?, 4, 3);""";
    public static final String SELECT_ALL = "SELECT * FROM %s;";
    public static final String INSERT = "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);";
    public static final String UPDATE = "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE ID = ?;";
    public static final String DELETE = "DELETE FROM %s WHERE ID = ?;";

    public static String getPathResources(String resources) {
        return SQLiteConnection.class.getClassLoader().getResource(resources).getFile();
    }
}
