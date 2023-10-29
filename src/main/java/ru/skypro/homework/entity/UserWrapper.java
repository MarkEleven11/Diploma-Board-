package ru.skypro.homework.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserWrapper implements UserDetails {

    private final UserEntity userEntity;

    public UserWrapper(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(this.userEntity.getRole().toString())
        );
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userEntity.isNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userEntity.isNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userEntity.isNonCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userEntity.isEnabled();
    }
}
