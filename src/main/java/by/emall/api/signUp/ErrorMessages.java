package by.emall.api.signUp;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages {
        public static final String REQUIRED_TARGET_ID = "Поле \"target ids\" обязательно для заполнения.";
        public static final String REQUIRED_PHONE = "Поле \"Номер телефона\" обязательно для заполнения.";
        public static final String REQUIRED_NAME = "Поле \"Имя\" обязательно для заполнения.";
        public static final String REQUIRED_SURNAME = "Поле \"Фамилия\" обязательно для заполнения.";
        public static final String REQUIRED_PASSWORD = "Поле \"Пароль\" обязательно для заполнения.";

        public static final String INVALID_NAME_LENGTH = "Количество символов в поле \"Имя\" должно быть не меньше 2";
        public static final String INVALID_SURNAME_LENGTH = "Количество символов в поле \"Фамилия\" должно быть не меньше 2";
        public static final String INVALID_NAME_CHARACTERS = "Cодержит недопустимые символы";
        public static final String INVALID_SURNAME_CHARACTERS = "Cодержит недопустимые символы";

        public static final String INVALID_PASSWORD_LENGTH = "Пароль должен содержать от 8 до 50 символов, включая хотя бы одну цифру, одну строчную и одну прописную латинские буквы";
        public static final String INVALID_PASSWORD_NO_SMALL = "Пароль должен содержать хотя бы одну строчную латинскую букву";
        public static final String INVALID_PASSWORD_NO_BIG = "Пароль должен содержать хотя бы одну прописную латинскую букву";

        public static final String INVALID_TARGET_ID = "Не переданы все обязательные согласия на сервисы";
        public static final String INVALID_EMAIL = "Значение поля \"Адрес электронной почты\" должно быть действительным электронным адресом.";
        public static final String INVALID_PHONE = "Значение поля \"Номер телефона\" должно начинаться с +375 затем код (25|29|33|44) и далее 7 цифр (первая из которых не 0)";
        public static final Map<String, String> ERROR_SIGNUP_MAP = new HashMap<>();
        static {
                ERROR_SIGNUP_MAP.put("REQUIRED_TARGET_ID", REQUIRED_TARGET_ID);
                ERROR_SIGNUP_MAP.put("REQUIRED_PHONE", REQUIRED_PHONE);
                ERROR_SIGNUP_MAP.put("REQUIRED_NAME", REQUIRED_NAME);
                ERROR_SIGNUP_MAP.put("REQUIRED_SURNAME", REQUIRED_SURNAME);
                ERROR_SIGNUP_MAP.put("REQUIRED_PASSWORD", REQUIRED_PASSWORD);
                ERROR_SIGNUP_MAP.put("INVALID_NAME_LENGTH", INVALID_NAME_LENGTH);
                ERROR_SIGNUP_MAP.put("INVALID_SURNAME_LENGTH", INVALID_SURNAME_LENGTH);
                ERROR_SIGNUP_MAP.put("INVALID_NAME_CHARACTERS", INVALID_NAME_CHARACTERS);
                ERROR_SIGNUP_MAP.put("INVALID_SURNAME_CHARACTERS", INVALID_SURNAME_CHARACTERS);
                ERROR_SIGNUP_MAP.put("INVALID_PASSWORD_LENGTH", INVALID_PASSWORD_LENGTH);
                ERROR_SIGNUP_MAP.put("INVALID_PASSWORD_NO_SMALL", INVALID_PASSWORD_NO_SMALL);
                ERROR_SIGNUP_MAP.put("INVALID_PASSWORD_NO_BIG", INVALID_PASSWORD_NO_BIG);
                ERROR_SIGNUP_MAP.put("INVALID_TARGET_ID", INVALID_TARGET_ID);
                ERROR_SIGNUP_MAP.put("INVALID_EMAIL", INVALID_EMAIL);
                ERROR_SIGNUP_MAP.put("INVALID_PHONE", INVALID_PHONE);
        }
}

