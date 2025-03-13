package by.emall.api.product.card;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ProductCard extends Base {
    private static final Logger logger = LogManager.getLogger(ProductCard.class);

    public static Response getViewProductCardRequest(ProductArticle article) {
        logger.info("Запрос информации о карточке товара (Артикул: {})", article.getArticleCode());

        try {
            Response response = given()
                    .headers(getHeaders())
                    .param("ids[]", article.getArticleCode()) // Убрал экранирование `\`, оно лишнее
                    .when()
                    .get(BASE_VIEW_PRODUCT_CARD_URL);

            logResponse(response, "запрос карточки товара");
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при получении карточки товара (Артикул: {})", article.getArticleCode(), e);
            throw e;
        }
    }

    private static void logResponse(Response response, String action) {
        int statusCode = response.getStatusCode();

        if (statusCode >= 200 && statusCode < 300) {
            logger.info("{} выполнен успешно (статус код: {})", action, statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            logger.warn("{} завершился ошибкой клиента (статус код: {}, тело: {})", action, statusCode, response.getBody().asString());
        } else if (statusCode >= 500) {
            logger.error("{} завершился ошибкой сервера (статус код: {}, тело: {})", action, statusCode, response.getBody().asString());
        }
    }
}
