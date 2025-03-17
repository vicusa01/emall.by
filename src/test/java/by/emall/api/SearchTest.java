package by.emall.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static by.emall.api.search.ErrorMessages.ERROR_QUERY_REQUIRED;
import static by.emall.api.search.ErrorMessages.ERROR_QUERY_REQUIRED_WHEN_NO_PROMOTION_URL;
import static by.emall.api.search.Search.searchProductRequest;

public class SearchTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("Search product")
    public void testSearchProduct() {
        Response response = searchProductRequest("adidas")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .response();
        response
                .then()
                .body("offers.name", hasItem(containsStringIgnoringCase("adidas")));
    }

    @Test
    @DisplayName("Search request with empty query param")
    public void testSearchProduct1() {
        searchProductRequest("")
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.query[0]", equalTo(ERROR_QUERY_REQUIRED));
    }

    @Test
    @DisplayName("Error on query with only spaces")
    public void testSearchProduct2() {
        searchProductRequest("         ")
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.query[0]", equalTo(ERROR_QUERY_REQUIRED_WHEN_NO_PROMOTION_URL));
    }

}
