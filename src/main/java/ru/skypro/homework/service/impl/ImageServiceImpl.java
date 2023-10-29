package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    @Value("src/main/resources/static")
    private String imageDirectory;
    @Value("/images/user_images")
    private String usersImagesPath;
    @Value("/images/ad_images")
    private String adsImagesPath;

    @PostConstruct
    private void init() throws FileNotFoundException {
        Path path = Path.of(imageDirectory);
        Path path1 = Path.of(imageDirectory + usersImagesPath);
        Path path2 = Path.of(imageDirectory + adsImagesPath);
        Path path3 = Path.of(imageDirectory + "/images");
        try {
            if (Files.notExists(path)) {
                Files.createDirectory(path.toAbsolutePath());
            }
            if (Files.notExists(path3)) {
                Files.createDirectory(path3.toAbsolutePath());
            }
            if (Files.notExists(path1)) {
                Files.createDirectory(path1.toAbsolutePath());
            }
            if (Files.notExists(path2)) {
                Files.createDirectory(path2.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public String saveUserImage(MultipartFile file) {
        String filePathInStorage = usersImagesPath + File.separator + getNewFileName(file);
        File newFile = new File(imageDirectory + filePathInStorage);
        uploadFile(file, newFile);
        return filePathInStorage;
    }

    @Override
    public String saveAdsImage(MultipartFile file) {
        String filePathInStorage = adsImagesPath + File.separator + getNewFileName(file);
        File newFile = new File(imageDirectory + filePathInStorage);
        uploadFile(file, newFile);
        return filePathInStorage;
    }

    private String getNewFileName(MultipartFile file) {
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension = split[split.length - 1];
        return UUID.randomUUID() + "." + extension;
    }

    private void uploadFile(MultipartFile file, File newFile) {
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             FileOutputStream fos = new FileOutputStream(newFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            while (bis.read(buffer) > 0) {
                bos.write(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
