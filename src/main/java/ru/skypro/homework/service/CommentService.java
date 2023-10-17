package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

public interface CommentService {

    ResponseWrapperComment getComments(int id);

    Comment add(AdEntity adEntity, CreateComment comment, UserEntity userEntity);

    void delete(int commentId);

    Comment update(int commentId, Comment newComment);

    CommentEntity getEntity(int commentId);
}
