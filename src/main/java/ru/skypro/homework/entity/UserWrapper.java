package ru.skypro.homework.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

/**
 * Класс представляет обертку для объекта {@link UserEntity} и реализует интерфейс {@link UserDetails}
 * для интеграции с Spring Security.
 * Класс позволяет использовать объекты типа {@code UserEntity} как объекты пользователя Spring Security.
 *
 * @see UserEntity
 * @see UserDetails
 */
public class UserWrapper implements UserDetails {

    private final UserEntity userEntity;

    /**
     * Конструктор класса {@code UserWrapper}.
     *
     * @param userEntity Объект типа {@link UserEntity}, который будет обернут.
     */
    public UserWrapper(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Метод для возврашения коллекции ролей пользователя. В данной реализации возвращается одна роль,
     * соответствующая роли пользователя в объекте {@link UserEntity}.
     *
     * @return Коллекция объектов типа {@link GrantedAuthority} представляющих роли пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(
                "ROLE_" + userEntity.getRole().toString()));
    }

    /**
     * Метод для возвращения пароля пользователя из объекта {@link UserEntity}.
     *
     * @return Пароль пользователя.
     */
    @NotNull
    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    /**
     * Метод для возвращения имени пользователя (логин) из объекта {@link UserEntity}.
     *
     * @return Имя пользователя (логин).
     */
    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    /**
     * Метод проверяет, не истек ли срок действия учетной записи пользователя.
     *
     * @return {@code true}, если срок действия учетной записи пользователя не истек, иначе {@code false}.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Метод проверяет, не заблокирована ли учетная запись пользователя.
     *
     * @return {@code true}, если учетная запись пользователя не заблокирована, иначе {@code false}.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Метод проверяет, не истек ли срок действия учетных данных пользователя.
     *
     * @return {@code true}, если срок действия учетных данных пользователя не истек, иначе {@code false}.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Метод проверяет, активирована ли учетная запись пользователя.
     *
     * @return {@code true}, если учетная запись пользователя активирована, иначе {@code false}.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
