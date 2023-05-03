package com.gassion.currencyexchange.DAO;

import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.utils.DBUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateDAO extends DAO<ExchangeRate>{
    Connection connection = DBUtils.connection;
    @Override
    public ExchangeRate get(int id) {
        ExchangeRate exchangeRate = null;
        String query = String.format(DBUtils.SELECT , "ExchangeRates", "ID");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            exchangeRate = getExchangeRate(result).get(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exchangeRate;
    }

    @Override
    public List<ExchangeRate> getAll() {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        String query = String.format(DBUtils.SELECT_ALL , "ExchangeRates");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();

            exchangeRates = getExchangeRate(result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exchangeRates;
    }

    @Override
    public void set(ExchangeRate exchangeRate) {
        String query = String.format(DBUtils.UPDATE , "ExchangeRates", "BaseCurrencyId", "TargetCurrencyId", "Rate");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, exchangeRate.getBaseCurrencyId());
            preparedStatement.setInt(2, exchangeRate.getTargetCurrencyId());
            preparedStatement.setBigDecimal(3, exchangeRate.getRate());
            preparedStatement.setInt(4, exchangeRate.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(ExchangeRate exchangeRate) {
        String query = String.format(DBUtils.INSERT , "ExchangeRates", "BaseCurrencyId", "TargetCurrencyId", "Rate");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, exchangeRate.getBaseCurrencyId());
            preparedStatement.setInt(2, exchangeRate.getTargetCurrencyId());
            preparedStatement.setBigDecimal(3, exchangeRate.getRate());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String query = String.format(DBUtils.DELETE , "ExchangeRates");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<ExchangeRate> getExchangeRate(ResultSet result) throws SQLException {
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        while (result.next()) {
            int ID = result.getInt("ID");
            int BaseCurrencyId = result.getInt("BaseCurrencyId");
            int TargetCurrencyId = result.getInt("TargetCurrencyId");
            BigDecimal Rate = BigDecimal.valueOf(result.getInt("Rate"));

            exchangeRates.add(new ExchangeRate(ID, BaseCurrencyId, TargetCurrencyId, Rate));
        }

        return exchangeRates;
    }
}
