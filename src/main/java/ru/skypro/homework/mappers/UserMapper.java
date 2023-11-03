package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

/**
 * Класс представляет собой маппер для преобразования данных между сущностями пользователей
 * ({@code UserEntity}) и объектами передачи данных о пользователях ({@code User}) в приложении.
 * Класс выполняет конвертацию данных о пользователе из формата сущности в формат DTO и обратно.
 *
 * @see User
 * @see UserEntity
 */
@Component
public class UserMapper {

    /**
     * Метод преобразует сущность пользователя ({@code UserEntity}) в объект передачи данных о пользователе ({@code User})
     *
     * @param entity Сущность пользователя
     * @return Объект DTO о пользователе ({@code User})
     */
    public User entityToUserDto(UserEntity entity) {
        return new User(entity.getId(),
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getImagePath());
    }
}
