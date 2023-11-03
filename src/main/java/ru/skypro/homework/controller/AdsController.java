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

/**
 * Контроллер для работы с объявлениями
 */
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

    /**
     * Метод для получения всх объявлений
     *
     * @return Ответ в виде массива объявлений в формате JSON и кодом состояния HTTP 200 (OK)
     */
    @GetMapping
    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                            )
                    )
            }
    )
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
     * Метод для добавления объявления
     *
     * @param properties - данные для создания нового объявления
     * @param image - изображение в объявлении
     * @return Добавленное объявление в формате JSON и кодом состояния HTTP 201 (Created)
     * @throws IOException в случае, если пользователь не авторизован HTTP 401 (Unauthorized)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Created",
                            content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ad.class))
                    }),
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

    /**
     * Метод для получения информации об объявлении по идентификатору
     *
     * @param id - уникальный идентификатор объявления
     * @return Ответ с информацией об объявлении в формате JSON и кодом состояния HTTP 200 (OK)
     * В случае отсутствия авторизации пользователя вернется код состояния HTTP 401 (Unauthorized)
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAd.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        ExtendedAd ad = adService.getExtendedAdsById(id);
        return ResponseEntity.ok(ad);
    }

    /**
     * Метод, удаляющий объявление по идентификатору объявления
     *
     * @param id Уникальный идентификатор объявления
     * @param auth Аутентификатор пользователя, проверяющий является ли он вдалельцем объявления или администратором
     * @return Ответ с кодом состояния HTTP 201 (OK) после успешного удаления
     * @throws IOException
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden)
     * Если объявление не найдено, вернется код состояния HTTP 404 (Not Found)
     */
    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }

    )
    @DeleteMapping("/{id}")
    @PreAuthorize("@adServiceImpl.get(#id).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> removeAd(@PathVariable Integer id, Authentication auth) throws IOException {
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Метод обновляющий информацию об объявлении
     *
     * @param id Уникальный идентификатор объявления
     * @param ads Обновленная информация об объявлении
     * @return Ответ с кодом состояния HTTP 201 (Created) после успешного обновления
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden)
     * Если объявление не найдено, вернется код состояния HTTP 404 (Not Found)
     */
    @Operation(
            summary = "Обновление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("@adServiceImpl.get(#id).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd ads,
                                        Authentication auth) {
        return ResponseEntity.ok(adService.update(id, ads));
    }

    /**
     * Метод для получения объявлений определенного пользователя
     *
     * @return Ответ с массивом объявлений в формате JSON и кодом состояния HTTP 201 (Created)
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     */
    @Operation(
            summary = "Получение объявлений пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
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

    /**
     * Метод для обновления фотографии объявления
     *
     * @param id Уникальный идентификатор объявления
     * @param image Изображение для обновления
     * @return Ответ с обновленным объявлением в формате JSON и кодом состояния HTTP 201 (Created)
     * @throws IOException
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden)
     * Если объявление не найдено, вернется код состояния HTTP 404 (Not Found)
     */
    @Operation(
            summary = "Обновление фотографии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> updateImage(@PathVariable Integer id, @RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.uploadImage(id, image));

    }

 }
