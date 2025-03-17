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

    @Test
    @DisplayName("[LOGIN][PASSWORD] invalid phone")
    public void testLogIn1() {
        LogIn.logInPasswordRequest("75293527282", "2375843")

                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.phone[0]", equalTo(ErrorMessages.INVALID_PHONE));
    }

    @Test
    @DisplayName("[LOGIN][PASSWORD] phone doesn't exist")
    public void testLogIn2() {
        LogIn.logInPasswordRequest("375445863145", "2375843")

                .then()
                .statusCode(401)
                .log()
                .all()
                .body("message", equalTo(ErrorMessages.INVALID_CREDENTIALS));
    }

    @Test
    @DisplayName("[LOGIN][PASSWORD] invalid password")
    public void testLogIn3() {
        LogIn.logInPasswordRequest("375445853145", "28")
                .then()
                .statusCode(401)
                .log()
                .all()
                .body("message", equalTo(ErrorMessages.INVALID_CREDENTIALS));
    }

    @Test
    @DisplayName("[LOGIN][SMS] empty field")
    public void testLogIn4() {
        LogIn.logInSmsRequest()
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.phone[0]", equalTo(ErrorMessages.REQUIRED_PHONE));
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
        LogIn.logInPasswordRequest("375445853145", "14041976Dfkbr@")
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("surname", equalTo("Цыглер"));
    }
}
