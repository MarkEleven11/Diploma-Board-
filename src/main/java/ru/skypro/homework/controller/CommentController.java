package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Методы работы с комментариями.")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final AdService adService;

    @GetMapping("/{id}/comments")
    @Operation(
            operationId = "getComments",
            summary = "getComments",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAds.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<Comment> getComments(@PathVariable("id") Integer id,
                                               @RequestBody Comment comment) {
        commentService.getEntity(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/{id}/comments")
    @Operation(
            operationId = "addComments",
            summary = "addComments",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = Comment.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<Comment> addComments(@PathVariable("id") int id,
                                               @NotNull @RequestBody CreateComment comment) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.add(adService.get(id), comment, userService.findUserEntityByUsername(userDetails.getUsername()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            operationId = "deleteComments",
            summary = "deleteComments",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PreAuthorize("@commentServiceImpl.getEntity(#adId).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteComments(@PathVariable("adId") Integer adId,
                                               @PathVariable("commentId") Integer commentId,
                                               Authentication auth) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(
            operationId = "updateComments",
            summary = "updateComments",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = Comment.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PreAuthorize("@commentServiceImpl.getEntity(#adId).author.username.equals(#auth.name) or hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComments(
            @PathVariable("commentId") Integer commentId,
            @RequestBody Comment comment, @PathVariable String adId,
            Authentication auth) {
        commentService.update(commentId, comment);
        return ResponseEntity.ok(comment);
    }
}