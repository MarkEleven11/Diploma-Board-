package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

public interface UserService extends UserDetailsService {

    UserEntity save(UserEntity model);

    UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser user);

    UserEntity updateImage(UserDetails userDetails, MultipartFile multipartFile) throws IOException;

    UserEntity findUserEntityByLogin(String login);

    boolean userExists(String login);

    UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails);

    User getUser(UserDetails userDetails);
}
