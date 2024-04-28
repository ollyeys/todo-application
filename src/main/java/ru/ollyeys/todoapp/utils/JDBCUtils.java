package ru.ollyeys.todoapp.utils;

import java.sql.*;

public class JDBCUtils {

    private static final String jdbcURL = System.getenv("DB_URL");

    private static final String jdbcUsername = System.getenv("DB_USER");
    private static final String jdbcPassword = System.getenv("DB_PASSWORD");


    public static Connection getConnection() throws SQLException {
        System.out.println(jdbcURL);
        System.out.println(jdbcUsername);
        System.out.println(jdbcPassword);
        Connection connection = null;
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;


    }

}

//    public static void main(String[] args) {
//
//        System.out.println("Testing connection to PostgreSQL JDBC");
//
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("PostgreSQL JDBC Driver successfully connected");
//        Connection connection = null;
//
//        try {
//            connection = DriverManager
//                    .getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//
//        } catch (SQLException e) {
//            System.out.println("Connection Failed");
//            e.printStackTrace();
//            return;
//        }
//
//    }


//    public static Date getSQLDate(LocalDate date) {
//        return java.sql.Date.valueOf(date);
//    }

//    public static LocalDate getUtilDate(Date sqlDate) {
//        return sqlDate.toLocalDate();
//    }


