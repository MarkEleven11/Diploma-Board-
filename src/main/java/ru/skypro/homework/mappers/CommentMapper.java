package ru.skypro.homework.mappers;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс представляет собой компонент, отвечающий за преобразование сущностей и DTO,
 * связанных с комментариями (Comment), в различные форматы и обратно
 *
 * <p>Класс реализует интерфейс {@code Mapper}, который определяет методы для преобразования данных.
 * В частности, этот класс выполняет преобразование сущностей {@code CommentEntity} в DTO {@code Comment}
 * и {@code Comments}
 *
 * @see Mapper
 * @see Comment
 * @see Comments
 * @see CommentEntity
 */
@Component
public class CommentMapper {

    /**
     * Метод выполняет преобразование сущности комментария {@code CommentEntity} в DTO комментария {@code Comment}
     *
     * @param entity Сущность комментария
     * @return Объект DTO комментария
     */
    public Comment entityToCommentDto(CommentEntity entity) {
        long createdAtInMillisecondsSinceEpoch = entity.getCreatedAt().toInstant(ZoneOffset.of("+03:00")).toEpochMilli();
        Comment comment = new Comment();
        comment.setPk(entity.getId());
        comment.setAuthor(entity.getAuthor().getId());
        comment.setAuthorImage(entity.getAuthor().getImagePath());
        comment.setText(entity.getText());
        comment.setAuthorFirstName(entity.getAuthor().getFirstName());
        comment.setCreatedAt(createdAtInMillisecondsSinceEpoch);
        return comment;
    }

    /**
     * Метод выполняет преобразование DTO созданного или обновленного комментария {@code CreateOrUpdateComment}
     * в сущность {@code CommentEntity}
     *
     * @param createOrUpdateComment Сущность комментария
     * @param ad Сущность
     * @return Объект сущность комментария
     */
    public CommentEntity createCommentToEntity(CreateOrUpdateComment createOrUpdateComment, AdEntity ad) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setAuthor(ad.getAuthor());
        commentEntity.setAd(ad);
        return commentEntity;
    }

    /**
     * Метод выполняет преобразование списка сущностей комментариев {@code CommentEntity} в DTO комментариев {@code Comments},
     * включая количество комментариев и сами комментарии
     *
     * @param entities список сущностей комментариев
     * @return объект DTO комментарий
     */
    public Comments entityToComments(List<CommentEntity> entities) {
        return Comments.builder()
                .results(entities
                        .stream().map(this::entityToCommentDto)
                        .collect(Collectors.toList()))
                .count(entities.size())
                .build();
    }

}