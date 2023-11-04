package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий пользователя
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    /**
     * Уникальный идентификатор пользователя
     */
    private int id;

    /**
     * Адрес электронной почты пользователя
     */
    private String email;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * Номер телефона пользователя
     */
    private String phone;

    /**
     * Ссылка на изображение-аватар пользователя
     */
    private String image;

    /**
     * Ссылка на enum Role - роль пользователя (юзер или админ)
     */
    private Role role;
}