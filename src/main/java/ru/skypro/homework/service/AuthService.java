package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterReq register);
}
