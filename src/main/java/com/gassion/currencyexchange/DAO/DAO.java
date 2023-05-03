package com.gassion.currencyexchange.DAO;

import java.util.List;

public abstract class DAO <T> {
    public abstract T get();
    public abstract List<T> getAll();
    public abstract void set(T t);
    public abstract void add(T t);
    public abstract void delete(T t);
}
