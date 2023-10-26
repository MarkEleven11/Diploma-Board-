package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;

public interface AuthService {
    boolean login(String userName, char[] password);

    boolean register(Register register);

    boolean setPassword(NewPassword newPassword, String name);
}
