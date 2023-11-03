package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Класс, представляющий учетные данные для входа пользователя
 */
@Data
@Builder
public class Login {

    /**
     * Имя пользователя (логин)
     */
    private String username;

    /**
     * Пароль учетной записи пользователя
     */
    private String password;


    /**
     * Конструктор для создания объекта класса {@code Login} с заданным именем пользователя и паролем.
     *
     * @param username Имя пользователя (логин)
     * @param password Пароль учетной записи пользователя
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
