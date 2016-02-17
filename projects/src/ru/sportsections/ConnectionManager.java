package ru.sportsections;

import java.sql.*;
import java.math.*;

/**
 * Created by esilchenko on 16.02.2016.
 */
public class ConnectionManager {

    private static String connDriver = "oracle.jdbc.driver.OracleDriver";
    private static String connStr = "jdbc:oracle:thin:@192.168.236.47:1521:ORCL";
    private static String connLogin = "silchenko";
    private static String connPassword = "silchenko";
    private static Connection connection = null;

    public static Connection getConnection() {
        //===================================================================
        //Проверяем доступность JDBC драйвера Oracle
        try {
            Class.forName(connDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер Oracle не подключен!");
            e.printStackTrace();
        }
        //===================================================================
        //Проверяем, доступна база или нет. Пробуем подключиться.
        try {
            connection = DriverManager.getConnection(connStr, connLogin, connPassword);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения! Проверьте настройки подключения или доступность базы!");
            e.printStackTrace();
        }
        //===================================================================
        //Если подключение прошло успешно, то всё в порядке, иначе - ошибка.
        if (connection != null) {
            System.out.println("Подключение к базе прошло успешно! Можно работать!");
        } else {
            System.out.println("Ошибка подключения! Всё пропало!");
        }
        return connection;
    }
}