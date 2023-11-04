package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
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
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

/**
 * Класс контроллер для работы с комментариями
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Методы работы с комментариями.")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    /**
     * Метод для получения комментариев к объявлению по его идентификатору
     *
     * @param id Идентификатор объявления
     * @return Ответ с массивом комментариев в формате JSON и кодом состояния HTTP 200 (OK)
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если комментарии не найдены, вернется код состояния HTTP 404 (Not Found)
     */
    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получение всех комментариев",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class))
                    }),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    public ResponseEntity<Comments> getComments(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    /**
     * Метод для добавления комментария
     *
     * @param id Идентификатор объявления
     * @param comment Объект с данными о комментарии
     * @return Ответ с созданным комментарием в формате JSON и кодом состояния HTTP 201 (Created)
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если комментарий не найден, вернется код состояния HTTP 404 (Not Found)
     */
    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавление комментария",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Created",
                            content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class))
                    }),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )

            }
    )
    public ResponseEntity<Comment> addComments(@PathVariable("id") Integer id,
                                               @NotNull @RequestBody CreateOrUpdateComment comment) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment commentNew = commentService.add(id, comment, userService.findUserEntityByLogin(userDetails.getUsername()));
        return ResponseEntity.ok().body(commentNew);
    }

    /**
     * Метод для удаления комметнтария
     *
     * @param adId Идентификатор объявления
     * @param commentId Идентификатор комментария
     * @return Ответ с кодом состояния HTTP 200 (OK) после успешного удаления
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden)
     * Если комментарий не найден, вернется код состояния HTTP 404 (Not Found)
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Удаление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
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
    @PreAuthorize("@commentServiceImpl.getEntity(#commentId).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteComments(@PathVariable Integer adId,
                                                     @PathVariable Integer commentId,
                                                     Authentication auth) {
        commentService.delete(commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Метод для обновления комментария
     *
     * @param adId Идентификатор объявления
     * @param commentId Идентификатор комментария
     * @param createOrUpdateComment Объект с обновленными данными комментария
     * @return Ответ с обновленным комментарием в формате JSON и кодом состояния HTTP 201 (Created)
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized)
     * Если комментарий не найден, вернется код состояния HTTP 404 (Not Found)
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden)
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Обновление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class)
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
    @PreAuthorize("@commentServiceImpl.getEntity(#commentId).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComments(@PathVariable Integer adId,
                                                  @PathVariable Integer commentId,
                                                  @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                  Authentication auth) {
        return ResponseEntity.ok(
                commentService.update(adId, commentId, createOrUpdateComment)
        );
    }
}