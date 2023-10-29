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
        int adId = Math.toIntExact(entity.getAuthor().getId());
        int userId = Math.toIntExact(entity.getId());
        return new Ad(adId,
                userId,
                entity.getImagePath(),
                entity.getPrice(),
                entity.getTitle());
    }

    public ExtendedAd entityToExtendedAdsDto(AdEntity entity) {
        UserEntity user = entity.getAuthor();
        return ExtendedAd.builder()
                .email(user.getUsername())
                .authorFirstName(user.getFirstName())
                .authorLastName(user.getLastName())
                .phone(user.getPhone())
                .image(entity.getImage())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .pk(Math.toIntExact(entity.getId()))
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

