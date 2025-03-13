package by.emall.pages.base.singleton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cookies {
    private  WebDriver driver;
    private final By ACCEPT_COOKIES_BUTTON_LOCATOR = By.cssSelector(".btn_btn__QQYBS.btn_btn_colour_black__V_2g5.btn_btn_size_medium__oY0HW.cookies_button__qWM6Y");
    public Cookies() {
        this.driver = Singleton.getDriver();
    }
    public void clickAcceptCookiesButton() {
        driver.findElement(ACCEPT_COOKIES_BUTTON_LOCATOR).click();
    }
}
