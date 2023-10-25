package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.RegisterReq;

public interface AuthService {
    boolean login(String userName, char[] password);

    boolean register(RegisterReq register);

    boolean setPassword(NewPassword newPassword, String name);
}
