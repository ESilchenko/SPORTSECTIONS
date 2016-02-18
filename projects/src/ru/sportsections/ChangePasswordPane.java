package ru.sportsections;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by esilchenko on 17.02.2016.
 */
public class ChangePasswordPane extends JPanel {
    //Переменные
    private String inputNewPasswordField;
    private String inputConfirmNewPasswordField;
    private String hashPassword;
    private Connection connection = null;
    private Statement statement = null;
    private int resultUpdate = 0;
    private String first_login = "N";
    //Конструкторы
    JButton changePasswordButton = new JButton("Сохранить");
    JButton cancelButton = new JButton("Выйти");
    JPasswordField newPasswordField = new JPasswordField(10);
    JPasswordField confirmNewPasswordField = new JPasswordField(10);
    JLabel newPasswordLabel = new JLabel("Введите новый пароль:");
    JLabel confirmNewPasswordLabel = new JLabel("Подтвердите новый пароль:");
    //===================================================================
    public ChangePasswordPane() {
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Окно смены пароля"));
        //===================================================================
        //Располагаем лейблы
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(newPasswordLabel, gbc);
        gbc.gridy++;
        add(confirmNewPasswordLabel, gbc);
        //===================================================================
        //Располагаем поля ввода логина и пароля
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        add(newPasswordField, gbc);
        gbc.gridy++;
        add(confirmNewPasswordField, gbc);
        //===================================================================
        //Располагаем кнопки логина и выхода
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        add(changePasswordButton, gbc);
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
        changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //===================================================================
                //Читаем пароль и его подтверждение
                inputNewPasswordField = new String(newPasswordField.getPassword());
                inputConfirmNewPasswordField = new String (confirmNewPasswordField.getPassword());
                //===================================================================
                //Сравниваем значения из двух полей. Если они различаются, уведомляем пользователя
                if (!inputNewPasswordField.equals(inputConfirmNewPasswordField)) {
                    JOptionPane.showMessageDialog(null, "Новый пароль и его подтверждение не совпадают! Проверьте правильность ввода!", "Ошибка смены пароля!", JOptionPane.ERROR_MESSAGE);
                //===================================================================
                //Если значения из двух полей совпадают, тогда хешируем пароль для передачи в базу
                } else {
                    try {
                        hashPassword = MD5Digest.getPasswordHash(inputNewPasswordField);
                        inputNewPasswordField = null;

                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    }
                    //===================================================================
                    //Готовим запрос в базу для обновления пароля пользователя
                    String updateUserPassword = "update users set passwd = '" + hashPassword + "', " +
                            "first_login = '" + first_login + "', " +
                            "created_by = '" + LoginPane.inputLoginField + "', " +
                            "created_date = sysdate " +
                            "where lower(login) = lower('" + LoginPane.inputLoginField + "')";
                    System.out.println(updateUserPassword);
                    //===================================================================
                    //Подключаемся к базе и проверяем пользователя
                    connection = ConnectionManager.getConnection();
                    try {
                        statement = connection.createStatement();
                        resultUpdate = statement.executeUpdate(updateUserPassword);
                        //===================================================================
                        //Проверяем, обновил запрос пароль в базе или нет
                        //Если база вернула 0 строк, то пароль не обновлён. Пугаем пользователя страшным сообщением закрываем программу с ошибкой.
                        if (resultUpdate == 0) {
                            JOptionPane.showMessageDialog(null, "Всё очень плохо! Никуда не уходите, за вами уже выехали!", "Неизвестная ошибка! Программа будет закрыта!", JOptionPane.ERROR_MESSAGE);
                            System.out.println(updateUserPassword);
                            System.exit(1);
                            //===================================================================
                            //Если база вернула 1 строку, то пароль обновлён успешно. Возвращаем пользователя на окно логина
                        } else if (resultUpdate == 1) {
                            JOptionPane.showMessageDialog(null, "Смена пароля прошла успешно. Вам нужно войти в систему с новым паролем.", "Пароль успешно обновлён", JOptionPane.INFORMATION_MESSAGE);
                            Controller.getLoginPane(Controller.frame);
                            //===================================================================
                            //Если база вернула не 1 строку и не 0 строк, то что-то пошло не так и обновилось больше строк чем нужно.
                            //Это плохо. Ищи свищи теперь, что произошло. Пугаем пользователя страшным сообщением и закрываем программу с ошибкой.
                        } else {
                            JOptionPane.showMessageDialog(null, "Всё очень плохо! Никуда не уходите, за вами уже выехали!", "Неизвестная ошибка! Программа будет закрыта!", JOptionPane.ERROR_MESSAGE);
                            System.out.println(updateUserPassword);
                            System.exit(1);
                        }
                        //===================================================================
                        //Закрываем работу с базой
                        statement.close();
                        connection.close();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Oracle Error - " + e1.getMessage(), "Запрос в базу содержит ошибки!", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }

            }
        });
    }
}