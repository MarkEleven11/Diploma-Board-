package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.entity.UserWrapper;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final ImageService imageService;

    private final UserRepository repository;

    @Override
    public UserEntity save(UserEntity model) {
            return repository.save(model);
    }

    @Override
    public UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser updateUser) {
        UserEntity userEntity = findUserEntityByLogin(userDetails.getUsername());
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        save(userEntity);
        return updateUser;
    }

    @Override
    public UserEntity updateImage(UserDetails userDetails, MultipartFile multipartFile) throws IOException {
        UserEntity userEntity = findUserEntityByLogin(userDetails.getUsername());
        userEntity.setImage(imageService.saveUserImage(multipartFile));
        return save(userEntity);
    }

    @Override
    public UserEntity findUserEntityByLogin(String username) {
        return repository.findUserEntityByUsername(username);
    }

    @Override
    public boolean userExists(String username) {
        return repository.existsUserEntityByUsername(username);
    }

    @Override
    public UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails) {
        UserEntity userEntity = findUserEntityByLogin(userDetails.getUsername());
        userEntity.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        save(userEntity);
        return userEntity;
    }

    @Override
    public User getUser(UserDetails userDetails) {
        return userMapper.entityToUserDto(
                findUserEntityByLogin(
                        userDetails.getUsername()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = repository.findUserEntityByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserWrapper(userEntity);
    }
}
