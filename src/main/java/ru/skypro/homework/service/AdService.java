package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

public interface AdService {

    Ads add(CreateAds properties, MultipartFile image, UserEntity userEntity) throws IOException;

    FullAds getFullAdsById(int id);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAllMyAds(String name);

    void delete(int id) throws IOException;

    Ads update(int id, CreateAds ads);

    void uploadImage(int id, MultipartFile image) throws IOException;
}
