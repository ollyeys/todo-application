package ru.ollyeys.todoapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.ollyeys.todoapp.dao.LoginDAO;
import ru.ollyeys.todoapp.model.Login;
import ru.ollyeys.todoapp.model.Task;
import ru.ollyeys.todoapp.model.TaskDTO;
import ru.ollyeys.todoapp.model.User;
import ru.ollyeys.todoapp.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final String GET_USER_ID = "select id from users where username = ?;";

    private LoginDAO loginDAO;

    public void init() {
        loginDAO = new LoginDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("login.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        System.out.println("Authentification start");

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            if (loginDAO.validate(user)) {
                HttpSession session = request.getSession();
                Connection connection = JDBCUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select id from users where username = ?;");
                preparedStatement.setString(1, user.getUsername());
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    Integer userId = rs.getInt("id");
                    user.setUserId(userId);
                }

                session.setAttribute("username", user.getUsername());
                session.setAttribute("userId", user.getUserId());

                List <TaskDTO> listTask = loginDAO.selectAllTasks(user.getUserId());

                request.setAttribute("listTask", listTask);
                RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
