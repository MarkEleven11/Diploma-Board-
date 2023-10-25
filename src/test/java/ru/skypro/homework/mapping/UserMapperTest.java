package ru.skypro.homework.mapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.UserMapper;

class UserMapperTest {

    private final UserMapper mapper = new UserMapper();
    private final int id = 1;
    private final String password = "123456";
    private final String email = "xxx@mail.ru";
    private final String fName = "Oleg";
    private final String lName = "Olegov";
    private final String phone = "+78008889922";
    private final Role role = Role.USER;

    @Test
    void entityToUserDtoTestNoImage() {
        UserEntity entity = new UserEntity(id, password, email, fName, lName, phone, role, null);
        User user = mapper.entityToUserDto(entity);
        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getEmail(), email);
        Assertions.assertEquals(user.getFirstName(), fName);
        Assertions.assertEquals(user.getLastName(), lName);
        Assertions.assertEquals(user.getPhone(), phone);
        Assertions.assertNull(user.getImage());
    }

    @Test
    void entityToUserDtoTestWithImage() {
        UserEntity entity = new UserEntity(id, password, email, fName, lName, phone, role,
                new ImageEntity(1));
        User user = mapper.entityToUserDto(entity);
        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getEmail(), email);
        Assertions.assertEquals(user.getFirstName(), fName);
        Assertions.assertEquals(user.getLastName(), lName);
        Assertions.assertEquals(user.getPhone(), phone);
        Assertions.assertEquals(user.getImage(), entity.getImagePath());
    }

    @Test
    void userDtoToEntityTest() {
        String newFName = "Yuri";
        String newLName = "Gagarin";
        String newPhone = "+78005553535";
        User user = new User(id, email, fName, lName, phone, null);
        UserEntity entity = new UserEntity(id, password, email, newFName, newLName, newPhone, role, null);
        UserEntity newEntity = mapper.userDtoToEntity(user, entity);
        Assertions.assertEquals(newEntity.getFirstName(), fName);
        Assertions.assertEquals(newEntity.getLastName(), lName);
        Assertions.assertEquals(newEntity.getPhone(), phone);
    }

    @Test
    void registerReqDtoToEntityTest() {
        RegisterReq req = new RegisterReq();
        req.setPassword(password);
        req.setRole(role);
        req.setUsername(email);
        req.setLastName(lName);
        req.setFirstName(fName);
        req.setPhone(phone);
        UserEntity entity = mapper.registerReqDtoToEntity(req);
        Assertions.assertEquals(entity.getPhone(), phone);
        Assertions.assertEquals(entity.getLastName(), lName);
        Assertions.assertEquals(entity.getEmail(), email);
        Assertions.assertEquals(entity.getFirstName(), fName);
        Assertions.assertEquals(entity.getRole(), role);
        Assertions.assertEquals(entity.getPassword(), password);
    }
}