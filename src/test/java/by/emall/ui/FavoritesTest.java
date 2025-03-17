package by.emall.ui;

import by.emall.ui.pages.FavoritesPage;
import by.emall.ui.pages.HomePage;
import by.emall.ui.pages.CookiesPage;
import by.emall.ui.singleton.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FavoritesTest {

    HomePage homePage;

    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage().openPage();
        new CookiesPage().clickAcceptCookiesButton();
    }

    @Test
    void testViewFavorites() {
        homePage.clickFavoritesButton();
        Assertions.assertEquals(FavoritesPage.FAVOURITE_URL, Singleton.getUrl());
    }

    @AfterEach
    public void closeSite() {
        Singleton.quit();
    }
}
