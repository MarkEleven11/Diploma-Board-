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

    Ad add(CreateOrUpdateAd properties, MultipartFile image, UserEntity userEntity) throws IOException;

    ExtendedAd getFullAdsById(int id);

    Ads getAllAds();

    Ads getAllMyAds(String name);

    void delete(int id) throws IOException;

    Ad update(int id, CreateOrUpdateAd ads);

    void uploadImage(int id, MultipartFile image) throws IOException;

    AdEntity get(int id);

}
