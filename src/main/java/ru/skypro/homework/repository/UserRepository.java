package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

/**
 * Репозиторий для доступа к данным пользователей (UserEntity) в базе данных
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Метод для поиска сущности пользователя по имени пользователя (логину)
     *
     * @param username Имя пользователя (логин)
     * @return Сущность пользователя
     */
    UserEntity findUserEntityByUsername(String username);

    /**
     * Метод для проверки существования пользователя в базе данных
     *
     * @param username Имя пользователя (логин)
     * @return "true" при наличии пользователя в базе данных, "false" при отсутствии
     */
    boolean existsUserEntityByUsername(String username);

}
