package com.gassion.currencyexchange.DB;

import java.sql.Connection;

public  abstract class DBConnection {
    public static Connection getConnection(){
        return null;
    }
}
