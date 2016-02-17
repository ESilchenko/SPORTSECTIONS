package ru.sportsections;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by esilchenko on 16.02.2016.
 */
public class MainClass {
    public static void main(String[] args) {
        //Параметры начального разрешения открываемого окна
        int width = 800;
        int height = 600;
        //===================================================================
        //Конструкторы
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridBagLayout());
        ImageIcon imageIcon = new ImageIcon("src\\sporticon.jpg");
        //===================================================================
        //Формирование вывода окна
        frame.setSize(width, height); //задаём разрешение окна
        frame.setTitle("Система управления детскими спортивными секциями"); //название окна
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрытие приложения после нажатия на крестик
        frame.setBackground(Color.GRAY); //цветовая заливка панели
        frame.setIconImage(imageIcon.getImage()); //иконка программы
        panel.setBorder(new EmptyBorder(20,20,20,20));
        frame.setContentPane(panel);
        frame.add(new LoginPane());
        frame.setLocationRelativeTo(null); //центруем окно по середине экрана
        frame.setVisible(true);
        //===================================================================

    }

}
