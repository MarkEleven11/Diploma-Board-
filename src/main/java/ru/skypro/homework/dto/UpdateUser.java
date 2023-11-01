package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUser {

    private String firstName;
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}
