package ru.ollyeys.todoapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ollyeys.todoapp.model.User;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    private static final String INSERT_USER = "insert into users" + " (name, surname, username, password) values " +
            " (?, ?, ?, ?); ";

    protected static final Logger LOGGER = LogManager.getLogger();


    public int registerUser(User user) throws ClassNotFoundException, SQLException {



        int result = 0;
        Class.forName("org.postgresql.Driver");

        Connection connection = JDBCUtils.getConnection();
        LOGGER.info(connection);
        LOGGER.info("REGISTRATION START");


        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getUserSurname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());

        LOGGER.info(preparedStatement);
        result = preparedStatement.executeUpdate();
        return result;

    }
}
