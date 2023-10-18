package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

public interface ImageService {

    ImageEntity saveImage(MultipartFile image) throws IOException;

    byte[] getImage(long id) throws IOException;

    void deleteImage(ImageEntity entity) throws IOException;

    ImageEntity getEntity(long id);
}
