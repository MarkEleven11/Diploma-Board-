package ru.skypro.homework.microservice;

import java.io.Serializable;


public interface MicroServiceMessage extends Serializable {

    String getCommonInfo();
}
