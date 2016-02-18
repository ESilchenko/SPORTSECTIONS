package ru.sportsections;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by esilchenko on 17.02.2016.
 */
public class TechnicalAdminPane extends JPanel {
    //===================================================================
    //Переменные + кнопки
    JButton saveButton = new JButton("Сохранить");
    JButton cancelButton = new JButton("Выйти");
    //===================================================================
    //Переменные + поля ввода
    JTextField positionField = new JTextField(20);
    JTextField lastNameField = new JTextField(20);
    JTextField firstNameField = new JTextField(20);
    JTextField middleNameField = new JTextField(20);
    JTextField phoneField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField notesField = new JTextField(20);
    JTextField loginField = new JTextField(20);
    JTextField passwordField = new JTextField(20);
    String[] roleField = {"ADMIN"};
    JTextField roleDescField = new JTextField(20);
    JComboBox<String> roleComboBoxField = new JComboBox<>(roleField);
    //===================================================================
    //Переменные + лейблы
    JLabel positionLabel = new JLabel("Должность:");
    JLabel lastNameLabel = new JLabel("Фамилия:");
    JLabel firstNameLabel = new JLabel("Имя:");
    JLabel middleNameLabel = new JLabel("Отчество:");
    JLabel phoneLabel = new JLabel("Телефон:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel notesLabel = new JLabel("Примечания:");
    JLabel loginLabel = new JLabel("Логин:");
    JLabel passwordLabel = new JLabel("Пароль:");
    JLabel roleLabel = new JLabel("Роль:");
    JLabel roleDescLabel = new JLabel("Описание роли:");
    //===================================================================
    //Переменные + поля вывода
    Object[] columnNames = {"Должность", "Фамилия", "Имя", "Отчество", "Телефон", "Email", "Примечания"};
    Object[][] data = {
            {"Администратор", "Иванов", "Иван", "Иванович", "7-911-111-11-11", "ivanov@mail.ru", "Хороший сотрудник"},
            {"Оператор", "Петров", "Иван", "Петрович", "7-922-222-22-22", "petrov@mail.ru", "Нормальный сотрудник"},
            {"Директор", "Сидоров", "Пётр", "Иванович", "7-933-333-33-33", "sidorov@mail.ru", "Обычный сотрудник"}
    };
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);

    public TechnicalAdminPane() {
//        GridBagConstraints gbc = new GridBagConstraints();
//        setLayout(new GridBagLayout());
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        //===================================================================
//        //Располагаем лейблы
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        add(positionLabel, gbc);
//        gbc.gridy++;
//        add(lastNameLabel, gbc);
//        gbc.gridy++;
//        add(firstNameLabel, gbc);
//        gbc.gridy++;
//        add(middleNameLabel, gbc);
//        gbc.gridy++;
//        add(phoneLabel, gbc);
//        gbc.gridy++;
//        add(emailLabel, gbc);
//        gbc.gridy++;
//        add(notesLabel, gbc);
//        gbc.gridy++;
//        add(loginLabel, gbc);
//        gbc.gridy++;
//        add(passwordLabel, gbc);
//        gbc.gridy++;
//        add(roleLabel, gbc);
//        gbc.gridy++;
//        add(roleDescLabel, gbc);
//        //===================================================================
//        //Располагаем поля ввода
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        add(positionField, gbc);
//        gbc.gridy++;
//        add(lastNameField, gbc);
//        gbc.gridy++;
//        add(firstNameField, gbc);
//        gbc.gridy++;
//        add(middleNameField, gbc);
//        gbc.gridy++;
//        add(phoneField, gbc);
//        gbc.gridy++;
//        add(emailField, gbc);
//        gbc.gridy++;
//        add(notesField, gbc);
//        gbc.gridy++;
//        add(loginField, gbc);
//        gbc.gridy++;
//        add(passwordField, gbc);
//        gbc.gridy++;
//        add(roleComboBoxField, gbc);
//        gbc.gridy++;
//        add(roleDescField, gbc);
//        //===================================================================
//        //Располагаем кнопки
//        gbc.gridx = 1;
//        gbc.gridy++;
//        gbc.gridwidth = 1;
//        gbc.weightx = 1;
//        gbc.fill = GridBagConstraints.BOTH;
//        add(saveButton, gbc);
//        gbc.gridx++;
//        add(cancelButton, gbc);

        add(scrollPane);
        //===================================================================
        //Слушаем нажатие кнопки Cancel. При нажатии закрываем приложение
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

    }

    public static String checkPositionField(String positionField) {
        if (positionField.length() == 0) {
            return "Поле не может оставаться пустым";
        } else if (positionField.length() > 50) {
            return "Значение поля не может превышать 50 символов";
        } else {
            return "Всё в порядке";
        }
    }
}
