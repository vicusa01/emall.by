package by.emall.api;

import by.emall.api.logIn.ErrorMessages;
import by.emall.api.logIn.LogIn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.Matchers.equalTo;

public class LogInTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("[LOGIN][PASSWORD] empty fields")
    public void testLogIn() {
        LogIn.logInPasswordRequest()
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.phone[0]", equalTo(ErrorMessages.REQUIRED_PHONE))
                .body("errors.password[0]", equalTo(ErrorMessages.REQUIRED_PASSWORD));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logInData.csv", numLinesToSkip = 1)
    @DisplayName("[LOGIN][PASSWORD] test")
    public void testLogIn1(String phone, String password, Integer statusCode, String errorKey) {
        LogIn.logInPasswordRequest(phone, password)
                .then()
                .statusCode(statusCode)
                .log()
                .all()
                .body("errors.phone[0]", equalTo(ErrorMessages.ERROR_LOGIN_MAP.get(errorKey)));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logInData.csv", numLinesToSkip = 1)
    @DisplayName("[LOGIN][SMS] test")
    public void testLogIn4(String phone, Integer statusCode, String errorKey) {
        LogIn.logInSmsRequest(phone)
                .then()
                .statusCode(statusCode)
                .log()
                .all()
                .body("errors.phone[0]",equalTo(ErrorMessages.ERROR_LOGIN_MAP.get(errorKey)));
    }

    @Test
    @DisplayName("[LOGIN][SMS] invalid phone")
    public void testLogIn5() {
        LogIn.logInSmsRequest("44")
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.phone[0]", equalTo(ErrorMessages.INVALID_PHONE));
    }

    @Test
    @DisplayName("[LOGIN][SMS] doesn't exist")
    public void testLogIn6() {
        LogIn.logInSmsRequest("375444444444")
                .then()
                .statusCode(400)
                .log()
                .all()
                .body("message", equalTo(ErrorMessages.UNEXIST_PHONE));
    }

    @Test
    @DisplayName("[LOGIN] enter wrong SMS code")
    public void testLogIn7() {
        LogIn.enterSMSRequest("34255550")
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.code[0]", equalTo(ErrorMessages.WRONG_SMS_CODE));
    }

    @Test
    @DisplayName("[LOGIN] invalidToken")
    public void testLogIn8() {
        LogIn.enterSMSRequest("3420")
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.CheckError[0]", equalTo(ErrorMessages.INVALID_TOKEN));
    }

    @Test
    @DisplayName("[LOGIN] success")
    public void testLogIn9() {
        LogIn.logInPasswordRequest("375445853145", "etfds@455cS")
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("surname",equalTo("Цыглер"));
    }
}
