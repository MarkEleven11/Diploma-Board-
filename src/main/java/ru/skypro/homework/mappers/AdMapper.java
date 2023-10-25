package ru.skypro.homework.mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;


@Component
public class AdMapper {
    public Ads entityToAdsDto(AdEntity entity) {
        return new Ads(entity.getAuthor().getId(), entity.getImagePath(),
                entity.getPk(), entity.getPrice(), entity.getTitle());
    }

    public FullAds entityToFullAdsDto(AdEntity entity) {
        return new FullAds(entity.getPk(), entity.getAuthor().getFirstName(), entity.getAuthor().getLastName(),
                entity.getDescription(), entity.getAuthor().getUsername(), entity.getImagePath(),
                entity.getAuthor().getPhone(), entity.getPrice(), entity.getTitle());
    }

    public AdEntity createAdsToEntity(CreateAds ads, UserEntity author) {
        return new AdEntity(author, ads.getTitle(), ads.getPrice(), ads.getDescription());
    }
}

