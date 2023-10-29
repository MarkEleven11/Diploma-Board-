package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity author;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "comments_text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity ad;

    public CommentEntity(UserEntity author, LocalDateTime createdAt, String text, AdEntity ad) {
        this.author = author;
        this.createdAt = createdAt;
        this.text = text;
        this.ad = ad;
    }

    public final CommentEntity setFieldsAndReturnEntity(
            UserEntity user, AdEntity adEntity, CreateOrUpdateComment createOrUpdateComment) {
        this.setText(createOrUpdateComment.getText());
        this.setAuthor(user);
        this.setAd(adEntity);
        this.setCreatedAt(LocalDateTime.now());
        return this;
    }

    public final CommentEntity setFieldsAndReturnEntity(CreateOrUpdateComment createOrUpdateComment) {
        this.setText(createOrUpdateComment.getText());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, text);
    }
}
