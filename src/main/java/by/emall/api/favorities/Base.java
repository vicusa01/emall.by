package by.emall.api.favorities;

import java.util.HashMap;
import java.util.Map;

public class Base extends by.emall.api.Base {
    protected static final String BASE_FAVORITES_VIEW_URL = "https://emall.by/_next/data" + Base.CLIENT_ID + "/favorites.json";
    protected static final String BASE_FAVORITES_EDIT_URL = BASE_URL + "/favorites/";
    protected static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
