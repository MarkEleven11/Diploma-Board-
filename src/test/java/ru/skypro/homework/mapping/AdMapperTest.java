package ru.skypro.homework.mapping;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.AdMapper;

class AdMapperTest {
    private final AdMapper mapper = new AdMapper();
    private final int pk = 1;
    private final int id = 10;
    private final int price = 100;
    private final String title = "Little kitty";
    private final String description = "Pretty white kitty";



    @Test
    void entityToFullAdsDto() {
        String authorFirstName = "Yuri";
        String authorLastName = "Gagarin";
        String email = "xxx@ya.ru";
        String phone = "+78005553535";
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setUsername(email);
        user.setPhone(phone);
        user.setFirstName(authorFirstName);
        user.setLastName(authorLastName);

    }

}
