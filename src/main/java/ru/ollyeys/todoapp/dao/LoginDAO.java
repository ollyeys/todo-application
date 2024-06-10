package ru.ollyeys.todoapp.dao;

/* contains JDBC code validate user login and password with users table */

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




    public boolean validate(User user) throws ClassNotFoundException, SQLException {
        boolean state = false;

        Class.forName("org.postgresql.Driver");

        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        System.out.println("Validation start");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users where username = ? and password = ? ");

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();


        state = resultSet.next();
        System.out.println("Validation state:" + ' ' + state);
        return state;


//        try (Connection connection = JDBCUtils.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "select * from users where username = ? and password = ? ")) {
//            preparedStatement.setString(1, login.getUsername());
//            preparedStatement.setString(2, login.getPassword());
//            System.out.println(preparedStatement);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            state = resultSet.next();
//        } catch (SQLException ex) {
////            JDBCUtils.printSQLException(ex);
//        }
//        return state;
    }

    public List<TaskDTO> selectAllTasks(Integer userId) {
        List<TaskDTO> tasks = new ArrayList<>();

        System.out.println("select all");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
            preparedStatement.setInt(1, userId);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();



            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("targetdate").toLocalDate();
                boolean isDone = rs.getBoolean("status");
                tasks.add(new TaskDTO(id, title, description, userId, targetDate, isDone));
                System.out.println(tasks);
            }
        } catch (SQLException exception) {
            System.out.println("exception");
        }
        return tasks;
    }

}
