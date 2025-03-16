package by.emall.ui;

import by.emall.ui.enums.CatalogSections;
import by.emall.ui.pages.CatalogPage;
import by.emall.ui.pages.HomePage;
import by.emall.ui.pages.CookiesPage;
import by.emall.ui.singleton.Singleton;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CatalogTest {
    HomePage homePage;
    CatalogPage catalogPage;
    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage().openPage();
        new CookiesPage().clickAcceptCookiesButton();
        catalogPage = new CatalogPage();
    }
    @ParameterizedTest
    @EnumSource(CatalogSections.class)
    @DisplayName("Checking the correctness of the Catalog section titles")
    public void testDisplayCorrectTitleForEachCatalogSection(CatalogSections section) {
        String actualTitle = homePage.clickCatalogButton().clickSectionProductsLink(section).getCatalogTitle(section);
        String expectedTitle = section.getLabel();
        Assertions.assertEquals(expectedTitle, actualTitle, "Заголовок страницы не совпадает с ожидаемым для секции: " + section);
    }

    @ParameterizedTest
    @EnumSource(CatalogSections.class)
    @DisplayName("Checking the correctness of the search")
    public void testDisplayCorrectTitleSearch(CatalogSections section) {
        String actualTitle = homePage.searchProduct(section).getCatalogTitle(section);
        String expectedTitle = "По запросу «"+section.getLabel()+"»";
        Assertions.assertTrue(actualTitle.contains(expectedTitle),
                "Заголовок страницы не содержит ожидаемую строку для секции: " + section +
                        "\nОжидалось: " + expectedTitle +
                        "\nФактический: " + actualTitle);
    }


    @AfterEach
    public void closeSite() {
        Singleton.quit();
    }
}

