package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UserService userService) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        return encoder.matches(password, userService.findUserEntityByUsername(userName).getPassword());
    }

    @Override
    public boolean register(RegisterReq register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        userService.createUser(
                UserEntity.builder()
                        .password(encoder.encode(register.getPassword()))
                        .username(register.getUsername())
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .phone(register.getPhone())
                        .role(register.getRole())
                        .build());
        return true;
    }

    @Override
    public boolean setPassword(NewPassword newPassword, String name) {
        if (encoder.matches(newPassword.getCurrentPassword(), userService.findUserEntityByUsername(name).getPassword())) {
            manager.changePassword(encoder.encode(newPassword.getNewPassword()), name);
            return true;
        }
        return false;
    }
}
