package ru.clevertec.bank.dao;

import ru.clevertec.bank.config.DatabaseConfig;
import ru.clevertec.bank.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository{

    private Connection connection = DatabaseConfig.getConnection();
    private final String INSERT_REQUEST = "INSERT INTO users (id, last_name, first_name, middle_name, bank_id)" +
            " VALUES (?, ?, ?, ?, ?)";
    private final  String FIND_BY_ID_REQUEST = "SELECT * FROM users WHERE id = ";
    private final  String FIND_ALL_REQUEST = "SELECT * FROM users";
    private final  String UPDATE_REQUEST = "UPDATE users SET last_name=?, first_name=?, middle_name=?, bank_id=? WHERE id = ?";
    private final  String DELETE_REQUEST = "DELETE FROM users WHERE id = ?";

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST);

            preparedStatement.setLong(1,user.getId());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getMiddleName());
            preparedStatement.setLong(5,user.getBank().getId());

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User findById(long id) {
        BankRepository repository = new BankRepository();
        User user = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_BY_ID_REQUEST + id);
            statement.close();

            if(resultSet != null){
                user =  User.builder()
                        .id(resultSet.getLong("id"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .middleName(resultSet.getString("middle_name"))
                        .bank(repository.findById(resultSet.getLong("bank_id")))
                        .build();
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAll() {
        BankRepository repository = new BankRepository();
        List<User> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_REQUEST);
            statement.close();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(User.builder()
                            .id(resultSet.getLong("id"))
                            .lastName(resultSet.getString("last_name"))
                            .firstName(resultSet.getString("first_name"))
                            .middleName(resultSet.getString("middle_name"))
                            .bank(repository.findById(resultSet.getLong("bank_id")))
                            .build()
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST);

            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setLong(4, user.getBank().getId());
            preparedStatement.setLong(5, user.getId());

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(User user) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST);

            preparedStatement.setLong(1,user.getId());

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
