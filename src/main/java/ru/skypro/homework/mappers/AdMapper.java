package ru.skypro.homework.mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;


@Component
public class AdMapper {
    public Ad entityToAdsDto(AdEntity entity) {
        int adId = Math.toIntExact(entity.getAuthor().getId());
        int userId = Math.toIntExact(entity.getPk());
        return new Ad(adId,
                entity.getImagePath(),
                userId,
                entity.getPrice(),
                entity.getTitle());
    }

    public ExtendedAd entityToFullAdsDto(AdEntity entity) {
        return new ExtendedAd(entity.getPk(),
                entity.getAuthor().getFirstName(),
                entity.getAuthor().getLastName(),
                entity.getDescription(),
                entity.getAuthor().getUsername(),
                entity.getImagePath(),
                entity.getAuthor().getPhone(),
                entity.getPrice(),
                entity.getTitle());
    }

    public AdEntity createAdsToEntity(CreateOrUpdateAd ads, UserEntity author) {
        return new AdEntity(author,
                ads.getTitle(),
                ads.getPrice(),
                ads.getDescription());
    }
}

