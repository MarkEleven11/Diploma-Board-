package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPassword {
    /*
    *текущий пароль
    */
    private String currentPassword;

    /*
    *новый пароль
     */
    private String newPassword;
}