package by.emall.api.signUp;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class SignUp extends Base {
    private static final Logger logger = LogManager.getLogger(SignUp.class);
    private static final String SIGN_UP_URL = BASE_URL + "/registration/sms";

    public static Response postRequest() {
        logger.info("Отправка запроса на регистрацию (без параметров)");

        try {
            Response response = given()
                    .headers(getHeaders())
                    .body(new SignUpBody())
                    .when()
                    .post(SIGN_UP_URL);

            logIfError(response, "Регистрация без параметров");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при выполнении запроса на регистрацию (без параметров)", e);
            throw e;
        }
    }

    public static Response postRequest(String name,
                                       String surname,
                                       String patronymic,
                                       String phone,
                                       String email,
                                       String password,
                                       int[] target_ids) {
        logger.info("Отправка запроса на регистрацию (Имя: {}, Фамилия: {}, Email: {})", name, surname, email);

        try {
            Response response = given()
                    .headers(getHeaders())
                    .body(new SignUpBody(name, surname, patronymic, phone, email, password, target_ids))
                    .when()
                    .post(SIGN_UP_URL);

            logIfError(response, "Регистрация пользователя: " + email);
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при регистрации пользователя (Email: {})", email, e);
            throw e;
        }
    }

    private static void logIfError(Response response, String action) {
        if (response.getStatusCode() >= 400) {
            logger.error("{} завершилась ошибкой (Статус: {}, Тело ответа: {})",
                    action, response.getStatusCode(), response.getBody().asString());
        }
    }
}
