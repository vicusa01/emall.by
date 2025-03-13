package by.emall.api.search;

import by.emall.api.Base;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Search extends Base {
    private static final Logger logger = LogManager.getLogger(Search.class);
    private static final String SEARCH_PRODUCT_URL = BASE_URL + "/preview-search";

    public static Response searchProductRequest(String searchProduct) {
        logger.info("Выполняется поиск товара: '{}'", searchProduct);

        try {
            Response response = given()
                    .headers(getHeaders())
                    .queryParam("query", searchProduct)
                    .when()
                    .get(SEARCH_PRODUCT_URL);

            logIfError(response, "Поиск товара: " + searchProduct);
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при выполнении поиска товара: '{}'", searchProduct, e);
            throw e;
        }
    }

    private static void logIfError(Response response, String action) {
        if (response.getStatusCode() >= 400) {
            logger.error("{} завершился ошибкой (Статус: {}, Тело ответа: {})",
                    action, response.getStatusCode(), response.getBody().asString());
        }
    }
}
