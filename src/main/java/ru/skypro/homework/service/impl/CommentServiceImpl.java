package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.FindNoEntityException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final AdService adService;
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    private final DateTimeFormatter localFormatter = new DateTimeFormatterFactory(" dd MMMM yyyy в HH:mm:ss)").createDateTimeFormatter();

    @Override
    public ResponseWrapperComment getComments(int id) {
        List<Comment> result = new LinkedList<>();
        commentRepository.findAllByAd_Pk(id).forEach(entity -> result.add(mapper.entityToCommentDto(entity)));
        return new ResponseWrapperComment(result.size(), result);
    }

    @Override
    public Comment add(AdEntity adEntity, CreateComment comment, UserEntity userEntity) {
        CommentEntity entity = mapper.createCommentToEntity(comment, adEntity, userEntity);
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(int commentId, Comment comment) {
        CommentEntity entity = getEntity(commentId);
        entity.setText(comment.getText() + LocalDateTime.now().format(localFormatter));
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public CommentEntity getEntity(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindNoEntityException("комментарий"));
    }
}
