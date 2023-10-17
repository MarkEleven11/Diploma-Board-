package ru.skypro.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkApplication {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HomeworkApplication.class);
    logger.debug("*** Start Project Application ***");
    SpringApplication.run(HomeworkApplication.class, args);
  }
}
