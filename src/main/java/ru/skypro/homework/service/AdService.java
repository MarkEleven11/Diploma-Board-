package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;


public interface AdService {

    AdEntity save(AdEntity model);

    AdEntity get(Long id);

    Ads getAllAds();

    Ad create(UserEntity userEntity,
              CreateOrUpdateAd createOrUpdateAd,
              MultipartFile multipartFile) throws IOException;

    ExtendedAd getExtendedAdsById(Long id);

    Ads getAllMyAds(UserEntity userEntity);

    Ad update(Long id, CreateOrUpdateAd ads);

    Ad uploadImage(Long id, MultipartFile image) throws IOException;

    String deleteAd(UserEntity userEntity, Long id);


}
