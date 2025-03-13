package by.emall.pages;

import by.emall.enums.CatalogSections;
import by.emall.pages.base.singleton.Singleton;
import by.emall.pages.base.utils.XPathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    private final By CATALOG_BUTTON_LOCATOR = By.className("catalog_button__text__0l2Oi");
    private final By INPUT_SEARCH_LOCATOR = By.className("web_search__input__YGpod");
    private final By LOGIN_BUTTON_LOCATOR = By.xpath("//div[@class='actions_actions__my7L3']/div[1]/a");
    private final By ORDERS_BUTTON_LOCATOR = By.xpath("//div[@class='actions_actions__my7L3']/div[2]/a");
    private final By FAVORITES_BUTTON_LOCATOR = By.xpath("//div[@class='actions_actions__my7L3']/div[3]/a");
    private final By CART_BUTTON_LOCATOR = By.xpath("//div[@class='actions_actions__my7L3']/div[4]/a");

    private final String URL = "https://emall.by/";
    private WebDriver driver;

    public HomePage() {
        this.driver = Singleton.getDriver();
    }

    public HomePage openPage() {
        logger.info("Открытие главной страницы: {}", URL);
        driver.get(URL);
        return this;
    }

    public HomePage clickCatalogButton() {
        try {
            driver.findElement(CATALOG_BUTTON_LOCATOR).click();
            logger.info("Клик по кнопке 'Каталог'");
        } catch (NoSuchElementException e) {
            logger.warn("Не найден элемент кнопки 'Каталог'", e);
        }
        return this;
    }

    public CatalogPage searchProduct(CatalogSections searchSection) {
        try {
            WebElement element = driver.findElement(INPUT_SEARCH_LOCATOR);
            element.click();
            element.clear();
            element.sendKeys(searchSection.getLabel());
            element.sendKeys(Keys.ENTER);
            logger.info("Выполнен поиск: {}", searchSection.getLabel());
        } catch (Exception e) {
            logger.error("Ошибка при поиске товара", e);
        }
        return new CatalogPage();
    }

    public LoginSmsPage clickLoginButton() {
        return clickButton(LOGIN_BUTTON_LOCATOR, "Кнопка входа", LoginSmsPage.class);
    }

    public OrdersPage clickOrdersButton() {
        return clickButton(ORDERS_BUTTON_LOCATOR, "Кнопка заказов", OrdersPage.class);
    }

    public FavoritesPage clickFavoritesButton() {
        return clickButton(FAVORITES_BUTTON_LOCATOR, "Кнопка избранного", FavoritesPage.class);
    }

    public CartPage clickCartButton() {
        return clickButton(CART_BUTTON_LOCATOR, "Кнопка корзины", CartPage.class);
    }

    public CatalogPage clickSectionProductsLink(CatalogSections category) {
        By SECTION_PRODUCTS_LINK_LOCATOR = By.xpath(XPathUtil.getXPathByCategory(category));
        try {
            driver.findElement(SECTION_PRODUCTS_LINK_LOCATOR).click();
            logger.info("Клик по секции каталога: {}", category);
        } catch (NoSuchElementException e) {
            logger.warn("Секция каталога '{}' не найдена", category, e);
        }
        return new CatalogPage();
    }

    private <T> T clickButton(By locator, String buttonName, Class<T> pageClass) {
        try {
            driver.findElement(locator).click();
            logger.info("Клик по '{}'", buttonName);
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchElementException e) {
            logger.warn("'{}' не найдена", buttonName, e);
        } catch (Exception e) {
            logger.error("Ошибка при переходе на страницу после клика по '{}'", buttonName, e);
        }
        return null;
    }
}
