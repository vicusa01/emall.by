package by.emall.api;

import by.emall.api.profile.Profile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class ProfileTest {
    @BeforeAll
    public static void getToken() {
        Token.getTokenAndCookies();
    }

    @Test
    @DisplayName("View profile")
    public void testViewProfile() {
        Profile.getViewProfileRequest()
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("user.id", equalTo(4441203));
    }

    @Test
    @DisplayName("Edit personal info")
    public void testEditPersonalInfo() {
        Profile.putEditPersonalInfoRequest("Вкаукв", "Цыглер", "Вал", "tet@mail.ru", "375445853145")
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("id", equalTo(4441203));
    }
}
