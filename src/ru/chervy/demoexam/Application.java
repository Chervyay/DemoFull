package ru.chervy.demoexam;

import ru.chervy.demoexam.ui.ProductEntityForm;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class Application {
    public static boolean ADMIN_MODE = false;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ADMIN_MODE = "0000".equalsIgnoreCase(JOptionPane.showInputDialog(null,"Введите пароль к режиму администратора, если вы его знаете","Вход", JOptionPane.QUESTION_MESSAGE));

        new ProductEntityForm();
    }

    public static Connection getConnetion() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/demoexam", "root", "25FrE3");
    }

    public static void changeAllFonts(Font font)
    {
        Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, font);
            }
        }
    }
}
