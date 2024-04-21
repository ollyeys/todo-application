package ru.ollyeys.todoapp.dao;

/* contains JDBC code validate user login and password with users table */

import ru.ollyeys.todoapp.model.Login;
import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    private static final String SELECT_ALL_TASKS = "select * from todos where user_id = ?;";

    public boolean validate(Login login) throws ClassNotFoundException, SQLException {
        boolean state = false;

        Class.forName("org.postgresql.Driver");

        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users where username = ? and password = ? ");
        preparedStatement.setString(1, login.getUsername());
        preparedStatement.setString(2, login.getPassword());
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        state = resultSet.next();
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

    public List<Task> selectAllTasks(String username) {


        List<Task> tasks = new ArrayList<>();


        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();


            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
//                String username = rs.getString("username");
                String description = rs.getString("description");
                boolean isDone = rs.getBoolean("is_done");
            }
        } catch (SQLException exception) {
            System.out.println("exception");
        }
        System.out.println(tasks);
        return tasks;
    }

}
