package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий данные для создания или обновления объявления
 * Является расширением класса {@link Ad}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAd {

    /**
     * Описание объявления
     */
    private String description;

    /**
     * Цена объявления
     */
    private Integer price;

    /**
     * Заголовок объявления
     */
    private String title;
}