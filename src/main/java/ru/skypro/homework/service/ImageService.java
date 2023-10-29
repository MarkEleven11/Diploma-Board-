package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String saveUserImage(MultipartFile file);

    String saveAdsImage(MultipartFile file);

    void deleteImage(String image) throws IOException;
}
