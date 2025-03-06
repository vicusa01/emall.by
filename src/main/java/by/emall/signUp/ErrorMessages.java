package by.emall.signUp;

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
    }

