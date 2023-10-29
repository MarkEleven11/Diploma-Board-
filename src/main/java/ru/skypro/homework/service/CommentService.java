package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

public interface CommentService {

    Comments getComments(Long id);

    Comment add(AdEntity adEntity, CreateOrUpdateComment comment, UserEntity userEntity);

    void delete(Long commentId);

    Comment update(Long commentId, CreateOrUpdateComment comment);

    CommentEntity getEntity(Long commentId);

    Comments findCommentsByAdId(Long id);
}
