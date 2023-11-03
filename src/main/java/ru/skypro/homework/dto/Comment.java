package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс - комментарий
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    /**
     * Уникальный идентификатор автора комментария
     */
    private int author;

    /**
     * Ссылка на изображение автора комментария
     */
    private String authorImage;

    /**
     * Имя автора комментария
     */
    private String authorFirstName;

    /**
     * Время создания комментария
     */
    private long createdAt;

    /**
     * Уникальный идентификатор комментария
     */
    private int pk;

    /**
     * Текст комментария
     */
    private String text;
}