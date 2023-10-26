package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final ImageService imageService;
    private final AdMapper mapper;

    @Override
    public Ad add(CreateOrUpdateAd properties, MultipartFile image, UserEntity userEntity) throws IOException {
        AdEntity ad = mapper.createAdsToEntity(properties, userEntity);
        ad.setImage(imageService.saveImage(image));
        return mapper.entityToAdsDto(adRepository.save(ad));
    }

    @Override
    public ExtendedAd getFullAdsById(int id) {
        return mapper.entityToFullAdsDto(getEntityById(id));
    }

    @Override
    public void delete(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public Ad update(int id, CreateOrUpdateAd ads) {
        AdEntity entity = getEntityById(id);
        entity.setTitle(ads.getTitle());
        entity.setDescription(ads.getDescription());
        entity.setPrice(ads.getPrice());
        adRepository.save(entity);
        return mapper.entityToAdsDto(entity);
    }

    private AdEntity getEntityById(int id) {
        return adRepository.findById(id);
    }

    @Override
    public void uploadImage(int id, MultipartFile image) throws IOException {
        AdEntity adEntity = getEntityById(id);
        ImageEntity imageEntity = adEntity.getImage();
        adEntity.setImage(imageService.saveImage(image));
        adRepository.save(adEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
    }

    @Override
    public final AdEntity get(int id) {
        return adRepository.findById(id);
    }

    @Override
    public Ads getAllAds() {
        return getWrapper(adRepository.findAll());
    }

    @Override
    public Ads getAllMyAds(String name) {
        return getWrapper(adRepository.findAllByAuthorUsername(name));
    }

    private Ads getWrapper(List<AdEntity> list) {
        List<Ad> result = new LinkedList<>();
        list.forEach((entity -> result.add(mapper.entityToAdsDto(entity))));
        return new Ads(result.size(), result);
    }
}
