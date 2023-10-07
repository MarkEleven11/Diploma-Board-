package ru.skypro.homework.exceptions;

public class FindNoEntityException extends RuntimeException{
    public FindNoEntityException(String message) {
        super(message);
    }
}
