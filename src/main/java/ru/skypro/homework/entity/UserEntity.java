package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    public UserEntity(int id, String username,
                      String password, String firstName,
                      String lastName, String phone,
                      Role role, String image) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return  Objects.equals(id, user.id)
                && Objects.equals(username, user.username) && Objects.equals(password, user.password)
                && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(phone, user.phone) && role == user.role && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName,
                lastName, phone, role, image);
    }
    public String getImagePath() {
        return image == null ? null : "/images/users/" + id;
    }

}
