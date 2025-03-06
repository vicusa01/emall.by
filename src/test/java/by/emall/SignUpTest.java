package by.emall;

import by.emall.signUp.ErrorMessages;
import by.emall.signUp.SignUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class SignUpTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("[SIGN UP] Empty fields")
    public void testFailedSignUp_EmptyFields() {
        SignUp.postRequest()
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.target_ids[0]", equalTo(ErrorMessages.REQUIRED_TARGET_ID))
                .body("errors.phone[0]", equalTo(ErrorMessages.REQUIRED_PHONE))
                .body("errors.name[0]", equalTo(ErrorMessages.REQUIRED_NAME))
                .body("errors.surname[0]", equalTo(ErrorMessages.REQUIRED_SURNAME))
                .body("errors.password[0]", equalTo(ErrorMessages.REQUIRED_PASSWORD));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid name length")
    public void testFailedSignUp_InvalidNameLength() {
        SignUp.postRequest("В", "Цыглер", "Вал", "+375294567898", "tet@mail.ru", "14089476Dbhbr@", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.name[0]", equalTo(ErrorMessages.INVALID_NAME_LENGTH));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid surname length")
    public void testFailedSignUp_InvalidSurnameLength() {
        SignUp.postRequest("Ви", "Ц", "Вал", "+375294567898", "tet@mail.ru", "14089476Dbhbr@", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.surname[0]", equalTo(ErrorMessages.INVALID_SURNAME_LENGTH));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid name and surname characters")
    public void testFailedSignUp_InvalidNameAndSurnameCharacters() {
        SignUp.postRequest("11", "22", "Вал", "+375294567898", "tet@mail.ru", "14089476Dbhbr@", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.name[0]", equalTo(ErrorMessages.INVALID_NAME_CHARACTERS))
                .body("errors.surname[0]", equalTo(ErrorMessages.INVALID_SURNAME_CHARACTERS));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid password (< min length)")
    public void testFailedSignUp_InvalidPasswordMinLength() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "+375294567898", "tet@mail.ru", "1404kJ@", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.password[0]", equalTo(ErrorMessages.INVALID_PASSWORD_LENGTH));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid password (> max length)")
    public void testFailedSignUp_InvalidPasswordMaxLength() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "+375294567898", "tet@mail.ru",
                        "1111111111111111111111111111111111111111111111111111111111111jJ", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.password[0]", equalTo(ErrorMessages.INVALID_PASSWORD_LENGTH));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid password (without small letter)")
    public void testFailedSignUp_InvalidPasswordNoSmallLetter() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "+375294567898", "tet@mail.ru", "14041976DDDD", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.password[0]", equalTo(ErrorMessages.INVALID_PASSWORD_LENGTH));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid password (without big letter)")
    public void testFailedSignUp_InvalidPasswordNoBigLetter() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "+375294567898", "tet@mail.ru", "14041976hhhhh", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.password[0]", equalTo(ErrorMessages.INVALID_PASSWORD_LENGTH));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid target_id")
    public void testFailedSignUp_InvalidTargetId() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "+375294567898", "tet@mail.ru", "14089476Dbhbr@", new int[]{1, 2})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.TargetId[0]", equalTo(ErrorMessages.INVALID_TARGET_ID));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid email")
    public void testFailedSignUp_InvalidEmail() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "+375294567898", "545", "14089476Dbhbr@", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.email[0]", equalTo(ErrorMessages.INVALID_EMAIL));
    }

    @Test
    @DisplayName("[SIGN UP] Invalid phone")
    public void testFailedSignUp_InvalidPhone() {
        SignUp.postRequest("Ви", "Цыглер", "Вал", "75444444444", "tet@mail.ru", "14089476Dbhbr@", new int[]{33, 34, 35})
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.phone[0]", equalTo(ErrorMessages.INVALID_PHONE));
    }
}
