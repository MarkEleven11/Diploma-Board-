package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;

/**
 * Сервис для работы с комментариями
 */
public interface CommentService {

    /**
     * Метод для получения всех комментариев для указанного объявления по его идентификатору
     *
     * @param id Идентификатор объявления
     * @return Комментарии для указанного объявления
     */
    Comments getComments(int id);

    /**
     * Метод для создания нового комментария на основе предоставленной модели
     *
     * @param id Идентификатор объявления
     * @param comment Модель данных комментария для создания
     * @return Созданный комментарий
     */
    Comment add(int id, CreateOrUpdateComment comment);

    /**
     * Метод для удаления комментария по указанному идентификатору
     *
     * @param commentId Идентификатор комментария для удаления
     */
    void delete(int commentId);

    /**
     * Метод для обновления существующего комментария по указанному идентификатору
     *
     * @param adId Идентификатор объявления
     * @param commentId  Идентификатор комментария для обновления
     * @param comment Модель данных комментария для обновления
     * @return Обновленный комментарий
     */
    Comment update(int adId, int commentId, CreateOrUpdateComment comment);

    /**
     * Метод для получения сущности комментария
     *
     * @param commentId Идентификатор комментария
     * @return Сущность комментария
     */
    CommentEntity getEntity(int commentId);
}
