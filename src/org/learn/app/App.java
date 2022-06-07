package org.learn.app;

import org.learn.app.ui.MaterialTableForm;
import org.learn.app.util.DialogUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showError(null, "Ошибка применения стиля");
        }
        new MaterialTableForm();
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test_table", "root", "952259");
    }
}
