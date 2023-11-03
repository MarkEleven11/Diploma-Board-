package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий новый пароль пользователя
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPassword {

    /**
     * Текущий пароль пользователя
     */
    private String currentPassword;

    /**
     * Новый пароль пользователя
     */
    private String newPassword;
}