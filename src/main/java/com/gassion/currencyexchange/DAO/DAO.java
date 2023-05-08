package com.gassion.currencyexchange.DAO;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO <T> {
    public abstract T get(String code) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
    public abstract void set(T t);
    public abstract void add(T t) throws SQLException;
    public abstract void delete(int id);
}
