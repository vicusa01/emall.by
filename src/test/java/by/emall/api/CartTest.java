package by.emall.api;

import by.emall.api.cart.Cart;
import by.emall.api.cart.ErrorMessages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class CartTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("view cart")
    public void testCart() {
        Cart.getRequest()
                .then()
                .statusCode(anyOf(is(200),is (201)))
                .log()
                .all();
    }

    @Test
    @DisplayName("add orders to cart")
    public void testCart1() {
        int quantity = 1;
        int item_id = 495915;
        Cart.postAddItemRequest(quantity, item_id)
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("basket_items[0].offer.id", equalTo(item_id))
                .body("basket_items[0].offer.basket_quantity", equalTo(quantity));
    }

    @Test
    @DisplayName("add unexist orders to cart")
    public void testCart2() {
        int quantity = 1;
        int item_id = 495915444;
        Cart.postAddItemRequest(quantity, item_id)
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.offer_id[0].", equalTo(ErrorMessages.INCORRECT_OFFER_ID));
    }

    @Test
    @DisplayName("add unavailable orders to cart")
    public void testCart3() {
        int quantity = 1;
        int item_id = 9286170;
        Cart.postAddItemRequest(quantity, item_id)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("add >max available (50 items)  orders to cart")
    public void testCart4() {
        int quantity = 545454554;
        int item_id = 495915;
        Cart.postAddItemRequest(quantity, item_id)
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("basket_items[0].offer.basket_quantity", equalTo(50));
    }

    @Test
    @DisplayName("removing unexist orders from cart")
    public void testCart5() {
        int item_id = 41128066;
        Cart.deleteItemsFromTheRequest(item_id)
                .then()
                .statusCode(422)
                .log()
                .all()
                .body("errors.basket_item_ids[0]", equalTo(ErrorMessages.ITEM_NOT_FOUND_IN_BASKET));
    }
}
