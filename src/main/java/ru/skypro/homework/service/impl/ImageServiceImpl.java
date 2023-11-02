package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${path.to.image.users}")
    private String usersImagesPath;
    @Value("${path.to.image.ads}")
    private String adsImagesPath;

    private final AdRepository repository;

    private final UserRepository userRepository;

    @PostConstruct
    private void init() throws FileNotFoundException {
        Path path1 = Path.of(usersImagesPath);
        Path path2 = Path.of(adsImagesPath);
        try {
            if (Files.notExists(path1)) {
                Files.createDirectories(path1);
            }
            if (Files.notExists(path2)) {
                Files.createDirectories(path2);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public String saveUserImage(MultipartFile file) throws IOException {
        File newFile = Paths.get(usersImagesPath, getNewFileName(file)).toFile();
        uploadFile(file, newFile);
        return newFile.getPath();
    }

    @Override
    public String saveAdsImage(MultipartFile file) throws IOException {
        File newFile =Paths.get(adsImagesPath, getNewFileName(file)).toFile();
        uploadFile(file, newFile);
        return newFile.getPath();
    }

    private String getNewFileName(MultipartFile file) {
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension = split[split.length - 1];
        return UUID.randomUUID() + "." + extension;
    }

    private void uploadFile(MultipartFile file, File newFile) throws IOException {
        Files.write(newFile.toPath(), file.getBytes());
    }

    @Override
    public void deleteImage(String image) throws IOException {
        Files.deleteIfExists(Paths.get(image));
    }

    @Override
    public byte[] loadImage(int adId) throws IOException {
        AdEntity entity = repository.findById(adId).orElseThrow();
        return Files.readAllBytes(Paths.get(entity.getImage()));
    }

    @Override
    public byte[] loadAvatar(int userId) throws IOException {
        UserEntity entity = userRepository.findById(userId).orElseThrow();
        return Files.readAllBytes(Paths.get(entity.getImage()));
    }
}
