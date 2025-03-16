package by.emall.ui.pages;

import by.emall.ui.singleton.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPasswordPage {
    private WebDriver driver;

    public LoginPasswordPage() {
        this.driver = Singleton.getDriver();
    }

    private final By INPUT_PHONE_LOCATOR = By.xpath("//input[@type=\"tel\"]");
    private final By INPUT_PASSWORD_LOCATOR = By.xpath("//input[@type=\"password\"]");
    private final By CLICK_LOGIN_BUTTON_LOCATOR = By.xpath("//button[@type=\"submit\"]");
    private final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[@class='password_error_message__KRNDP']");

    public String getErrorLoginMessage() {
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();
    }
    private void enterCredentialsInFields(String phoneNumber, String password) {
        WebElement inputPhoneElement = driver.findElement(INPUT_PHONE_LOCATOR);
        WebElement inputPasswordElement = driver.findElement(INPUT_PASSWORD_LOCATOR);

        inputPhoneElement.sendKeys(phoneNumber);
        inputPasswordElement.sendKeys(password);
    }

    public LoginPasswordPage enterCredentials(String phoneNumber, String password) {
        try {
            enterCredentialsInFields(phoneNumber, password);
        } catch (StaleElementReferenceException e) {
            enterCredentialsInFields(phoneNumber, password);
        }

        return this;
    }

    public LoginPasswordPage clickLoginButton() {
        driver.findElement(CLICK_LOGIN_BUTTON_LOCATOR).click();
        return this;
    }
}
