package ru.skypro.homework.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * Конфигурационный класс для настройки мультипарт файлов
 */
public class MultipartConfig {

    /**
     * Метод, создающий и настраивающий бин для параметров мультипарт-загрузки файлов
     *
     * @return Объект MultipartConfigElement с настроенными параметрами максимального размера файла и запроса
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("5000KB"));
        factory.setMaxRequestSize(DataSize.parse("5000KB"));
        return factory.createMultipartConfig();
    }

}
