package by.emall;

import io.restassured.response.Response;

import static io.restassured.RestAssured.when;

public class Token {
    public static String API_TOKEN;
    public static String HG_CLIENT_SECURITY;
    public static void getTokenAndCookies(){
        String URL = "https://emall.by/";
        Response response = when()
                .get(URL)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString("<html"))
                .body(org.hamcrest.Matchers.containsString("Интернет-магазин Emall"))
                .extract()
                .response();
        Token.API_TOKEN =response.getCookie("api_token");
        Token.HG_CLIENT_SECURITY= response.getCookie("hg-client-security");
    }
}
