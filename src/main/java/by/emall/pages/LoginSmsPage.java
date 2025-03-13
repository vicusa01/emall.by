package by.emall.pages;

import by.emall.pages.base.singleton.Singleton;
import by.emall.pages.base.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class LoginSmsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(LoginSmsPage.class);


    public LoginSmsPage() {
        this.driver = Singleton.getDriver();
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By INPUT_PHONE_LOCATOR = By.className("new-input-phone_input__XWAH5");
    private final By BUTTON_GET_SMS_CODE_LOCATOR = By.xpath("//button[@type='submit']");
    private final By BUTTON_LOGIN_BY_PASSWORD_LOCATOR = By.xpath("(//button[@type='button'])[1]");
    private final By ERROR_NOT_EXIST_PHONE_MESSAGE_LOCATOR = By.className("new-input-phone_message__H_yAc");
    private final By TITLE_SMS_INPUT_LOCATOR = By.xpath("//h1[contains(string(.),'код')]");

    public LoginPasswordPage clickLoginByPasswordButton() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_LOGIN_BY_PASSWORD_LOCATOR));
            button.click();
            logger.info("Clicked on the login by password button.");
            return new LoginPasswordPage();
        } catch (StaleElementReferenceException e) {
            logger.warn("StaleElementReferenceException encountered, retrying...");

            try {
                WebElement button = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(BUTTON_LOGIN_BY_PASSWORD_LOCATOR)));
                button.click();
                logger.info("Successfully retried and clicked the button.");
                return new LoginPasswordPage();
            } catch (Exception e2) {
                logger.error("Failed to click on the login button after retrying: {}", e.getMessage());
                return null;
            }
        } catch (Exception e) {
            logger.error("Unexpected error clicking login by password button: {}", e.getMessage());
            return null;
        }
    }

    public LoginSmsPage clickGetSmsCodeButton() {
        WebElement buttonGetSmsCode = driver.findElement(BUTTON_GET_SMS_CODE_LOCATOR);
        buttonGetSmsCode.click();
        return this;
    }

    public LoginSmsPage enterPhoneNumber(String phoneNumber) {
        WebElement inputPhoneElement = driver.findElement(INPUT_PHONE_LOCATOR);
        inputPhoneElement.sendKeys(phoneNumber);
        return this;
    }

    public String getErrorMessageOfUnexistPhoneViaLogin() {
        return driver.findElement(ERROR_NOT_EXIST_PHONE_MESSAGE_LOCATOR).getText();
    }

    public String getTitleSmsInputForm() {
        return driver.findElement(TITLE_SMS_INPUT_LOCATOR).getText();
    }

}
