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
    public static String inputLoginField;
    private String inputPasswordField;
    private String hashPassword;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    //Конструкторы
    JButton loginButton = new JButton("Войти");
    JButton cancelButton = new JButton("Выйти");
    JTextField loginField = new JTextField(10);
    JPasswordField passwordField = new JPasswordField(10);
    JLabel loginLabel = new JLabel("Введите логин:   ");
    JLabel passwordLabel = new JLabel("Введите пароль:");
    //===================================================================
    public LoginPane() {
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Окно входа в систему"));
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
                String checkUser = "select count(u.user_id) as cnt, u.first_login, r.role from users u " +
                                   "inner join roles r " +
                                   "on u.user_id = r.user_id " +
                                   "where lower(u.login) = lower('" + inputLoginField + "')" +
                                   " and lower(u.passwd) = lower('" + hashPassword + "') " +
                                   "group by u.first_login, r.role";
                //===================================================================
                //Подключаемся к базе и проверяем пользователя
                connection = ConnectionManager.getConnection();
                try {
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(checkUser);
                    //===================================================================
                    //Параметры признака первого входа в систему и роли.
                    int cnt;
                    String first_login;
                    String role;
                    //===================================================================
                    //Проверяем, вернул ли запрос какую-нибудь строку по пользователю
                    //Если база не вернула строк, то присваиваем технические значения переменным
                    if (!resultSet.isBeforeFirst()) {
                        cnt = 0;
                        first_login = "N";
                        role = "UNKNOWN";
                     //===================================================================
                     //Если база вернула строку, то присваиваем значения переменным из строки
                    } else {
                        resultSet.next();
                        cnt = resultSet.getInt("cnt");
                        first_login = resultSet.getString("first_login");
                        role = resultSet.getString("role");
                    }
                    //===================================================================
                    //Если пользователь есть, и он входит первый раз, ему необходимо сменить пароль
                    if (cnt == 1 && first_login.equals("Y")) {
                        JOptionPane.showMessageDialog(null, "Вы первый раз вошли в систему. Вам необходимо сменить пароль.", "Необходима смена пароля!", JOptionPane.INFORMATION_MESSAGE);
                        Controller.getChangePasswordPane(Controller.frame);
                    //===================================================================
                    //Если пользователь есть, и он входит не первый раз, в зависимости от его роли, ему формируется его рабочее окно
                    } else  if (cnt == 1 && first_login.equals("N")){
                        if (role.equals("TECH_ADMIN")) {
                            JOptionPane.showMessageDialog(null, "Вы успешно вошли в систему как Технический Администратор!", "Успешный вход в систему", JOptionPane.INFORMATION_MESSAGE);
                            Controller.getTechnicalAdminPane(Controller.frame);
                        } else if (role.equals("ADMIN")) {
                            JOptionPane.showMessageDialog(null, "Вы успешно вошли в систему как Администратор!", "Успешный вход в систему", JOptionPane.INFORMATION_MESSAGE);
                        } else if (role.equals("OPERATOR")) {
                            JOptionPane.showMessageDialog(null, "Вы успешно вошли в систему как Оператор!", "Успешный вход в систему", JOptionPane.INFORMATION_MESSAGE);
                        }
                    //===================================================================
                    //Если пользователь нет или пароль не подходит, то выводится сообщение об ошибке
                    } else {
                        JOptionPane.showMessageDialog(null, "Пользователя не существует или не подходит пароль!", "Ошибка входа в систему!", JOptionPane.ERROR_MESSAGE);
                    }
                    //===================================================================
                    //Закрываем работу с базой
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Oracle Error - " + e1.getMessage(), "Запрос в базу содержит ошибки", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
    }
}
