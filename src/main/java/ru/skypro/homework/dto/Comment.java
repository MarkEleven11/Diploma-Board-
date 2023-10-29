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
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pk;
    private String text;
}