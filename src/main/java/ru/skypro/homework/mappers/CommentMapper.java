package ru.skypro.homework.mappers;

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

@Component
public class CommentMapper {
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

    public CommentEntity createCommentToEntity(CreateOrUpdateComment createOrUpdateComment, AdEntity ad) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(createOrUpdateComment.getText());
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setAuthor(ad.getAuthor());
        commentEntity.setAd(ad);
        return commentEntity;
    }

    public Comments entityToComments(List<CommentEntity> entities) {
        return Comments.builder()
                .results(entities
                        .stream().map(this::entityToCommentDto)
                        .collect(Collectors.toList()))
                .count(entities.size())
                .build();
    }

}