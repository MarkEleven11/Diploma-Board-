package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.FindNoEntityException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    private final DateTimeFormatter localFormatter = new DateTimeFormatterFactory(" dd MMMM yyyy в HH:mm:ss)").createDateTimeFormatter();

    public final CommentEntity save(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    @Override
    public Comments getComments(Long id) {
        return mapper.entityToComments(
                commentRepository.findAllByAdId(id));
    }

    @Override
    public Comment add(AdEntity adEntity, CreateOrUpdateComment comment, UserEntity userEntity) {
        return mapper.entityToCommentDto(
                save(
                        new CommentEntity().setFieldsAndReturnEntity(userEntity, adEntity, comment)));
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(Long id, CreateOrUpdateComment comment) {
        return mapper.entityToCommentDto(
                commentRepository.save(
                        getEntity(id).setFieldsAndReturnEntity(comment)));
    }

    @Override
    public CommentEntity getEntity(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindNoEntityException("комментарий"));
    }

    @Override
    public Comments findCommentsByAdId(Long id) {
        return mapper.entityToComments(
                commentRepository.findAllByAdId(id));
    }
}
