package ru.ollyeys.todoapp.dao;

import ru.ollyeys.todoapp.model.User;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    private static final String INSERT_USER = "insert into users" + " (name, surname, username, password) values " +
            " (?, ?, ?, ?); ";


    public int registerUser(User user) throws ClassNotFoundException, SQLException {



        int result = 0;
        Class.forName("org.postgresql.Driver");

        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        System.out.println("Registration start");


        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getUserSurname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());

        System.out.println(preparedStatement);
        result = preparedStatement.executeUpdate();



//        try (Connection connection = JDBCUtils.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
//            System.out.println(preparedStatement);
//            System.out.println(user.getName());
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getSurname());
//            preparedStatement.setString(3, user.getUsername());
//            preparedStatement.setString(4, user.getPassword());


        return result;

    }
}
