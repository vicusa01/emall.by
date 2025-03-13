package by.emall.api.product.filters;

import java.util.HashMap;
import java.util.Map;

public class Base extends by.emall.api.Base {
    protected static final String BASE_VIEW_PRODUCTS_URL = "https://emall.by/_next/data" + by.emall.api.Base.CLIENT_ID + "/category/";
    protected static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "emall.by");
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
