package ru.ollyeys.todoapp.dao;

import ru.ollyeys.todoapp.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void insertTask(Task task) throws SQLException;

    Task selectTask(long todoId);

    List<Task> selectAllTasks(String username);

    boolean deleteTask(int id) throws SQLException;

    boolean updateTask(Task task) throws SQLException;
}
