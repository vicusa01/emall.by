package by.emall.api.logIn;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class LogIn extends Base {
    private static final Logger logger = LogManager.getLogger(LogIn.class);

    private static final String LOG_IN_PASSWORD_URL = BASE_URL + "/password";
    private static final String LOG_IN_SMS_URL = BASE_URL + "/sms";
    private static final String ENTER_SMS_URL = LOG_IN_SMS_URL + "/verify";

    public static Response logInSmsRequest() {
        logger.info("Отправка запроса на вход по SMS без указания номера телефона");
        return sendLogInRequest(LOG_IN_SMS_URL, new LogInBody());
    }

    public static Response logInSmsRequest(String phone) {
        logger.info("Отправка запроса на вход по SMS (телефон: {})", phone);
        return sendLogInRequest(LOG_IN_SMS_URL, new LogInBody(phone));
    }

    public static Response logInPasswordRequest() {
        logger.info("Отправка запроса на вход по паролю без указания номера и пароля");
        return sendLogInRequest(LOG_IN_PASSWORD_URL, new LogInBody());
    }

    public static Response logInPasswordRequest(String phone, String password) {
        logger.info("Отправка запроса на вход по паролю (телефон: {})", phone);
        return sendLogInRequest(LOG_IN_PASSWORD_URL, new LogInBody(phone, password));
    }

    public static Response enterSMSRequest(String smsCode) {
        logger.info("Отправка кода подтверждения SMS (код: {})", smsCode);

        try {
            Response response = given()
                    .headers(getHeaders())
                    .body("{\"code\":\"" + smsCode + "\"}")
                    .when()
                    .post(ENTER_SMS_URL);

            logResponse(response, "вход с кодом подтверждения");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при отправке кода SMS", e);
            throw e;
        }
    }

    private static Response sendLogInRequest(String url, LogInBody body) {
        try {
            Response response = given()
                    .headers(getHeaders())
                    .body(body)
                    .when()
                    .post(url);

            logResponse(response, "логин");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при выполнении запроса на логин (URL: {})", url, e);
            throw e;
        }
    }

    private static void logResponse(Response response, String action) {
        int statusCode = response.getStatusCode();

        if (statusCode >= 200 && statusCode < 300) {
            logger.info("{} выполнен успешно (статус код: {})", action, statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            logger.warn("{} завершился с ошибкой клиента (статус код: {}, тело: {})", action, statusCode, response.getBody().asString());
        } else if (statusCode >= 500) {
            logger.error("{} завершился с ошибкой сервера (статус код: {}, тело: {})", action, statusCode, response.getBody().asString());
        }
    }
}
