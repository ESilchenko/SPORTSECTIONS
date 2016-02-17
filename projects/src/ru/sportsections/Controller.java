package ru.sportsections;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by esilchenko on 17.02.2016.
 */
public class Controller {
    //===================================================================
    //Переменные
    public static JFrame frame = new JFrame();
    private static int width = 800;  //Параметры начального разрешения открываемого окна
    private static int height = 600; //Параметры начального разрешения открываемого окна
    //===================================================================
    //Конструктор класса
    public Controller () {
        //===================================================================
        //Конструкторы
        ImageIcon imageIcon = new ImageIcon("src\\sporticon.jpg");
        //===================================================================
        //Формирование первоначального вывода окна
        frame.setSize(width, height); //задаём разрешение окна
        frame.setTitle("Система управления детскими спортивными секциями"); //название окна
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрытие приложения после нажатия на крестик
        frame.setBackground(Color.GRAY); //цветовая заливка панели
        frame.setIconImage(imageIcon.getImage()); //иконка программы
        frame.setLocationRelativeTo(null); //центруем окно по середине экрана
        Controller.getLoginPane(Controller.frame);
    }
    //===================================================================
    //Метод формирования JPanel логина
    public static void getLoginPane(JFrame frame) {
        JPanel panelLogin = new JPanel (new GridBagLayout());
        panelLogin.setBorder(new EmptyBorder(20,20,20,20));
        frame.setContentPane(panelLogin);
        frame.add(new LoginPane());
        frame.setVisible(true);
    }
    //===================================================================
    //Метод формирования JPanel смены пароля
    public static void getChangePasswordPane(JFrame frame) {
        JPanel panelChangePassword = new JPanel (new GridBagLayout());
        panelChangePassword.setBorder(new EmptyBorder(20,20,20,20));
        frame.setContentPane(panelChangePassword);
        frame.add(new ChangePasswordPane());
        frame.setVisible(true);
    }
    //===================================================================
    //Метод формирования JPanel технического администратора
    public static void getTechnicalAdminPane(JFrame frame) {
        JPanel panelTechnicalAdmin = new JPanel (new GridBagLayout());
        panelTechnicalAdmin.setBorder(new EmptyBorder(20,20,20,20));
        frame.setContentPane(panelTechnicalAdmin);
        frame.add(new TechnicalAdminPane());
        frame.setVisible(true);
    }
}
