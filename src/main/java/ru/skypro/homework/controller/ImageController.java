package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/ads/{adId}")
    public byte[] getAdImage(@PathVariable int adId) throws IOException {
        return imageService.loadImage(adId);
    }

    @GetMapping("/users/{userId}")
    public byte[] getUserImage( @PathVariable int userId) throws IOException {
        return imageService.loadAvatar(userId);
    }
}
