package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Component
public class UserMapper {
    public User entityToUserDto(UserEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getFirstName(),
                entity.getLastName(), entity.getPhone(), entity.getImagePath());
    }

    public UserEntity userDtoToEntity(User user, UserEntity entity) {
        entity.setPhone(user.getPhone());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }

    public UserEntity registerReqDtoToEntity(RegisterReq req) {
        return new UserEntity(req.getPassword(), req.getUsername(), req.getFirstName(),
                req.getLastName(), req.getPhone(), req.getRole());
    }
}
