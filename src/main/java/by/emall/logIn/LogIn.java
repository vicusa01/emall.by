package by.emall.logIn;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LogIn extends Base {
    private static final String LOG_IN_PASSWORD_URL = BASE_URL + "/password";
    private static final String LOG_IN_SMS_URL = BASE_URL + "/sms";
    private static final String ENTER_SMS_URL = LOG_IN_SMS_URL + "/verify";

    public static Response logInSmsRequest() {
        return given()
                .headers(getHeaders())
                .body(new LogInBody())
                .when()
                .post(LOG_IN_SMS_URL);
    }

    public static Response logInSmsRequest(String phone) {
        return given()
                .headers(getHeaders())
                .body(new LogInBody(phone))
                .when()
                .post(LOG_IN_SMS_URL);
    }

    public static Response logInPasswordRequest() {
        return given()
                .headers(getHeaders())
                .body(new LogInBody())
                .when()
                .post(LOG_IN_PASSWORD_URL);
    }

    public static Response logInPasswordRequest(String phone, String password) {
        return given()
                .headers(getHeaders())
                .body(new LogInBody(phone, password))
                .when()
                .post(LOG_IN_PASSWORD_URL);
    }

    public static Response enterSMSRequest(String smsCode) {
        return given()
                .headers(getHeaders())
                .body("{\n" +
                        "    \"code\":" + smsCode +
                        "}")
                .when()
                .post(ENTER_SMS_URL);
    }
}
