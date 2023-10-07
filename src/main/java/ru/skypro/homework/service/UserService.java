package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

public interface UserService {

    User update(User user, String name);

    void delete(String name);

    User get(String name);

    UserEntity getEntity(String name);

    void uploadImage(MultipartFile image, String name) throws IOException;

    UserEntity getEntityById(int id);
    void changePassword(String newPassword, String name);
    boolean userExists(String username);
    void createUser(UserEntity user);
}
