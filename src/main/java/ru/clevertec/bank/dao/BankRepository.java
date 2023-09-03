package ru.clevertec.bank.dao;

import ru.clevertec.bank.config.DatabaseConfig;
import ru.clevertec.bank.model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankRepository {

    private Connection connection = DatabaseConfig.getConnection();
    private final String INSERT_REQUEST = "INSERT INTO banks (id, bic, name) VALUES (?, ?, ?)";
    private final  String FIND_BY_ID_REQUEST = "SELECT * FROM banks WHERE id = ?";
    private final  String FIND_ALL_REQUEST = "SELECT * FROM banks";
    private final  String UPDATE_REQUEST = "UPDATE banks SET bic=?, name=? WHERE id = ?";
    private final  String DELETE_REQUEST = "DELETE FROM banks WHERE id = ?";

    public void save(Bank bank) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST);

            preparedStatement.setLong(1,bank.getId());
            preparedStatement.setString(2,bank.getBic());
            preparedStatement.setString(3,bank.getName());

            int rows = preparedStatement.executeUpdate();

            System.out.printf("%d rows added", rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Bank findById(long id) {
        Bank bank = null;

        try {
            PreparedStatement statement  = connection.prepareStatement(FIND_BY_ID_REQUEST);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()) {
                    bank = Bank.builder()
                            .id(resultSet.getLong("id"))
                            .bic(resultSet.getString("bic"))
                            .name(resultSet.getString("name"))
                            .build();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return bank;
    }

    public List<Bank> findAll() {
        List<Bank> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_REQUEST);

            if(resultSet != null) {
                while (resultSet.next()) {
                    list.add(Bank.builder()
                            .id(resultSet.getLong("id"))
                            .bic(resultSet.getString("bic"))
                            .name(resultSet.getString("name"))
                            .build()
                    );
                }
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void update(Bank bank) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST);

            statement.setString(1, bank.getBic());
            statement.setString(2, bank.getName());
            statement.setLong(3, bank.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Bank bank) {
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST);

            statement.setLong(1,bank.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
