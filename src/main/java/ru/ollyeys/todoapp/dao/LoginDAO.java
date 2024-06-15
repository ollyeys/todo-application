package ru.ollyeys.todoapp.dao;

/* contains JDBC code validate user login and password with users table */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ollyeys.todoapp.model.Login;
import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.model.TaskDTO;
import ru.ollyeys.todoapp.model.User;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    private static final String SELECT_ALL_TASKS = "select * from todos where user_id = ?;";
    protected static final Logger LOGGER = LogManager.getLogger();


    public boolean validate(User user) throws ClassNotFoundException, SQLException {
        boolean state = false;


        Class.forName("org.postgresql.Driver");
        Connection connection = JDBCUtils.getConnection();

        LOGGER.info(connection);
        LOGGER.info("VALIDATION START");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users where username = ? and password = ? ");

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        LOGGER.info(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();


        state = resultSet.next();
        LOGGER.info("VALIDATION STATE: " + state);
        return state;
    }

    public List<TaskDTO> selectAllTasks(Integer userId) {
        List<TaskDTO> tasks = new ArrayList<>();

        System.out.println("select all");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
            preparedStatement.setInt(1, userId);

            LOGGER.info(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();



            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("targetdate").toLocalDate();
                boolean isDone = rs.getBoolean("status");
                tasks.add(new TaskDTO(id, title, description, userId, targetDate, isDone));
            }
        } catch (SQLException exception) {
            LOGGER.warn("SQL_EXCVEPTION" + exception);
        }
        return tasks;
    }

}
