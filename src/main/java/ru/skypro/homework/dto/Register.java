package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий данные для регистрации нового пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Register {

    /**
     * Имя пользователя
     */
    private String username;

    /**
     * Пароль пользователя для регистрации
     */
    private String password;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * Номер телефона ползователя
     */
    private String phone;

    /**
     * Роль пользователя
     */
    private Role role;

}
