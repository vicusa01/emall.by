package by.emall.api;

import by.emall.api.product.card.ProductArticle;
import by.emall.api.product.card.ProductCard;
import by.emall.api.product.filters.CategoryCode;
import by.emall.api.product.filters.Filters;
import by.emall.api.product.filters.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("View product card")
    public void testViewProductCard() {
        ProductCard.getViewProductCardRequest(ProductArticle.MILK)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("View product card from category")
    public void testViewProductCard1() {
        Product.getCategoryProductsRequest(CategoryCode.WATER)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("View products by filters")
    public void testViewProductCard2() {
        Product.getProductsByFiltersRequest(Filters.BY_POPULARITY)
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}
