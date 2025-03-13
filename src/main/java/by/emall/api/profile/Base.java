package by.emall.api.profile;

import java.util.HashMap;
import java.util.Map;

import static by.emall.api.Token.HG_CLIENT_SECURITY;

public class Base extends by.emall.api.Base {
    public static String API_TOKEN="2AZBRqRZsR8i1t1zX9vdDFODxl5qjRud";
    protected static final String BASE_URL = by.emall.api.Base.BASE_URL+"/user";
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
