package mapping;

import org.junit.jupiter.api.Test;
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
        assert ads.getPk() == pk;
        assert ads.getAuthor() == id;
        assert ads.getTitle().equals(title);
        assert ads.getPrice() == price;
        assert Objects.equals(ads.getImage(), "/ads/image/" + pk);
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
        assert fullAds.getPk() == pk;
        assert fullAds.getAuthorFirstName().equals(authorFirstName);
        assert fullAds.getAuthorLastName().equals(authorLastName);
        assert fullAds.getDescription().equals(description);
        assert fullAds.getImage().equals("/ads/image/" + pk);
        assert fullAds.getPhone().equals(phone);
        assert fullAds.getEmail().equals(email);
        assert fullAds.getPrice() == price;
    }

    @Test
    void createAdsToEntity() {
        CreateAds createAds = new CreateAds();
        createAds.setDescription(description);
        createAds.setPrice(price);
        createAds.setTitle(title);
        UserEntity user = new UserEntity();
        AdEntity adEntity = mapper.createAdsToEntity(createAds, user);
        assert adEntity.getAuthor() == user;
        assert adEntity.getPrice() == price;
        assert adEntity.getDescription().equals(description);
        assert adEntity.getTitle().equals(title);
    }
}
