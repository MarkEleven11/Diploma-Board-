package ru.skypro.homework.mapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
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
    void entityToAdsDtoTest() {
        UserEntity user = new UserEntity();
        user.setId(id);
        AdEntity entity = new AdEntity(pk, user, title, price, description, new ImageEntity());
        Ads ads = mapper.entityToAdsDto(entity);
        Assertions.assertEquals(ads.getPk(), pk);
        Assertions.assertEquals(ads.getAuthor(), id);
        Assertions.assertEquals(ads.getTitle(), title);
        Assertions.assertEquals(ads.getPrice(), price);
        Assertions.assertEquals(ads.getImage(), "/ads/image/" + pk);
    }

    @Test
    void entityToFullAdsDto() {
        String authorFirstName = "Yuri";
        String authorLastName = "Gagarin";
        String email = "xxx@ya.ru";
        String phone = "+78005553535";
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setEmail(email);
        user.setPhone(phone);
        user.setFirstName(authorFirstName);
        user.setLastName(authorLastName);
        AdEntity entity = new AdEntity(pk, user, title, price, description, new ImageEntity());
        FullAds fullAds = mapper.entityToFullAdsDto(entity);
        Assertions.assertEquals(fullAds.getPk(), pk);
        Assertions.assertEquals(fullAds.getAuthorFirstName(), authorFirstName);
        Assertions.assertEquals(fullAds.getAuthorLastName(), authorLastName);
        Assertions.assertEquals(fullAds.getDescription(), description);
        Assertions.assertEquals(fullAds.getImage(), "/ads/image/" + pk);
        Assertions.assertEquals(fullAds.getPhone(), phone);
        Assertions.assertEquals(fullAds.getEmail(), email);
        Assertions.assertEquals(fullAds.getPrice(), price);
    }

    @Test
    void createAdsToEntity() {
        CreateAds createAds = new CreateAds();
        createAds.setDescription(description);
        createAds.setPrice(price);
        createAds.setTitle(title);
        UserEntity user = new UserEntity();
        AdEntity adEntity = mapper.createAdsToEntity(createAds, user);
        Assertions.assertEquals(adEntity.getAuthor(), user);
        Assertions.assertEquals(adEntity.getPrice(), price);
        Assertions.assertEquals(adEntity.getDescription(), description);
        Assertions.assertEquals(adEntity.getTitle(), title);
    }
}
