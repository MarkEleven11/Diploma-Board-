package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Класс - список комментариев
 */
@AllArgsConstructor
@Data
@Builder
public class Comments {

    /**
     * Количество комментариев в списке
     */
    private Integer count;

    /**
     * Список комментариев
     */
    private List<Comment> results;
}