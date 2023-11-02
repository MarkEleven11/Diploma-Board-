package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

/**
 * Сервис для управления объявлениями
 */
public interface AdService {

    /**
     * Метод для сохранения сущности объявления
     *
     * @param model Сущность объявления
     * @return Сохраненная сущность объявления
     */
    AdEntity save(AdEntity model);

    /**
     * Метод для получения сущности объявления
     *
     * @param id Идентификатор объявления
     * @return Сущность объявления
     */
    AdEntity get(int id);

    /**
     * Метод для получения всех объявлений
     *
     * @return Список всех объявлений
     */
    Ads getAllAds();

    /**
     * Метод для создания DTO объявления
     *
     * @param userEntity Сущность пользователя, создателя объявления
     * @param createOrUpdateAd Созданный объект DTO
     * @param multipartFile Файл - изображение для объявления
     * @return Созданное объявление
     * @throws IOException Возникает в случае ошибки в процессе загрузки/создания объявления
     */
    Ad create(UserEntity userEntity,
              CreateOrUpdateAd createOrUpdateAd,
              MultipartFile multipartFile) throws IOException;

    /**
     * Метод для получения расширенной информации об объявлении
     *
     * @param id Идентификатор объявления
     * @return Расширенная информация об объявлении
     */
    ExtendedAd getExtendedAdsById(int id);

    /**
     * Метод для получения списка объявлений, принадлежащих пользователю
     *
     * @param userEntity Сущность пользователя
     * @return Список объявлений, принадлежащих пользователю
     */
    Ads getAllMyAds(UserEntity userEntity);

    /**
     * Метод для обновления объявления
     *
     * @param id Идентификатор объявления
     * @param ads Модель данных для обновления объявления
     * @return Обновленное объявление
     */
    Ad update(int id, CreateOrUpdateAd ads);

    /**
     * Метод для обновления изображения объявления
     *
     * @param id Идентификатор объявления
     * @param image Файл - изображение для обновления
     * @return Обновленное изображение в объявлении
     * @throws IOException Возникает в случае ошибки загрузки/обработки изображения
     */
    Ad uploadImage(int id, MultipartFile image) throws IOException;

    /**
     * Метод для удаления объявления по идентификатору
     *
     * @param id Идентификатор объявления
     * @throws IOException Возникает в случае отсутствия информации об объявлении в базе данных
     */
    void delete(int id) throws IOException;

}
