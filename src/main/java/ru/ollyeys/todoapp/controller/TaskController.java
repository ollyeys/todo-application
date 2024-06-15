package ru.ollyeys.todoapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.ollyeys.todoapp.dao.LoginDAO;
import ru.ollyeys.todoapp.dao.TaskDAO;
import ru.ollyeys.todoapp.dao.TaskDAOImpl;
import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.model.TaskDTO;
import ru.ollyeys.todoapp.model.User;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@WebServlet("/")
public class TaskController extends HttpServlet {

    private static final long serialId = 1L;
    private TaskDAO taskDAO;
    private TaskDTO taskDTO;
    private LoginDAO loginDAO;

    public void init() {
        taskDAO = new TaskDAOImpl();
        loginDAO = new LoginDAO();
        taskDTO = new TaskDTO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        request.setAttribute("action", action);
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTask(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteTask(request, response);
                    break;
                case "/update":
                    updateTask(request, response);
                    break;
                case "/list":
                    listTask(request, response);
                    break;
//                default:
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//                    dispatcher.forward(request, response);
//                    break;
            }
        } catch (SQLException exception) {
            throw new ServletException(exception);
        }
    }



    private void listTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println(userId);
        List <TaskDTO> listTask = loginDAO.selectAllTasks(userId);
        request.setAttribute("listTask", listTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-new.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        TaskDTO existingTask = taskDAO.selectTask(id);

        request.setAttribute("existingTask", existingTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-edit.jsp");
        dispatcher.forward(request, response);

    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {

        System.out.println("INSERT");

        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
//        System.out.println(targetDate);
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
//        System.out.println(isDone);
        Integer userId = (Integer) session.getAttribute("userId");
        Task insertTask = new Task (title, description, userId, targetDate, isDone);
        taskDAO.insertTask(insertTask);
        response.sendRedirect("list");

    }


    private void updateTask(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");


        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));

        Task updateTask = new Task (title, description, userId, targetDate, isDone);
        taskDAO.updateTask(id, updateTask);
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        taskDAO.deleteTask(id);
        response.sendRedirect("list");
    }

}
