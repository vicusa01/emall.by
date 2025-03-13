package by.emall.api.cart;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Cart extends Base {
    private static final Logger logger = LogManager.getLogger(Cart.class);
    protected static final String EDIT_CART_URL = "https://api-preprod.emall.by/api/v1/basket/items";

    public static Response getRequest() {
        logger.info("Отправка GET-запроса для получения содержимого корзины");

        try {
            Response response = given()
                    .headers(getHeaders())
                    .when()
                    .get(CART_URL);

            logger.info("GET-запрос выполнен успешно, статус код: {}", response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при выполнении GET-запроса к корзине", e);
            throw e;
        }
    }

    public static Response postAddItemRequest(int quantity, int item_id) {
        logger.info("Отправка POST-запроса на добавление товара (ID: {}, Количество: {})", item_id, quantity);

        try {
            Response response = addItemsToTheCart(quantity, item_id)
                    .when()
                    .post(EDIT_CART_URL);

            logger.info("POST-запрос выполнен успешно, статус код: {}", response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при добавлении товара в корзину", e);
            throw e;
        }
    }

    public static Response postRemoveItemRequest(int quantity, int item_id) {
        logger.info("Отправка POST-запроса на удаление товара (ID: {}, Количество: {})", item_id, quantity);

        try {
            Response response = removeItemsFromTheCart(quantity, item_id)
                    .when()
                    .post(EDIT_CART_URL);

            logger.info("POST-запрос выполнен успешно, статус код: {}", response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при удалении товара из корзины", e);
            throw e;
        }
    }

    public static Response deleteItemsFromTheRequest(int item_id) {
        logger.info("Отправка DELETE-запроса на удаление товара из корзины (ID: {})", item_id);

        try {
            Response response = given()
                    .headers(getHeaders())
                    .queryParam("basket_item_ids[]", item_id)
                    .when()
                    .delete(EDIT_CART_URL);

            logger.info("DELETE-запрос выполнен успешно, статус код: {}", response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("Ошибка при удалении товара из корзины", e);
            throw e;
        }
    }

    private static RequestSpecification addItemsToTheCart(int quantity, int item_id) {
        int newQuantity = getQuantityOfItem(item_id) + quantity;
        logger.info("Формирование запроса на добавление товара (ID: {}, Итоговое количество: {})", item_id, newQuantity);

        return given()
                .headers(getHeaders())
                .body(new CartBody(newQuantity, item_id));
    }

    private static RequestSpecification removeItemsFromTheCart(int quantity, int item_id) {
        int newQuantity = getQuantityOfItem(item_id) - quantity;
        logger.info("Формирование запроса на удаление товара (ID: {}, Итоговое количество: {})", item_id, newQuantity);

        return given()
                .headers(getHeaders())
                .body(new CartBody(newQuantity, item_id));
    }

    private static int getQuantityOfItem(int item_id) {
        logger.info("Получение количества товара (ID: {}) в корзине", item_id);

        try {
            int quantity = getBasketList().getOrDefault(item_id, 0);
            logger.info("Количество товара (ID: {}) в корзине: {}", item_id, quantity);
            return quantity;
        } catch (Exception e) {
            logger.warn("Ошибка при получении количества товара в корзине (ID: {})", item_id, e);
            return 0;
        }
    }

    private static Map<Integer, Integer> getBasketList() {
        logger.info("Запрос списка товаров в корзине");

        try {
            List<Map<String, Object>> items = getRequest()
                    .then()
                    .extract()
                    .path("basket_items");

            Map<Integer, Integer> basketList = items.stream()
                    .collect(Collectors.toMap(
                            item -> (Integer) item.get("id"),
                            item -> (Integer) item.get("quantity")
                    ));

            logger.info("Список товаров в корзине получен успешно");
            return basketList;
        } catch (Exception e) {
            logger.warn("Ошибка при получении списка товаров в корзине", e);
            return Map.of();
        }
    }
}