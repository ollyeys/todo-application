package ru.ollyeys.todoapp.dao;

import ru.ollyeys.todoapp.model.User;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "insert into users" +
                " (name, surname, username, password) values " +
                " (?, ?, ?, ?);";
        int result = 0;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
//            JDBCUtils.printSQLException(ex);
        }
        return result;

    }
}
