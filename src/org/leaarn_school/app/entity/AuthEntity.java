package org.leaarn_school.app.entity;

import lombok.Data;

@Data
public class AuthEntity {
    private int id;
    private String nick;
    private String password;

    public AuthEntity(int id, String nick, String password) {
        this.id = id;
        this.nick = nick;
        this.password = password;
    }
}
