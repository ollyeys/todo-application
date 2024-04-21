package ru.ollyeys.todoapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ollyeys.todoapp.dao.UserDAO;
import ru.ollyeys.todoapp.model.User;

import java.io.IOException;

@WebServlet("/register")
public class UserController extends HttpServlet {
    private static final long serialID = 1L;
    private UserDAO userDAO;

    public void init(){
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);

        try {
            int result = userDAO.registerUser(user);
            if (result == 1) {
                request.setAttribute("NOTIFICATION", "User registered successfully! You can go to the Login tab ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
}
