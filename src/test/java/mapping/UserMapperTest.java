package mapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        assert user.getId() == id;
        assert user.getEmail().equals(email);
        assert user.getFirstName().equals(fName);
        assert user.getLastName().equals(lName);
        assert user.getPhone().equals(phone);
        Assertions.assertNull(user.getImage());
    }

    @Test
    void entityToUserDtoTestWithImage() {
        UserEntity entity = new UserEntity(id, password, email, fName, lName, phone, role,
                new ImageEntity(1));
        User user = mapper.entityToUserDto(entity);
        assert user.getId() == id;
        assert user.getEmail().equals(email);
        assert user.getFirstName().equals(fName);
        assert user.getLastName().equals(lName);
        assert user.getPhone().equals(phone);
        assert user.getImage().equals(entity.getImagePath());
    }

    @Test
    void userDtoToEntityTest() {
        String newFName = "Yuri";
        String newLName = "Gagarin";
        String newPhone = "+78005553535";
        User user = new User(id, email, fName, lName, phone, null);
        UserEntity entity = new UserEntity(id, password, email, newFName, newLName, newPhone, role, null);
        UserEntity newEntity = mapper.userDtoToEntity(user, entity);
        assert newEntity.getFirstName().equals(fName);
        assert newEntity.getLastName().equals(lName);
        assert newEntity.getPhone().equals(phone);
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
        assert entity.getPhone().equals(phone);
        assert entity.getLastName().equals(lName);
        assert entity.getEmail().equals(email);
        assert entity.getFirstName().equals(fName);
        assert entity.getRole().equals(role);
        assert entity.getPassword().equals(password);
    }
}