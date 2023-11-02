package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий данные для создания или обновления комментария
 * Является расширением класса {@link Comment}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateComment {

    /**
     * Текст комментария
     */
    private String text;
}