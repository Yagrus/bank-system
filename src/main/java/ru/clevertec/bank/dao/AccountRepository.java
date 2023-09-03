package ru.clevertec.bank.dao;

import ru.clevertec.bank.config.DatabaseConfig;
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

public class AccountRepository {

    private Connection connection = DatabaseConfig.getConnection();
    private final String INSERT_REQUEST = "INSERT INTO accounts (id, iban, currency, balance, date_open, user_id, bank_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final  String FIND_BY_ID_REQUEST = "SELECT * FROM accounts WHERE id = ?";
    private final  String FIND_ALL_REQUEST = "SELECT * FROM accounts";
    private final  String UPDATE_REQUEST = "UPDATE accounts SET currency=?, balance=?, date_open=?, user_id=?, bank_id=? WHERE id = ?";

    private final String UPDATE_BALANCE = "UPDATE accounts SET balance=? WHERE id = ?";
    private final  String DELETE_REQUEST = "DELETE FROM accounts WHERE id = ?";

    public void save(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_REQUEST);

            statement.setLong(1,account.getId());
            statement.setString(2, account.getIban());
            statement.setString(3,account.getCurrency());
            statement.setDouble(4,account.getBalance());
            statement.setObject(5, account.getDateOpen());
            statement.setLong(6, account.getUser().getId());
            statement.setLong(7, account.getBank().getId());
            int rows = statement.executeUpdate();

            System.out.printf("%d rows added", rows);

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Account findById(long id) {
        BankRepository bankRepository = new BankRepository();
        UserRepository userRepository = new UserRepository();
        Account account = null;

        try {
            PreparedStatement statement  = connection.prepareStatement(FIND_BY_ID_REQUEST);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()) {
                    account = Account.builder()
                            .id(resultSet.getLong("id"))
                            .iban(resultSet.getString("iban"))
                            .currency(resultSet.getString("currency"))
                            .balance(resultSet.getDouble("balance"))
                            .dateOpen(resultSet.getObject("date_open", LocalDateTime.class))
                            .user(userRepository.findById(resultSet.getLong("user_id")))
                            .bank(bankRepository.findById(resultSet.getLong("bank_id")))
                            .build();
                }
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> findAll() {
        BankRepository bankRepository = new BankRepository();
        UserRepository userRepository = new UserRepository();
        List<Account> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_REQUEST);

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(Account.builder()
                            .id(resultSet.getLong("id"))
                            .iban(resultSet.getString("iban"))
                            .currency(resultSet.getString("currency"))
                            .balance(resultSet.getDouble("balance"))
                            .dateOpen(resultSet.getObject("date_open", LocalDateTime.class))
                            .user(userRepository.findById(resultSet.getLong("user_id")))
                            .bank(bankRepository.findById(resultSet.getLong("bank_id")))
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

    public void updateBalance(Account account){
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);

            statement.setDouble(1,account.getBalance());
            statement.setLong(2,account.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST);

            statement.setString(1,account.getCurrency());
            statement.setDouble(2,account.getBalance());
            statement.setTimestamp(3, Timestamp.valueOf(account.getDateOpen()));
            statement.setLong(4, account.getUser().getId());
            statement.setLong(5, account.getBank().getId());
            statement.setLong(6,account.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Account account) {
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST);

            statement.setLong(1,account.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
