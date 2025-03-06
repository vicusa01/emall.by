package by.emall.cart;

public class CartBody {
//        "quantity": 1,
//                "offer_id": 495915,
//                "gtm_item_list_id": "ForgetToBuy",
//                "gtm_item_list_name": "ForgetToBuy"
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
