package by.emall.pages;

import by.emall.pages.base.singleton.Singleton;
import org.openqa.selenium.WebDriver;

public class FavoritesPage {
    private WebDriver driver;
    public FavoritesPage() {
        this.driver = Singleton.getDriver();
    }
    public static final String FAVOURITE_URL ="https://emall.by/favorites";
}
