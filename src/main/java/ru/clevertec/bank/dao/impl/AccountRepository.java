package ru.clevertec.bank.dao.impl;

import ru.clevertec.bank.config.DatabaseConfig;
import ru.clevertec.bank.dao.Repository;
import ru.clevertec.bank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {

    private Connection connection = DatabaseConfig.getConnection();
    private final String INSERT_REQUEST = "INSERT INTO accounts (id, currency, balance, date_open, user_id, bank_id)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private final  String FIND_BY_ID_REQUEST = "SELECT * FROM accounts WHERE id = ";
    private final  String FIND_ALL_REQUEST = "SELECT * FROM accounts";
    private final  String UPDATE_REQUEST = "UPDATE accounts SET currency=?, balance=?, date_open=?, user_id=?, bank_id=? WHERE id = ?";
    private final  String DELETE_REQUEST = "DELETE FROM accounts WHERE id = ?";

    @Override
    public void save(Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST);

            preparedStatement.setLong(1,account.getId());
            preparedStatement.setString(2,account.getCurrency());
            preparedStatement.setDouble(3,account.getBalance());
            preparedStatement.setObject(4, account.getDateOpen());
            preparedStatement.setLong(5, account.getUser().getId());
            preparedStatement.setLong(6, account.getBank().getId());

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account findById(long id) {
        BankRepository bankRepository = new BankRepository();
        UserRepository userRepository = new UserRepository();
        Account account = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_REQUEST + id);
            statement.close();

            if(resultSet != null){
                account =  Account.builder()
                        .id(resultSet.getLong("id"))
                        .currency(resultSet.getString("currency"))
                        .balance(resultSet.getDouble("balance"))
                        .dateOpen(resultSet.getObject("date_open", LocalDateTime.class))
                        .user(userRepository.findById(resultSet.getLong("user_id")))
                        .bank(bankRepository.findById(resultSet.getLong("bank_id")))
                        .build();
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        BankRepository bankRepository = new BankRepository();
        UserRepository userRepository = new UserRepository();
        List<Account> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_REQUEST);
            statement.close();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(Account.builder()
                            .id(resultSet.getLong("id"))
                            .currency(resultSet.getString("currency"))
                            .balance(resultSet.getDouble("balance"))
                            .dateOpen(resultSet.getObject("date_open", LocalDateTime.class))
                            .user(userRepository.findById(resultSet.getLong("user_id")))
                            .bank(bankRepository.findById(resultSet.getLong("bank_id")))
                            .build()
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void update(Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST);

            preparedStatement.setString(1,account.getCurrency());
            preparedStatement.setDouble(2,account.getBalance());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(account.getDateOpen()));
            preparedStatement.setLong(4, account.getUser().getId());
            preparedStatement.setLong(5, account.getBank().getId());
            preparedStatement.setLong(6,account.getId());

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Account account) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST);

            preparedStatement.setLong(1,account.getId());

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
