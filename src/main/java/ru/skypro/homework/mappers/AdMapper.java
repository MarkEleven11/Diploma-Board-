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
        int userId = entity.getAuthor().getId();
        int adId = entity.getId();
        return new Ad(adId,
                userId,
                entity.getImagePath(),
                entity.getPrice(),
                entity.getTitle());
    }

    public ExtendedAd entityToExtendedAdsDto(AdEntity ad) {
        if ( ad == null ) {
            return null;
        }

        ExtendedAd.ExtendedAdBuilder extendedAdDTO = ExtendedAd.builder();

        extendedAdDTO.pk( ad.getId() );
        extendedAdDTO.description( ad.getDescription() );
        extendedAdDTO.image( ad.getImage() );
        extendedAdDTO.price( ad.getPrice() );
        extendedAdDTO.title( ad.getTitle() );

        return extendedAdDTO.build();
    }

    public Ads createAdsToEntity(List<AdEntity> adEntities) {
        return Ads.builder()
                .results(adEntities.stream()
                        .map(this::entityToAdsDto)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }
}

