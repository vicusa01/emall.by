package by.emall.api.profile;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Profile extends Base {
    private static final Logger logger = LogManager.getLogger(Profile.class);
    private static final String VIEW_PROFILE_URL = BASE_URL + "/profile";

    public static Response getViewProfileRequest() {
        logger.info("Запрос профиля пользователя");

        try {
            Response response = given()
                    .headers(getHeaders())
                    .when()
                    .get(VIEW_PROFILE_URL);

            logIfError(response, "Запрос профиля");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при запросе профиля пользователя", e);
            throw e;
        }
    }

    public static Response putEditPersonalInfoRequest(String name, String surname, String patronymic, String email, String phone) {
        logger.info("Изменение данных профиля (Имя: {}, Фамилия: {}, Email: {})", name, surname, email);

        try {
            Response response = given()
                    .headers(getHeaders())
                    .body(new ProfileBody(name, surname, patronymic, email, phone))
                    .when()
                    .put(Base.BASE_URL);

            logIfError(response, "Изменение данных профиля");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при изменении данных профиля", e);
            throw e;
        }
    }

    private static void logIfError(Response response, String action) {
        if (response.getStatusCode() >= 400) {
            logger.error("{} завершился ошибкой (Статус: {}, Тело ответа: {})",
                    action, response.getStatusCode(), response.getBody().asString());
        }
    }
}
