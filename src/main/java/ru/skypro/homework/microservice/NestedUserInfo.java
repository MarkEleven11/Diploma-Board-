package ru.skypro.homework.microservice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.UserEntity;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
final class NestedUserInfo implements Serializable {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    public NestedUserInfo(UserEntity userEntity) {
        id = Long.valueOf(userEntity.getId());
        username = userEntity.getUsername();
        firstName = userEntity.getFirstName();
        lastName = userEntity.getLastName();
    }

    public String toString() {
        return String.format("User id %s username : %s; firstname : %s; lastname : %s ",
                id, username, firstName, lastName);
    }
}
