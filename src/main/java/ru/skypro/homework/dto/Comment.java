package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private int pk;
    private String text;
}