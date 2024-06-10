package ru.ollyeys.todoapp.utils;

import java.sql.*;
import java.time.LocalDate;

//    jdbc:postgresql://localhost:5432/test_db

public class JDBCUtils {

    private static final String jdbcURL = System.getenv("DB_URL");


    private static final String jdbcUsername = System.getenv("DB_USER");
    private static final String jdbcPassword = System.getenv("DB_PASSWORD");


    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
}







