package by.emall.ui.pages;

import by.emall.ui.singleton.Singleton;
import org.openqa.selenium.WebDriver;

public class FavoritesPage {
    private WebDriver driver;
    public FavoritesPage() {
        this.driver = Singleton.getDriver();
    }
    public static final String FAVOURITE_URL ="https://emall.by/favorites";
}
