package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.lang.module.FindException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final ImageService imageService;
    private final AdMapper mapper;
    private final CommentRepository commentRepository;

    @Override
    public final AdEntity save(AdEntity model) {
        return adRepository.save(model);
    }

    @Override
    public final void delete(int id) throws IOException {
        AdEntity adEntity = get(id);
        adRepository.deleteById(id);
        imageService.deleteImage(adEntity.getImage());
        commentRepository.deleteAll(adEntity.getComments());
    }

    @Override
    public final AdEntity get(int id) {
        return adRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Ad create(UserEntity userEntity,
                     CreateOrUpdateAd createOrUpdateAd,
                     MultipartFile multipartFile) throws IOException {
        return mapper.entityToAdsDto(
                save(new AdEntity()
                        .setFieldsAndReturnEntity(userEntity, createOrUpdateAd,
                                imageService.saveAdsImage(multipartFile))));
    }

    @Override
    public ExtendedAd getExtendedAdsById(int id) {
        return mapper.entityToExtendedAdsDto(get(id));
    }

    @Override
    public Ad update(int id, CreateOrUpdateAd ads) {
        return mapper.entityToAdsDto(
                adRepository.save(get(id).setFieldsAndReturnEntity(ads)));
    }

    @Override
    public Ad uploadImage(int id, MultipartFile image) throws IOException {
        AdEntity adEntity = get(id);
        adEntity.setImage(imageService.saveAdsImage(image));
        adRepository.save(adEntity);
        return mapper.entityToAdsDto(adEntity);
    }

    @Override
    public Ads getAllAds() {
        List<AdEntity> entities = adRepository.findAll();
        return Ads.builder()
                .results(entities.stream()
                        .map(mapper::entityToAdsDto)
                        .collect(Collectors.toList()))
                .count(entities.size()).build();
    }

    @Override
    public Ads getAllMyAds(UserEntity userEntity) {
        List<AdEntity> adEntities = adRepository.findAllByAuthorUsername(userEntity.getUsername());
        return mapper.createAdsToEntity(adEntities);
    }
}
