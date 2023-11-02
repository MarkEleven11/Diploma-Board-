package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

/**
 * Репозиторий для доступа к данным комментариев (CommentEntity) в базе данных
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {


    /**
     * Метод для поиска сущностей комментариев по идентификатору пользователя
     *
     * @param id Идентификатор пользователя
     * @return Список сущностей комментариев, принадлежащих пользователю
     */
    List<CommentEntity> findAllByAdId(int id);

    /**
     * Метод для поиска сущностей комментариев по сущности объявления и идентификатору комментария
     *
     * @param ad Сущность объявления
     * @param commentId Идентификатор сущности комментария
     * @return Сущность комментария
     */
    CommentEntity findCommentEntityByAdAndId(AdEntity ad, int commentId);
}
