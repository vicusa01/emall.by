package by.emall.api.favorities;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Favorites extends Base {
    private static final Logger logger = LogManager.getLogger(Favorites.class);

    public static Response postAddToFavoritesRequest(Integer productId) {
        logger.info("Добавление товара в избранное (ID: {})", productId);
        try {
            Response response = given()
                    .headers(by.emall.api.Base.getHeaders())
                    .when()
                    .post(BASE_FAVORITES_EDIT_URL + productId);

            logResponse(response, "добавление в избранное");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при добавлении товара в избранное (ID: {})", productId, e);
            throw e;
        }
    }

    public static Response getAllFavorites() {
        logger.info("Получение списка избранных товаров");
        try {
            Response response = given()
                    .headers(getHeaders())
                    .when()
                    .get(BASE_FAVORITES_VIEW_URL);

            logResponse(response, "получение списка избранных товаров");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при получении списка избранных товаров", e);
            throw e;
        }
    }

    private static void logResponse(Response response, String action) {
        int statusCode = response.getStatusCode();

        if (statusCode >= 200 && statusCode < 300) {
            logger.info("{} выполнено успешно (статус код: {})", action, statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            logger.warn("{} завершилось ошибкой клиента (статус код: {}, тело: {})", action, statusCode, response.getBody().asString());
        } else if (statusCode >= 500) {
            logger.error("{} завершилось ошибкой сервера (статус код: {}, тело: {})", action, statusCode, response.getBody().asString());
        }
    }
}
