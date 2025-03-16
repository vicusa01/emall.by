package by.emall.ui;

import by.emall.pages.FavoritesPage;
import by.emall.pages.HomePage;
import by.emall.pages.LoginPasswordPage;
import by.emall.pages.base.singleton.Cookies;
import by.emall.pages.base.singleton.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FavoritesTest {

    HomePage homePage;
    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage().openPage();
        new Cookies().clickAcceptCookiesButton();
    }
    @Test
    void testViewFavorites() {
        homePage.clickFavoritesButton();
        Assertions.assertEquals(FavoritesPage.FAVOURITE_URL,Singleton.getUrl());
    }
    @AfterEach
    public void closeSite() {
        Singleton.quit();
    }
}
