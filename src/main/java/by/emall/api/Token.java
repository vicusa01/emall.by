package by.emall.api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.when;

public class Token {
    private static final Logger logger = LogManager.getLogger(Token.class);
    public static String API_TOKEN;
    public static String HG_CLIENT_SECURITY;
    private static final String URL = "https://emall.by/";

    public static void getTokenAndCookies() {
        logger.info("Запрос токена и cookies с {}", URL);

        try {
            Response response = when()
                    .get(URL)
                    .then()
                    .statusCode(200)
                    .body(org.hamcrest.Matchers.containsString("<html"))
                    .body(org.hamcrest.Matchers.containsString("интернет-магазин Емолл"))
                    .extract()
                    .response();

            API_TOKEN = response.getCookie("api_token");
            HG_CLIENT_SECURITY = response.getCookie("hg-client-security");

            if (API_TOKEN == null || HG_CLIENT_SECURITY == null) {
                logger.warn("Один или оба cookie не были получены! API_TOKEN: {}, HG_CLIENT_SECURITY: {}", API_TOKEN, HG_CLIENT_SECURITY);
            } else {
                logger.info("Токены успешно получены. API_TOKEN: {}, HG_CLIENT_SECURITY: {}", API_TOKEN, HG_CLIENT_SECURITY);
            }
        } catch (Exception e) {
            logger.error("Ошибка при получении токена и cookies", e);
            throw e;
        }
    }
}
