package ru.ollyeys.todoapp.dao;

import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

//    private static final String INSERT_TASK_SQL = "insert into todos" +
//            " (id, title, description, user_id) values " + " (?, ?, ?, ?, ?); ";

    private static final String SELECT_TASK_BY_ID = "select id, title, description " +
            "from todos where id =?";


    @Override
    public void insertTask(Task task) throws SQLException {

    }

    @Override
    public Task selectTask(long taskId) {
        Task task = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);) {
            preparedStatement.setLong(1, taskId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                long user_id = rs.getLong("user_id");
                task = new Task(id, title, description, user_id);
            }
        } catch (SQLException exception) {
//            JDBCUtils.printSQLException(exception);
        }
        return task;
    }

    @Override
    public List<Task> selectAllTasks(String username) {
        return null;
    }

    @Override
    public boolean deleteTask(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTask(Task task) throws SQLException {
        return false;
    }


}
