package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Сервис для аутентификации и регистрации пользователей
 */
public interface AuthService {

    /**
     * Метод для проверки аутентификации пользователя по имени пользователя и паролю
     *
     * @param userName Имя пользователя (логин)
     * @param password Пароль пользователя
     * @return true, если пользователь успешно аутентифицирован, в противном случае false
     */
    boolean login(String userName, String password);

    /**
     * Метод для регистрации нового пользователя на основе предоставленных данных
     *
     * @param register Модель данных пользователя для регистрации
     * @return true, если регистрация прошла успешно, в противном случае false
     */
    boolean register(Register register);
}
