package ru.ollyeys.todoapp.dao;

import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.model.TaskDTO;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void insertTask(Task task) throws SQLException;

    TaskDTO selectTask(Integer todoId);

    boolean deleteTask(int id) throws SQLException;

    boolean updateTask(int id, Task task) throws SQLException;
}
