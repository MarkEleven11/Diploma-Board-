package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;
/**
 * Сервис для работы с пользователями
 */
public interface UserService extends UserDetailsService {

    /**
     * Метод для сохранения информации о пользователе
     *
     * @param model Сущность пользователя
     * @return Сохраненная сущность пользователь
     */
    UserEntity save(UserEntity model);

    /**
     * Метод для создания или обновления информацию о пользователе
     *
     * @param userDetails Сведения о пользователе, полученные из Spring Security
     * @param user Обновленные сведения о пользователе
     * @return Обновленные сведения о пользователе
     */
    UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser user);

    /**
     * Метод для обновления изображения-аватара пользователя
     *
     * @param userDetails Сведения о пользователе, полученные из Spring Security
     * @param multipartFile Изображение пользователя в формате MultipartFile
     * @return Обновленная сущность пользователя с новым изображением
     */
    UserEntity updateImage(UserDetails userDetails, MultipartFile multipartFile) throws IOException;

    /**
     * Метод для поиска сущности пользователя по логину (имени пользователя)
     *
     * @param login Логин (имя пользователя)
     * @return Сущность пользователя с указанным логином
     */
    UserEntity findUserEntityByLogin(String login);

    /**
     * Метод для проверки существования информации о пользователе в базе данных
     *
     * @param login Логин (имя пользователя)
     * @return true, если пользователь существует, в противном случае - false
     */
    boolean userExists(String login);

    /**
     * Метод для обновления пароля пользователя
     *
     * @param newPassword Модель нового пароля
     * @param userDetails Сведения о пользователе, полученные из Spring Security
     * @return Обновленная сущность пользователя с обновленным паролем
     */
    UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails);

    /**
     * Метод для получения модели пользователя
     *
     * @param userDetails Сведения о пользователе, полученные из Spring Security
     * @return Информация о пользователе
     */
    User getUser(UserDetails userDetails);
}
