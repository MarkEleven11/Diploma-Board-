package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
