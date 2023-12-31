package ru.clevertec.bank.dao;

import ru.clevertec.bank.config.DatabaseConfig;
import ru.clevertec.bank.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository{

    private Connection connection = DatabaseConfig.getConnection();
    private final String INSERT_REQUEST = "INSERT INTO transactions (id, price, type, date, comment, account_id)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private final  String FIND_BY_ID_REQUEST = "SELECT * FROM transactions WHERE id = ?";
    private final  String FIND_ALL_REQUEST = "SELECT * FROM transactions";
    private final  String UPDATE_REQUEST = "UPDATE transactions SET price=?, type=?, date=?, comment=?, account_id=? WHERE id = ?";
    private final  String DELETE_REQUEST = "DELETE FROM transactions WHERE id = ?";

    public void save(Transaction transaction) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_REQUEST);

            statement.setLong(1,transaction.getId());
            statement.setDouble(2,transaction.getPrice());
            statement.setString(3,transaction.getType());
            statement.setObject(4,transaction.getDate());
            statement.setString(5,transaction.getComment());
            statement.setLong(6,transaction.getAccount().getId());
            int rows = statement.executeUpdate();

            System.out.printf("%d rows added", rows);

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Transaction findById(long id) {
        AccountRepository repository = new AccountRepository();
        Transaction transaction = null;

        try {
            PreparedStatement statement  = connection.prepareStatement(FIND_BY_ID_REQUEST);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()) {
                    transaction = Transaction.builder()
                            .id(resultSet.getLong("id"))
                            .price(resultSet.getDouble("price"))
                            .type(resultSet.getString("type"))
                            .date(resultSet.getObject("date", LocalDateTime.class))
                            .comment(resultSet.getString("comment"))
                            .account(repository.findById(resultSet.getLong("account_id")))
                            .build();
                }
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public List<Transaction> findAll() {
        AccountRepository repository = new AccountRepository();
        List<Transaction> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_REQUEST);

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(Transaction.builder()
                            .id(resultSet.getLong("id"))
                            .price(resultSet.getDouble("price"))
                            .type(resultSet.getString("type"))
                            .date(resultSet.getObject("date", LocalDateTime.class))
                            .comment(resultSet.getString("comment"))
                            .account(repository.findById(resultSet.getLong("account_id")))
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

    public void update(Transaction transaction) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST);

            statement.setDouble(1,transaction.getPrice());
            statement.setString(2,transaction.getType());
            statement.setObject(3,transaction.getDate());
            statement.setString(4,transaction.getComment());
            statement.setLong(5,transaction.getAccount().getId());
            statement.setLong(6,transaction.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Transaction transaction) {
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST);

            statement.setLong(1,transaction.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
