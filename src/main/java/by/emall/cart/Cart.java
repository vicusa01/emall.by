package by.emall.cart;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Cart extends Base {
    protected static final String EDIT_CART_URL = "https://api-preprod.emall.by/api/v1/basket" + "/items";

    public static Response getRequest() {
        return given()
                .headers(getHeaders())
                .when()
                .get(CART_URL);
    }

    public static Response postAddItemRequest(int quantity, int item_id) {
        return addItemsToTheCart(quantity, item_id)
                .when()
                .post(EDIT_CART_URL);
    }

    public static Response postRemoveItemRequest(int quantity, int item_id) {
        return removeItemsFromTheCart(quantity, item_id)
                .when()
                .post(EDIT_CART_URL);
    }

    public static Response deleteItemsFromTheRequest(int item_id) {
        return given()
                .headers(getHeaders())
                .queryParam("basket_item_ids\\[\\]", item_id)
                .queryParam("param2", "value2")
                .when()
                .delete(EDIT_CART_URL);
    }

    private static RequestSpecification addItemsToTheCart(int quantity, int item_id) {
        int newQuantity = getQuantityOfItem(item_id) + quantity;
        return given()
                .headers(getHeaders())
                .body(new CartBody(newQuantity, item_id));

    }

    private static RequestSpecification removeItemsFromTheCart(int quantity, int item_id) {
        int newQuantity = getQuantityOfItem(item_id) - quantity;
        return given()
                .headers(getHeaders())
                .body(new CartBody(newQuantity, item_id));

    }

    private static int getQuantityOfItem(int item_id) {
        return getBasketList().getOrDefault(item_id, 0);
    }

    private static Map<Integer, Integer> getBasketList() {
        List<Map<String, Object>> items = getRequest()
                .then()
                .extract()
                .path("basket_items");
        return items.stream()
                .collect(Collectors.toMap(
                        item -> (Integer) item.get("id"),
                        item -> (Integer) item.get("quantity")
                ));
    }
}
