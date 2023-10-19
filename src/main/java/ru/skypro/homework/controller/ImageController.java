package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/images")
public class ImageController {

    @Value("${path.to.image}")
    private String imagesPath;
    @Value("${path.to.image.ads}")
    private String adsImagesPath;
    @Value("${path.to.image.users}")
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
