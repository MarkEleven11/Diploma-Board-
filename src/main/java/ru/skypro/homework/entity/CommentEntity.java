package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
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
}
