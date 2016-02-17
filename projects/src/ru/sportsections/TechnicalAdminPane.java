package ru.sportsections;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by esilchenko on 17.02.2016.
 */
public class TechnicalAdminPane extends JPanel {
    //Переменные
    JButton logoutButton = new JButton("Logout");
    //JButton cancelButton = new JButton("Кнопка 2");
    JTextField positionField = new JTextField(10);
    JTextField lastNameField = new JTextField(10);
    JTextField firstNameField = new JTextField(10);
    JTextField middleNameField = new JTextField(10);
    JTextField phoneField = new JTextField(10);
    JTextField emailField = new JTextField(10);
    JTextField notesField = new JTextField(10);
    JLabel positionLabel = new JLabel("Должность:");
    JLabel lastNameLabel = new JLabel("Фамилия:");
    JLabel firstNameLabel = new JLabel("Имя:");
    JLabel middleNameLabel = new JLabel("Отчество:");
    JLabel phoneLabel = new JLabel("Телефон:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel notesLabel = new JLabel("Примечания:");
    JPanel logoutButtonPanel = new JPanel();

    public TechnicalAdminPane() {
        setLayout(new GridBagLayout());
        //setBorder(new TitledBorder("Login"));
        //===================================================================
        //Располагаем лейблы
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints logoutGbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(positionLabel, gbc);
        gbc.gridy++;
        add(lastNameLabel, gbc);
        gbc.gridy++;
        add(firstNameLabel, gbc);
        gbc.gridy++;
        add(middleNameLabel, gbc);
        gbc.gridy++;
        add(phoneLabel, gbc);
        gbc.gridy++;
        add(emailLabel, gbc);
        gbc.gridy++;
        add(notesLabel, gbc);
        //===================================================================
        //Располагаем поля ввода логина и пароля
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        add(positionField, gbc);
        gbc.gridy++;
        add(lastNameField, gbc);
        gbc.gridy++;
        add(firstNameField, gbc);
        gbc.gridy++;
        add(middleNameField, gbc);
        gbc.gridy++;
        add(phoneField, gbc);
        gbc.gridy++;
        add(emailField, gbc);
        gbc.gridy++;
        add(notesField, gbc);
        //===================================================================
        //Располагаем кнопки логина и выхода
        logoutButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoutButtonPanel.add(logoutButton);
        add(logoutButtonPanel,logoutGbc.NORTH);
//        logoutGbc.gridx = 10;
//        logoutGbc.gridy = 10;
//        gbc.gridwidth = 0;
//        logoutGbc.weightx = 0;
//        logoutGbc.fill = GridBagConstraints.NONE;
//        add(logoutButton, logoutGbc);
    }
}
