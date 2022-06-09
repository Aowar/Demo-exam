package org.leaarn_school.app.manager;

import org.leaarn_school.app.App;
import org.leaarn_school.app.entity.AuthEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthManager {
    public static String selectUser(String nick, String password) throws SQLException {
        Connection c = App.getConnection();
        String sql = "SELECT * FROM users WHERE nick=? AND password =?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, nick);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        List<AuthEntity> list = new ArrayList<>();
        while (rs.next()) {
            return rs.getString("permission");
        }
        return "Такого пользователя не существует";
    }
}
