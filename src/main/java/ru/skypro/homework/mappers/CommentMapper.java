package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class CommentMapper {
    public Comment entityToCommentDto(CommentEntity entity) {
        return new Comment(entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), getMillis(entity.getCreatedAt()),
                entity.getPk(), entity.getText());
    }

    public CommentEntity createCommentToEntity(CreateOrUpdateComment createOrUpdateComment, AdEntity ad, UserEntity author) {
        return new CommentEntity(author, LocalDateTime.now(), createOrUpdateComment.getText(), ad);
    }

    private long getMillis(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(5)).toEpochMilli();
    }

}