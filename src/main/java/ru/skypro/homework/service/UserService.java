package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

public interface UserService {

    User update(User user, String name);

    User get(String name);

    void uploadImage(MultipartFile image, String name) throws IOException;

    UserEntity findUserEntityByUsername(String username);


    void createUser(UserEntity user);

    User getUser(UserDetails userDetails);
}

