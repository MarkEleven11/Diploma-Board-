package ru.skypro.homework.mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AdMapper {
    public Ad entityToAdsDto(AdEntity entity) {
        int adId = entity.getId();
        int userId = entity.getAuthor().getId();
        return new Ad(adId,
                userId,
                entity.getImagePath(),
                entity.getPrice(),
                entity.getTitle());
    }

    public ExtendedAd entityToExtendedAdsDto(AdEntity entity) {
        UserEntity user = entity.getAuthor();
        int adId = entity.getId();
        return ExtendedAd.builder()
                .pk(adId)
                .email(user.getUsername())
                .authorFirstName(user.getFirstName())
                .authorLastName(user.getLastName())
                .phone(user.getPhone())
                .image(entity.getImagePath())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .build();
    }

    public Ads createAdsToEntity(List<AdEntity> adEntities) {
        return Ads.builder()
                .results(adEntities.stream()
                        .map(this::entityToAdsDto)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }
}

