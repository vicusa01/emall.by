package by.emall.api.product.filters;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Product extends Base {
    private static final Logger logger = LogManager.getLogger(Product.class);

    public static Response getCategoryProductsRequest(CategoryCode categoryCode) {
        logger.info("Запрос товаров категории (ID: {})", categoryCode.getArticleCode());

        try {
            Response response = given()
                    .headers(getHeaders())
                    .param("id", categoryCode.getArticleCode())
                    .when()
                    .get(Base.BASE_VIEW_PRODUCTS_URL + categoryCode.getArticleCode() + ".json");

            if (response.getStatusCode() >= 400) {
                logger.error("Ошибка при запросе товаров категории (ID: {}, статус: {})",
                        categoryCode.getArticleCode(), response.getStatusCode());
            }

            return response;
        } catch (Exception e) {
            logger.error("Исключение при запросе товаров категории (ID: {})", categoryCode.getArticleCode(), e);
            throw e;
        }
    }

    public static Response getProductsByFiltersRequest(Filters filter) {
        logger.info("Запрос товаров с фильтром (ID: {})", filter.getFilterCode());

        try {
            Response response = given()
                    .headers(getHeaders())
                    .param("common_sorting_id", filter.getFilterCode())
                    .when()
                    .get(Base.BASE_VIEW_PRODUCTS_URL + filter.getFilterCode() + ".json");

            if (response.getStatusCode() >= 400) {
                logger.error("Ошибка при запросе товаров с фильтром (ID: {}, статус: {})",
                        filter.getFilterCode(), response.getStatusCode());
            }

            return response;
        } catch (Exception e) {
            logger.error("Исключение при запросе товаров с фильтром (ID: {})", filter.getFilterCode(), e);
            throw e;
        }
    }
}
