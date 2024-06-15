package ru.ollyeys.todoapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.model.TaskDTO;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaskDAOImpl implements TaskDAO {

//    private static final String INSERT_TASK_SQL = "insert into todos" +
//            " (title, username, description, target_date, is_done) values " + " (?, ?, ?, ?, ?); ";

    private static final String INSERT_TASK_SQL = "insert into todos" +
            " (title, description, user_id, targetdate, status) values " + " (?, ?, ?, ?, ?); ";

    private static final String SELECT_TASK_BY_ID = "select title, description, user_id, targetdate, status " +
            "from todos where id = ?;";

    private static final String SELECT_ALL_TASKS_USERNAME = "select title, description " +
            "from todos where username =?";

    private static final String SELECT_ALL_TASKS_USERID = "select title, description " +
            "from todos where user_id =?";

    private static final String DELETE_TASK_BY_ID = "delete from todos where id = ?;";
    private static final String UPDATE_TASK = "update todos set title = ?, description = ?, targetdate = ?, status = ? " + "where id = ?;";

    protected static final Logger LOGGER = LogManager.getLogger();



    @Override
    public void insertTask(Task task) throws SQLException {
        System.out.println(INSERT_TASK_SQL);
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
//            preparedStatement.setLong(1, task.getId());
            preparedStatement.setString(1, task.getTaskTitle());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setLong(3, task.getUser_id());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(task.getTargetDate()));
            preparedStatement.setBoolean(5, task.getTaskStatus());
            LOGGER.info(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.warn("SQL_EXCEPTION" + exception);
        }

    }

    @Override
    public TaskDTO selectTask(Integer taskId) {
        TaskDTO task = null;


        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);) {
            preparedStatement.setInt(1, taskId);
            LOGGER.info(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Integer userId = Integer.parseInt(rs.getString("user_id"));
                LocalDate targetDate = rs.getDate("targetdate").toLocalDate();
                boolean isDone = rs.getBoolean("status");
                task = new TaskDTO(taskId, title, description, userId, targetDate, isDone);
//                System.out.println(task);
            }
        } catch (SQLException exception) {
            LOGGER.warn("SQL_EXCEPTION" + exception);
        }
        return task;
    }



    @Override
    public boolean deleteTask(int id) throws SQLException {
        boolean taskDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK_BY_ID);) {
            preparedStatement.setInt(1, id);
            taskDeleted = preparedStatement.executeUpdate() > 0;
        }
        return taskDeleted;
    }


    public boolean updateTask(int id, Task task) throws SQLException {
        boolean taskUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK);) {
            preparedStatement.setString(1, task.getTaskTitle());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setInt(5, id);
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(task.getTargetDate()));
            preparedStatement.setBoolean(4, task.getTaskStatus());
            LOGGER.info(preparedStatement);
            taskUpdated = preparedStatement.executeUpdate() > 0;
        }
        return taskUpdated;
    }
}
