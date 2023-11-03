package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exceptions.FindNoEntityException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

/**
 * Реализация интерфейса CommentService для управления комментариями
 */
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final AdService adService;

    public final CommentEntity save(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    @Override
    public Comments getComments(int id) {
        return mapper.entityToComments(
                commentRepository.findAllByAdId(id));
    }

    @Override
    public Comment add(int id, CreateOrUpdateComment comment) {
        AdEntity ad = adService.get(id);
        CommentEntity newEntity = mapper.createCommentToEntity(comment, ad);
        commentRepository.save(newEntity);
        return mapper.entityToCommentDto(newEntity);
    }

    @Override
    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(int adId, int commentId, CreateOrUpdateComment comment) {
        CommentEntity commentEntity = commentRepository.findCommentEntityByAdAndId(adService.get(adId), commentId);
        commentEntity.setText(comment.getText());
        save(commentEntity);
        return mapper.entityToCommentDto(commentEntity);
    }

    @Override
    public CommentEntity getEntity(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindNoEntityException("Обращение к несуществующему комментарию"));
    }
}
