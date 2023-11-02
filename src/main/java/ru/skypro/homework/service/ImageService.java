package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Сервис для обработки изображений
 */
public interface ImageService {

    /**
     * Метод для сохранения изображения-аватара пользователя
     *
     * @param file Изображение для сохранения
     * @return Сохраненное изображение
     * @throws IOException Возникает в случае ошибки в процессе загрузки/сохранения
     */
    String saveUserImage(MultipartFile file) throws IOException;

    /**
     * Метод для сохранения изображения объявления
     *
     * @param file Изображение для сохранения
     * @return Сохраненное изображение
     * @throws IOException Возникает в случае ошибки в процессе загрузки/сохранения
     */
    String saveAdsImage(MultipartFile file) throws IOException;

    /**
     * Метод для удаления изображения по ссылке на него
     *
     * @param image Ссылка - путь к изображению
     * @throws IOException Возникает в случае отсутствия изображения, неправильно указанной ссылки
     */
    void deleteImage(String image) throws IOException;

    /**
     * Метод для загрузки изображения объявления
     *
     * @param adId Идентификатор объявления
     * @return Массив байтов с изображением
     * @throws IOException Возникает в случае ошибки в процессе загрузки изображения
     */
    byte[] loadImage(int adId) throws IOException;

    /**
     * Метод для загрузки изображения-аватара пользователя
     *
     * @param userId Идентификатор пользователя
     * @return Массив байтов с изображением
     * @throws IOException Возникает в случае ошибки в процессе загрузки изображения
     */
    byte[] loadAvatar(int userId) throws IOException;

}
