import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;

@Component
public class CommentMapper {
    public Comment entityToCommentDto(CommentEntity entity) {
        return new Comment(entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), getMillis(entity.getCreatedAt()),
                entity.getPk(), entity.getText());
    }

    public CommentEntity createCommentToEntity(CreateComment createComment, AdEntity ad, UserEntity author) {
        return new CommentEntity(author, LocalDateTime.now(), createComment.getText(), ad);
    }

    private long getMillis(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(5)).toEpochMilli();
    }

}