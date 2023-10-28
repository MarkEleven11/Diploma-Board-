package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final PasswordEncoder encoder;

    private final UserDetailsManager manager;

    @Override
    public boolean login(String userName, char[] password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        return encoder.matches(CharBuffer.wrap(password),
                userService.findUserEntityByUsername(userName).getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        userService.createUser(
                UserEntity.builder()
                        .password(encoder.encode(CharBuffer.wrap(register.getPassword())))
                        .username(register.getUsername())
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .phone(register.getPhone())
                        .role(register.getRole())
                        .build());
        return true;
    }

}
