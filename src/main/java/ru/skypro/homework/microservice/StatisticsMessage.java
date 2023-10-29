package ru.skypro.homework.microservice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.skypro.homework.HomeworkApplication;
import ru.skypro.homework.entity.UserEntity;

import java.time.LocalDateTime;

@Data
@Slf4j
@EqualsAndHashCode
public class StatisticsMessage implements MicroServiceMessage {

    private static long counter;
    private Long messageId;
    private NestedUserInfo userInfo;
    private LocalDateTime timeSending;
    private transient String fromServiceName;
    private transient String toServiceName;


    public StatisticsMessage(UserEntity userEntity) {
        messageId = counter++;
        userInfo = new NestedUserInfo(userEntity);
        timeSending = LocalDateTime.now();
        toServiceName = "StatisticService";
        fromServiceName = HomeworkApplication.class.getSimpleName();
    }

    @Override
    public String getCommonInfo() {
        return String.format("%s id %s, user %s from : %s to : %s",
                getClass().getSimpleName(), messageId,
                userInfo, fromServiceName, toServiceName);
    }
}
