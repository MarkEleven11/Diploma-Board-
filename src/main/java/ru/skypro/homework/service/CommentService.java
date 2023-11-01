package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

public interface CommentService {

    Comments getComments(int id);

    Comment add(int adId, CreateOrUpdateComment text);

    void delete(int commentId);

    Comment update(int commentId, CreateOrUpdateComment comment);

    CommentEntity getEntity(int commentId);

    Comments findCommentsByAdId(int id);
}
