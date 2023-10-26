package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/images")
public class ImageController {

    private String imagesPath;
    private String adsImagesPath;
    private String usersImagesPath;

    @GetMapping("/ads/{imageId}")
    public byte[] getAdImage(@PathVariable String imageId) throws IOException {
        return Files.readAllBytes(Path.of(imagesPath + adsImagesPath + File.separator + imageId));
    }

    @GetMapping("/users/{inageId}")
    public byte[] getUserImage(@PathVariable String imageId) throws IOException {
        return Files.readAllBytes(Path.of(imagesPath + usersImagesPath + File.separator + imageId));
    }
}
