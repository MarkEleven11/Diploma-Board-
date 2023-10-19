package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.service.AdService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    @GetMapping
    @Operation(
            operationId = "getAllAds",
            summary = "getAllAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = Ads.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            operationId = "addAds",
            summary = "addAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = Ads.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Ads> addAd(@RequestPart CreateAds properties, @RequestPart MultipartFile image,
                                     UserEntity userEntity) throws IOException {
        return ResponseEntity.ok(adService.add(properties, image, userEntity));
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getAdsId",
            summary = "getFullAd",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = FullAds.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FullAds> getAds(@PathVariable Long id) {
        return ResponseEntity.ok(adService.getFullAdsById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@adServiceImpl.getEntity(#id).author.email.equals(#auth.name)")
    public ResponseEntity<?> removeAd(@PathVariable int id, Authentication auth) throws IOException {
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@adServiceImpl.getEntity(#id).author.email.equals(#auth.name) or hasAuthority('UPDATE_ANY_AD')")
    public ResponseEntity<Ads> updateAds(@PathVariable Long id, @RequestBody CreateAds ads, Authentication auth) {
        return ResponseEntity.ok(adService.update(id, ads));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(Authentication auth) {
        return ResponseEntity.ok(adService.getAllMyAds(auth.name()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestPart MultipartFile image) throws IOException {
        adService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

 }
