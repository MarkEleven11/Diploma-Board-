package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPassword {
    private String currentPassword;
    private String newPassword;
}