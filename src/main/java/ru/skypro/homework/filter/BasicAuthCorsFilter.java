package ru.skypro.homework.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс {@code BasicAuthCorsFilter} представляет собой фильтр, который обрабатывает HTTP-запросы,
 * добавляя заголовок для разрешения передачи учетных данных (credentials) через CORS (Cross-Origin Resource Sharing).
 *
 * <p>Фильтр позволяет браузерам выполнять запросы с учетными данными на сервер,
 * даже если запрос отправляется с другого домена. Он гарантирует, что сервер разрешает обмен данными
 * между разными доменами, при этом обеспечивая безопасность и контроль доступа.
 *
 * <p>Фильтр добавляет заголовок "Access-Control-Allow-Credentials" со значением "true" ко всем HTTP-ответам,
 * что указывает браузеру на разрешение передачи учетных данных между источниками (origins).
 *
 * @see OncePerRequestFilter
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Метод {@code doFilterInternal} выполняет фактическую обработку запросов, добавляя заголовок
     * "Access-Control-Allow-Credentials" со значением "true" ко всем HTTP-ответам.
     *
     * @param httpServletRequest  объект запроса HTTP
     * @param httpServletResponse объект ответа HTTP
     * @param filterChain         цепочка фильтров, через которую проходит запрос
     * @throws ServletException если происходит ошибка в обработке запроса
     * @throws IOException      если происходит ошибка ввода/вывода при обработке запроса
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}