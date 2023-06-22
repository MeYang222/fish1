package com.student.util;

import java.sql.*;

import static java.lang.Class.forName;

/**
 * 此类用于jdbc连接数据库
 */
public class DbUtil {
    private static String url = "jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static String username = "root";
    private static String password = "root";
    private static String Driver = "com.mysql.cj.jdbc.Driver";


    static {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }



    public static Connection getCon() {
        try {

            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }


    //关闭数据库连接
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }
}

