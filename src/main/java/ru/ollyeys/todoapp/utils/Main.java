package ru.ollyeys.todoapp.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        if (connection != null) {
            System.out.println("Success!");
        } else {
            System.out.println("Not success");
        }

    }
}
