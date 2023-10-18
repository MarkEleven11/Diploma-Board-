package ru.skypro.homework.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class Advice {
    @ExceptionHandler(FindNoEntityException.class)
    public void handleException(FindNoEntityException e) {
        log.warn("Обращение к несуществующей записи: " + e.getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void handleException(EmptyResultDataAccessException e) {
        log.warn("Обращение к несуществующей записи: " + e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public void handleException(IOException e) {
        log.warn("Ошибка чтения/записи изображения " + e.getMessage());
    }
}
