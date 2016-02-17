package ru.sportsections;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by esilchenko on 16.02.2016.
 */
public class LoginPane extends JPanel {
    //Переменные
    private String inputLoginField;
    private String inputPasswordField;
    private String hashPassword;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    //Конструкторы
    JButton loginButton = new JButton("Login");
    JButton cancelButton = new JButton("Cancel");
    JTextField loginField = new JTextField(10);
    JPasswordField passwordField = new JPasswordField(10);
    JLabel loginLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    //===================================================================
    public LoginPane() {
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Login"));
        //===================================================================
        //Располагаем лейблы
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginLabel, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        //===================================================================
        //Располагаем поля ввода логина и пароля
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        add(loginField, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        //===================================================================
        //Располагаем кнопки логина и выхода
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(loginButton, gbc);
        gbc.gridx++;
        add(cancelButton, gbc);
        //===================================================================
        //Слушаем нажатие кнопки Cancel. При нажатии закрываем приложение
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        //===================================================================
        //Слушаем нажатие кнопки Login. При нажатии делаем проверку и логиним сотрудника
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //===================================================================
                //Читаем логин пользователя
                inputLoginField = loginField.getText();
                //===================================================================
                //Читаем пароль и хешируем его
                inputPasswordField = new String(passwordField.getPassword());
                try {
                    hashPassword = MD5Digest.getPasswordHash(inputPasswordField);
                    inputPasswordField = null;
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                }
                //===================================================================
                //Готовим запрос в базу для проверки пользователя
                String checkUser = "select count(user_id) as cnt from users " +
                                   "where lower(login) = lower('" + inputLoginField + "')" +
                                   " and lower(passwd) = lower('" + hashPassword + "')";
                //===================================================================
                //Подключаемся к базе и проверяем пользователя
                connection = ConnectionManager.getConnection();
                try {
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(checkUser);
                    resultSet.next();
                    int cnt = resultSet.getInt("cnt");
                    if (cnt == 1) {
                        JOptionPane.showMessageDialog(null, "Вы успешно вошли в систему!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Пользователя не существует или не подходит пароль!", "Ошибка входа в систему", JOptionPane.ERROR_MESSAGE);
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
