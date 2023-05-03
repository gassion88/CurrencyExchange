package com.gassion.currencyexchange.DB;

import java.sql.Connection;

public  abstract class DBConnection {
    public abstract Connection getConnection();
    abstract void setProperties();
}
