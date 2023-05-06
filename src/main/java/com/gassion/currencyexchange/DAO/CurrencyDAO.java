package com.gassion.currencyexchange.DAO;

import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO extends DAO<Currency>{
    Connection connection = DBUtils.connection;
    @Override
    public Currency get(String code) throws SQLException {
        List<Currency> currency = null;
        String query = String.format(DBUtils.SELECT , "Currencies", "Code");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);
            ResultSet result = preparedStatement.executeQuery();

            currency = getCurrency(result);

        }

        if (currency.size() == 0) {
            throw new SQLException();
        }

        return currency.get(0);
    }


    @Override
    public List<Currency> getAll() {
        List<Currency> currencies;
        String query = String.format(DBUtils.SELECT_ALL , "Currencies");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();

            currencies = getCurrency(result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currencies;
    }

    @Override
    public void set(Currency currency) {
        String query = String.format(DBUtils.UPDATE , "Currencies", "Code", "FullName", "Sign");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currency.getCode());
            preparedStatement.setString(2, currency.getFullName());
            preparedStatement.setString(3, currency.getSign());
            preparedStatement.setInt(4, currency.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Currency currency) throws SQLException {
        String query = String.format(DBUtils.INSERT , "Currencies", "Code", "FullName", "Sign");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currency.getCode());
            preparedStatement.setString(2, currency.getFullName());
            preparedStatement.setString(3, currency.getSign());

            preparedStatement.execute();
        }
    }

    @Override
    public void delete(int id) {
        String query = String.format(DBUtils.DELETE , "Currencies");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Currency> getCurrency(ResultSet result) throws SQLException {
        List<Currency> currencies = new ArrayList<>();

        while (result.next()) {
            int ID = result.getInt("ID");
            String Code = result.getString("Code");
            String FullName = result.getString("FullName");
            String Sign = result.getString("Sign");

            currencies.add(new Currency(ID, Code, FullName, Sign));
        }

        return currencies;
    }
}
