package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

public interface UserService {

    User update(User user, String name);

    User get(String name);

    UserEntity uploadImage(MultipartFile image, UserDetails userDetails) throws IOException;

    UserEntity findUserEntityByUsername(String username);


    void createUser(UserEntity user);

    UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser updateUser);

    User getUser(UserEntity userDetails);

    boolean userExists(String username);

    UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}

