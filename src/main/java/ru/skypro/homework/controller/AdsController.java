package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdService adService;
    private final UserService userService;


    public AdsController(AdService adService, UserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                    )),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            operationId = "addAds",
            summary = "addAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ad.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<Ad> addAd(@RequestPart CreateOrUpdateAd properties,
                                    @RequestPart MultipartFile image) throws IOException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(
                adService.create(userService.findUserEntityByLogin(userDetails.getUsername()),
                        properties, image)
        );
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getAdsId",
            summary = "getFullAd",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавлена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAd.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        ExtendedAd ad = adService.getExtendedAdsById(id);
        return ResponseEntity.ok(
                ad);
    }

    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Удалена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }

    )
    @DeleteMapping("/{id}")
    @PreAuthorize("@adServiceImpl.get(#id).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removeAd(@PathVariable Integer id, Authentication auth) throws IOException {
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Объявление обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd ads) {
        return ResponseEntity.ok(adService.update(id, ads));
    }

    @Operation(
            summary = "Получение объявлений пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Объявление обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(
                adService.getAllMyAds(
                        userService.findUserEntityByLogin(userDetails.getUsername()))
        );
    }

    @Operation(
            summary = "Обновление фотографии пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Фото обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> updateImage(@PathVariable Integer id, @RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.uploadImage(id, image));

    }

 }
