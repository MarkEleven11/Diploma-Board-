package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Класс, представляющий расширенное объявление
 * Является расширением класса {@link Ad}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtendedAd {

    /**
     * Уникальный идентификатор объявления
     */
    private Integer pk;

    /**
     * Имя автора объявления
     */
    private String authorFirstName;

    /**
     * Фамилия автора объявления
     */
    private String authorLastName;

    /**
     * Описание объявления
     */
    private String description;

    /**
     * Электронная почта автора объявления
     */
    private String email;

    /**
     * Ссылка на изображение объявления
     */
    private String image;

    /**
     * Номер телефона автора объявления
     */
    private String phone;

    /**
     * Цена объявления
     */
    private Integer price;

    /**
     * Заголовок объявления
     */
    private String title;

    /**
     * Переопределенный метод для сравнения объектов класса ExtendedAd.
     *
     * @param o Объект, с которым выполняется сравнение.
     * @return true, если объекты равны, и false в противном случае.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedAd that = (ExtendedAd) o;
        return Objects.equals(pk, that.pk) && Objects.equals(authorFirstName, that.authorFirstName) && Objects.equals(authorLastName, that.authorLastName) && Objects.equals(description, that.description) && Objects.equals(email, that.email) && Objects.equals(image, that.image) && Objects.equals(phone, that.phone) && Objects.equals(price, that.price) && Objects.equals(title, that.title);
    }

    /**
     * Переопределенный метод для вычисления хэш-кода объекта.
     *
     * @return Хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pk, authorFirstName, authorLastName, description, email, image, phone, price, title);
    }
}