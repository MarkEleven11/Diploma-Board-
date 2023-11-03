package ru.skypro.homework.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

/**
 * Класс контроллер для работы с пользователем
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Методы работы с пользователем.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для обновления пароляпользователя
     *
     * @param newPassword Данные о новом пароле
     * @return Ответ с кодом состояния HTTP 201 (Created) после успешного обновления пароля.
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized).
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden).
     */
    @PostMapping("/set_password")
    @Operation(
            summary = "Обновление пароля пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HttpStatus.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            }
    )
    public ResponseEntity<HttpStatus> setPassword(@RequestBody NewPassword newPassword) {
        userService.updateUserPassword(newPassword, (UserDetails)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Метод для получения информации о текущем пользователе
     *
     * @return Данные о пользователе в формате JSON и кодом соятояния 200 (OK)
     * В случае отсутствия авторизации пользователя возвращает код состояния HTTP 401 (Unauthorized)
     */
    @GetMapping("/me")
    @Operation(
            summary = "Получение информации о пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(
                userService.getUser((UserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal()));
    }

    /**
     * Метод для обновления информации о текущем пользователе
     *
     * @param updateUser Объект с обновленными данными пользователя
     * @return Ответ с обновленной информацией о пользователе и кодом состояния HTTP 200 (OK)
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     */
    @PatchMapping("/me")
    @Operation(
            summary = "Обновление информации о пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class))
                    }),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(
                userService.createOrUpdate(
                        (UserDetails)
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getPrincipal(), updateUser));
    }

    /**
     * Метод для обновления изображения пользователя
     *
     * @param image Новое изображение пользователя
     * @return Ответ с кодом состояния HTTP 201 (Created) после успешного обновления аватара
     * @throws IOException в случае отсутствия авторизации пользователя возвращает код состояния HTTP 401 (Unauthorized)
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновление изображения пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<HttpStatus> updateUserImage(@RequestParam MultipartFile image) throws IOException {
        userService.updateImage(
                (UserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal(),
                image);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}