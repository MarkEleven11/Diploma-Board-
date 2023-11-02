package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String saveUserImage(MultipartFile file) throws IOException;

    String saveAdsImage(MultipartFile file) throws IOException;

    void deleteImage(String image) throws IOException;

    byte[] loadImage(int adId) throws IOException;

    byte[] loadAvatar(int userId) throws IOException;

}
