package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * Конфигурационный класс для настройки шифрования паролей
 */
@Component
public class EncoderSecurityConfig {

    /**
     * Метод создает и настраивает бин для шифрования паролей с использованием BCryptPasswordEncoder.
     *
     * @return Объект PasswordEncoder для шифрования паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
