package by.emall.signUp;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SignUp extends Base {
    private static final String SIGN_UP_URL = BASE_URL + "/registration/sms";

    public static Response postRequest() {
        return given()
                .headers(getHeaders())
                .body(new SignUpBody())
                .when()
                .post(SIGN_UP_URL);
    }

    public static Response postRequest(String name,
                                       String surname,
                                       String patronymic,
                                       String phone,
                                       String email,
                                       String password,
                                       int[] target_ids) {
        return given()
                .headers(getHeaders())
                .body(new SignUpBody(name,surname,patronymic,phone,email,password,target_ids))
                .when()
                .post(SIGN_UP_URL);
    }
}
