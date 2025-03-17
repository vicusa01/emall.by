package by.emall.api;

import by.emall.api.favorities.ErrorMessages;
import by.emall.api.favorities.Favorites;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class FavoritesTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("Adding product to favorites")
    public void testAddToFavorites() {
        Favorites.postAddToFavoritesRequest(9305561)
                .then()
                .statusCode(204)
                .log()
                .all();
    }

    @Test
    @DisplayName("Adding unexist product to favorites")
    public void testAddToFavorites1() {
        Favorites.postAddToFavoritesRequest(9999999)
                .then()
                .statusCode(404)
                .log()
                .all()
                .body("message", equalTo(ErrorMessages.PRODUCT_NOT_FOUND));
    }

    @Test
    @DisplayName("View list of favorites")
    public void testViewFavorites() {
        Favorites.getAllFavorites()
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}
