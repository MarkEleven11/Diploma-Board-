package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.exceptions.FindNoEntityException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;
    @Value("${path.to.images}")
    private String imageDirectory;

    @Override
    public ImageEntity saveImage(MultipartFile image) throws IOException {
        ImageEntity entity = repository.save(new ImageEntity());
        Path filePath = getPath(entity);
        Files.createDirectories(filePath.getParent());
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return entity;
    }

    @Override
    public byte[] getImage(long id) throws IOException {
        return Files.readAllBytes(getPath(getEntity(id)));
    }

    @Override
    public ImageEntity getEntity(long id) {
        return repository.findById(id).orElseThrow(() -> new FindNoEntityException("изображение"));
    }

    @Override
    public void deleteImage(ImageEntity image) throws IOException {
        Files.deleteIfExists(getPath(image));
        repository.delete(image);
    }

    private Path getPath(ImageEntity image) {
        return Path.of(imageDirectory, String.valueOf(image.getId()));
    }
}
