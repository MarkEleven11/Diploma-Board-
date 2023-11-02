package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Класс - список объявлений
 */
@Data
@AllArgsConstructor
@Builder
public class Ads {

    /**
     * Количество объявлений в списке
     */
    private Integer count;

    /**
     * Список объявлений
     */
    private List<Ad> results;
}