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
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.entity.UserWrapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User update(User user, String name) {
        return mapper.entityToUserDto(userRepository.save(mapper.userDtoToEntity(user, getEntity(name))));
    }

    @Override
    public UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser updateUser) {
        UserEntity userEntity = findUserEntityByUsername(userDetails.getUsername());
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return updateUser;
    }

    @Override
    public User get(String name) {
        return mapper.entityToUserDto(getEntity(name));
    }

    private UserEntity getEntity(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public UserEntity uploadImage(MultipartFile image, UserDetails userDetails) throws IOException {
        UserEntity userEntity = findUserEntityByUsername(userDetails.getUsername());
        userEntity.setImage(imageService.saveImage(image));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserEntityByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(UserEntity userDetails) {
        return mapper.entityToUserDto(
                findUserEntityByUsername(
                        userDetails.getUsername()));
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsUserEntityByUsername(username);
    }

    @Override
    public UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails) {
        UserEntity userEntity = findUserEntityByUsername(userDetails.getUsername());
        userEntity.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserWrapper(findUserEntityByUsername(username));
    }
}
