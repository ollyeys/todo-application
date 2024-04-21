package ru.ollyeys.todoapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.ollyeys.todoapp.dao.TaskDAO;
import ru.ollyeys.todoapp.dao.TaskDAOImpl;
import ru.ollyeys.todoapp.model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class TaskController extends HttpServlet {

    private static final long serialId = 1L;
    private TaskDAO taskDAO;

    public void init() {
        taskDAO = new TaskDAOImpl();
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
//                case "/insert":
//                    insertTask(request, response);
//                    break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/delete":
//                    deleteTask(request, response);
//                    break;
//                case "/update":
//                    updateTask(request, response);
//                    break;
                case "/list":
                    listTask(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException exception) {
            throw new ServletException(exception);
        }
    }



    private void listTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List <Task> listTask = taskDAO.selectAllTasks(username);
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
        Task existingTask = taskDAO.selectTask(id);

        request.setAttribute("existingTask", existingTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-edit.jsp");
        dispatcher.forward(request, response);

    }

//    private void insertTask(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, SQLException {
////        long id = Integer.parseInt(request.getParameter("id")); // ex 1
//        String title = request.getParameter("title");
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//        String description = request.getParameter("description");
//
//
////        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
////        Task insertTask = new Task(id, title, username, description);
//        taskDAO.insertTask(insertTask);
//        response.sendRedirect("list");
//    }
//
//    private void updateTask(HttpServletRequest request, HttpServletResponse response)
//        throws SQLException, IOException {
//        long id = Integer.parseInt(request.getParameter("id"));
//        String title = request.getParameter("title");
//        String user_id = request.getParameter("user_id");
//        String description = request.getParameter("description");
//
//
//        Task updateTask = new Task (id, title, description, user_id);
//        taskDAO.updateTask(updateTask);
//        response.sendRedirect("list");
//    }
//
//    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
//        throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        taskDAO.deleteTask(id);
//        response.sendRedirect("list");
//    }

}
