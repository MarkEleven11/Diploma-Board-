package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.FindNoEntityException;
import ru.skypro.homework.mappers.UserMapper;
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

    @Override
    public User update(User user, String name) {
        return mapper.entityToUserDto(userRepository.save(mapper.userDtoToEntity(user, getEntity(name))));
    }

    @Override
    public User get(String name) {
        return mapper.entityToUserDto(getEntity(name));
    }

    private UserEntity getEntity(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void uploadImage(MultipartFile image, String name) throws IOException {
        UserEntity userEntity = getEntity(name);
        ImageEntity imageEntity = userEntity.getImage();
        userEntity.setImage(imageService.saveImage(image));
        userRepository.save(userEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
    }

    private UserEntity getEntityById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new FindNoEntityException("пользователь"));
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
    public User getUser(UserDetails userDetails) {
        return mapper.entityToUserDto(
                findUserEntityByUsername(
                        userDetails.getUsername()));
    }
}
