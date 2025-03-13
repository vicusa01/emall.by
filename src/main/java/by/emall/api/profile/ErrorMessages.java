package by.emall.api.profile;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages extends by.emall.api.signUp.ErrorMessages {
    public static final String INVALID_CREDENTIALS = "Неверный логин или пароль";
    public static final String UNEXIST_PHONE = "Пожалуйста, убедитесь, что правильно ввели телефон";
    public static final String WRONG_SMS_CODE = "Неверный код";
    public static final String INVALID_TOKEN = "Передан невалидный токен";
    public static final Map<String, String> ERROR_LOGIN_MAP = new HashMap<>();
    static {
        ERROR_LOGIN_MAP.put("REQUIRED_PHONE", REQUIRED_PHONE);
        ERROR_LOGIN_MAP.put("INVALID_PHONE", INVALID_PHONE);
        ERROR_LOGIN_MAP.put("INVALID_CREDENTIALS", INVALID_CREDENTIALS);
        ERROR_LOGIN_MAP.put("UNEXIST_PHONE", UNEXIST_PHONE);
        ERROR_LOGIN_MAP.put("WRONG_SMS_CODE", WRONG_SMS_CODE);
        ERROR_LOGIN_MAP.put("INVALID_TOKEN", INVALID_TOKEN);
    }
}
