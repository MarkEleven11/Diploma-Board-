package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * Класс контроллер для работы с изображениями
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    /**
     * Метод для получения изображения объявления
     *
     * @param adId Идентификатор объявления
     * @return Массив байтов с изображением и код состояния HTTP 200 (OK)
     * @throws IOException При ошибке чтения изображения
     */
    @GetMapping("/ads/{adId}")
    public byte[] getAdImage(@PathVariable int adId) throws IOException {
        return imageService.loadImage(adId);
    }

    /**
     * Метод для получения изображения пользователя (аватар)
     *
     * @param userId Идентификатор пользователя
     * @return Массив байтов с изображением и код состояния HTTP 200 (OK)
     * @throws IOException При ошибке чтения изображения
     */
    @GetMapping("/users/{userId}")
    public byte[] getUserImage( @PathVariable int userId) throws IOException {
        return imageService.loadAvatar(userId);
    }
}
