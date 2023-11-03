package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Класс - объявление
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ad {

    /**
     * Уникальный идентификатор объявления
     */
    private Integer pk;

    /**
     * Уникальный идентификатор автора объявления
     */
    private Integer author;

    /**
     * Путь к изображению объявления
     */
    private String image;

    /**
     * Цена объявления
     */
    private Integer price;

    /**
     * Заголовок объявления
     */
    private String title;

    /**
     * Переопределенный метод для сравнения объектов класса Ad.
     *
     * @param o Объект, с которым выполняется сравнение.
     * @return true, если объекты равны, и false в противном случае.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(pk, ad.pk) && Objects.equals(author, ad.author) && Objects.equals(image, ad.image) && Objects.equals(price, ad.price) && Objects.equals(title, ad.title);
    }

    /**
     * Переопределенный метод для вычисления хэш-кода объекта.
     *
     * @return Хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pk, author, image, price, title);
    }
}

