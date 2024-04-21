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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private LoginDAO loginDAO;

    public void init() {
        loginDAO = new LoginDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
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
        System.out.println(username);
        System.out.println(password);
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        try {
            if (loginDAO.validate(login)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", login.getUsername());
                List<Task> listTask = loginDAO.selectAllTasks(username);
                request.setAttribute("listTask", listTask);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/task-list.jsp");
                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
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
