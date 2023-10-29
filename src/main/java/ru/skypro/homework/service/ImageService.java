package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveUserImage(MultipartFile file);

    String saveAdsImage(MultipartFile file);
}
