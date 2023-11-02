package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

/**
 * Репозиторий для доступа к данным объявлений (AdEntity) в базе данных
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    /**
     * Метод для поиска сущностей объявлений по имени пользователя
     *
     * @param username Имя пользователя (логин)
     * @return Список сущностей объявлений, принадлежащих пользователю
     */
    List<AdEntity> findAllByAuthorUsername(String username);



}
