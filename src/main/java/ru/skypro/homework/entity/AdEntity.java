package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.CreateOrUpdateAd;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
/**
 * Класс, представляющий сущность объявления в приложении
 * Класс соответствует таблице "ads" в базе данных и используется
 * для хранения информации о объявлениях пользователей
 */
@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdEntity {
    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity author;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    private String title;
    private int price;
    private String description;
    @Column(name = "image")
    private String image;

    /**
     * Контроллер для создания сущности объявления
     *
     * @param author Данные об авторе объявления
     * @param title Заголовок объявления
     * @param price Цена объявления
     * @param description Описание объявления
     */
    public AdEntity(UserEntity author, String title, int price, String description) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    /**
     * Метод для получения ссылке к изображению объявления
     * @return Строка-ссылка на изображение
     */
    public String getImagePath() {
        return image == null ? null : "/images/ads/" + id;
    }

    /**
     * Метод для установки полей объекта AdEntity из DTO и пути к изображению.
     *
     * @param userEntity Сущность пользователя, создавшего объявление.
     * @param dto DTO с информацией об объявлении.
     * @param image Путь к изображению.
     * @return Обновленная сущность объявления.
     */
    public final AdEntity setFieldsAndReturnEntity(UserEntity userEntity,
                                                   CreateOrUpdateAd dto,
                                                   String image) {
        this.setAuthor(userEntity);
        this.setDescription(dto.getDescription());
        this.setPrice(dto.getPrice());
        this.setTitle(dto.getTitle());
        this.setImage(image);
        return this;
    }

    /**
     * Метод для установки полей объекта AdEntity из DTO.
     *
     * @param createOrUpdateAd DTO с информацией об объявлении.
     * @return Обновленная сущность объявления.
     */
    public final AdEntity setFieldsAndReturnEntity(CreateOrUpdateAd createOrUpdateAd) {
        this.setDescription(createOrUpdateAd.getDescription());
        this.setTitle(createOrUpdateAd.getTitle());
        this.setPrice(createOrUpdateAd.getPrice());
        return this;
    }

    /**
     * Переопределенный метод для сравнения объектов класса AdEntity.
     *
     * @param o Объект, с которым выполняется сравнение.
     * @return true, если объекты равны, и false в противном случае.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id) &&
                Objects.equals(createdAt, adEntity.createdAt) &&
                Objects.equals(image, adEntity.image) &&
                Objects.equals(price, adEntity.price) &&
                Objects.equals(title, adEntity.title) &&
                Objects.equals(description, adEntity.description);
    }

    /**
     * Переопределенный метод для вычисления хэш-кода объекта.
     *
     * @return Хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, image, price, title, description);
    }

}