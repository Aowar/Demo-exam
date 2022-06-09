package org.leaarn_school.app.ui;

import org.leaarn_school.app.App;
import org.leaarn_school.app.entity.AuthEntity;
import org.leaarn_school.app.manager.AuthManager;
import org.leaarn_school.app.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AuthForm extends BaseForm {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPanel mainPanel;
    private JButton enterButton;

    public AuthForm() {
        super(800, 600);
        setContentPane(mainPanel);
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        enterButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            try {
                String permission = AuthManager.selectUser(login, password);
                if(permission.equals("admin")) {
                    App.isAdmin = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            dispose();
            new ServiceTableForm();
        });
    }
}
