package ru.skypro.homework.mapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User;
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
}