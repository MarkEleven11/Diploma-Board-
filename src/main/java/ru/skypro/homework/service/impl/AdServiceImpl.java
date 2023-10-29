package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.FindNoEntityException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.lang.module.FindException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final ImageService imageService;
    private final AdMapper mapper;

    @Override
    public final AdEntity save(AdEntity model) {
        return adRepository.save(model);
    }

    public final void delete(Long id) {
        adRepository.deleteById(id);
    }

    @Override
    public final AdEntity get(Long id) {
        return adRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public Ad create(UserEntity userEntity,
                     CreateOrUpdateAd createOrUpdateAd,
                     MultipartFile multipartFile) {
        return mapper.entityToAdsDto(
                save(new AdEntity()
                        .setFieldsAndReturnEntity(userEntity, createOrUpdateAd,
                                imageService.saveAdsImage(multipartFile))));
    }

    @Override
    public ExtendedAd getExtendedAdsById(Long id) {
        return mapper.entityToExtendedAdsDto(get(id));
    }

    @Override
    public Ad update(Long id, CreateOrUpdateAd ads) {
        return mapper.entityToAdsDto(
                adRepository.save(get(id).setFieldsAndReturnEntity(ads)));
    }

    @Override
    public Ad uploadImage(Long id, MultipartFile image) {
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

    @Override
    public String deleteAd(UserEntity userEntity, Long id) {
        if (userEntity.getRole().equals(Role.ADMIN)) {
            delete(id);
            return "Объявление удалено администратором";
        } else {
            if (get(id).getAuthor().getId().equals(userEntity.getId())) {
                delete(id);
                return "Объявление удалено автором";
            } else {
                throw new FindNoEntityException("Вы не можете удалить объявление другого пользователя");
            }
        }
    }
}
