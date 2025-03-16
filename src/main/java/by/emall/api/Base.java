package by.emall.api;

import java.util.HashMap;
import java.util.Map;

import static by.emall.api.Token.API_TOKEN;
import static by.emall.api.Token.HG_CLIENT_SECURITY;

public class Base {
    protected static final String BASE_URL = "https://api-preprod.emall.by/api/v1";
    protected static final String CLIENT_ID = "/33xjhb-pu-C6lkhzKrurU";
    protected static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "api-preprod.emall.by");
        headers.put("apitoken", API_TOKEN);
        headers.put("Content-Type", "application/json");
        headers.put("Cookie", "api_token=" + API_TOKEN +
                "; api_token_s=" + API_TOKEN +
                "; hg-client-security=" + HG_CLIENT_SECURITY);
        return headers;
    }
}
