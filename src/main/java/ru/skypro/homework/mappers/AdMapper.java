package ru.skypro.homework.mappers;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс представляет собой компонент, отвечающий за преобразование сущностей и DTO,
 * связанных с объявлениями, в различные форматы и обратно
 *
 * <p>Класс реализует интерфейс {@code Mapper}, который определяет методы для преобразования данных
 * В частности, этот класс выполняет преобразование сущностей {@code AdEntity} в DTO {@code Ad} и {@code ExtendedAd},
 * а также преобразование списка сущностей в DTO {@code Ads}
 *
 * @see Mapper
 * @see Ad
 * @see ExtendedAd
 * @see Ads
 * @see AdEntity
 */
@Component
public class AdMapper {

    /**
     * Метод выполняет преобразование сущности объявления {@code AdEntity} в DTO объявления {@code Ad}
     *
     * @param entity Сущность объявления
     * @return Объект DTO объявления
     */
    public Ad entityToAdsDto(AdEntity entity) {
        int adId = entity.getId();
        int userId = entity.getAuthor().getId();
        return new Ad(adId,
                userId,
                entity.getImagePath(),
                entity.getPrice(),
                entity.getTitle());
    }


    /**
     * Метод выполняет преобразование сущности {@code AdEntity} в DTO объявления {@code ExtendedAd}
     * @param entity Сущность объявления
     * @return Объект DTO объявления
     */
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

    /**
     * Метод выполняет преобразование списка сущностей объявлений {@code AdEntity} в DTO объявлений {@code Ads},
     * включая количество объявлений и сами объявления.
     *
     * @param adEntities список сущностей объявлений
     * @return объект DTO объявлений
     */
    public Ads createAdsToEntity(List<AdEntity> adEntities) {
        return Ads.builder()
                .results(adEntities.stream()
                        .map(this::entityToAdsDto)
                        .collect(Collectors.toList()))
                .count(adEntities.size()).build();
    }
}

