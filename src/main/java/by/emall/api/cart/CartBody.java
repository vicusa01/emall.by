package by.emall.api.cart;

public class CartBody {
    private Integer quantity;
    private Integer offer_id;

    public CartBody(Integer quantity, Integer offer_id) {
        this.quantity = quantity;
        this.offer_id = offer_id;
    }

    public CartBody() {
    }

    public Integer getQuantity() {
        return quantity;
    }


    public Integer getOffer_id() {
        return offer_id;
    }


}
