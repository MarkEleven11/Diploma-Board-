package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class LoginReq {

    private String username;
    private char[] password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password.toCharArray();
    }
}
