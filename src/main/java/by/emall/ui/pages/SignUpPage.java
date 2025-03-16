package by.emall.ui.pages;

import by.emall.ui.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(SignUpPage.class);

    public SignUpPage() {
        this.driver = Singleton.getDriver();
    }

    private final By INPUT_NAME_LOCATOR = By.xpath("//input[@name='name']");
    private final By INPUT_SURNAME_LOCATOR = By.xpath("//input[@name='surname']");
    private final By INPUT_PATRONYMIC_LOCATOR = By.xpath("//input[@name='patronymic']");
    private final By INPUT_PHONE_LOCATOR = By.xpath("//input[@name='phone']");
    private final By INPUT_EMAIL_LOCATOR = By.xpath("//input[@name='email']");
    private final By INPUT_PASSWORD_LOCATOR = By.xpath("//input[@name='password']");
    private final By INPUT_PASSWORD_REPEAT_LOCATOR = By.xpath("//input[@name='passwordRepeat']");
    private final By CHECKBOX_PERSONAL_DATA_LOCATOR = By.xpath("(//span[@class='checkbox_checkbox__pseudo__c2vTZ']/i)[4]");
    private final By BUTTON_NEXT_LOCATOR = By.xpath("//button[contains(@class, 'btn_btn__QQYBS')]");
    private final By ERROR_NAME_LOCATOR = By.xpath("//div[@class='input_input_wrapper__Qa21g input_input_wrapper_error__qEL4H']//div[contains(@class, 'input_message__veX8F') and text()='Имя обязательно для заполнения']");
    private final By ERROR_SURNAME_LOCATOR = By.xpath("//div[@class='input_input_wrapper__Qa21g input_input_wrapper_error__qEL4H']//div[contains(@class, 'input_message__veX8F') and text()='Фамилия обязательна для заполнения']");
    private final By ERROR_PATRONYMIC_LOCATOR = By.xpath("//div[@class='input_input_wrapper__Qa21g input_input_wrapper_error__qEL4H']//div[contains(@class, 'input_message__veX8F') and text()='Отчество обязательно для заполнения']");
    private final By ERROR_PHONE_LOCATOR = By.xpath("//div[@class='new-input-phone_input_wrapper__cjYkb new-input-phone_input_wrapper_error__gpYqX']//div[contains(@class, 'new-input-phone_message__H_yAc') and text()='Номер телефона введён некорректно']");
    private final By ERROR_EMAIL_LOCATOR = By.xpath("//div[@class='input_input_wrapper__Qa21g input_input_wrapper_error__qEL4H']//div[contains(@class, 'input_message__veX8F') and text()='Email обязателен для заполнения']");
    private final By ERROR_PASSWORD_LOCATOR = By.xpath("//div[@class='registration_error___J4Zl' and text()='Создание пароля обязательно']");
    private final By ERROR_CONSENT_LOCATOR = By.xpath("//div[@class='registration_error___J4Zl' and text()='Необходимо согласие']");


    private void enterTextInPasswordFields(String password, String passwordRepeat) {
        WebElement inputPasswordElement = driver.findElement(INPUT_PASSWORD_LOCATOR);
        WebElement inputPasswordRepeatElement = driver.findElement(INPUT_PASSWORD_REPEAT_LOCATOR);

        inputPasswordElement.sendKeys(password);
        inputPasswordRepeatElement.sendKeys(passwordRepeat);
    }

    public SignUpPage enterPasswordCredentials(String password, String passwordRepeat) {
        try {
            enterTextInPasswordFields(password, passwordRepeat);
        } catch (StaleElementReferenceException e) {
            enterTextInPasswordFields(password, passwordRepeat);
        }

        return this;
    }

    public AgreementPage checkPersonalDataCheckbox() {
        driver.findElement(CHECKBOX_PERSONAL_DATA_LOCATOR).click();
        return new AgreementPage();
    }


    public void clickNextButton() {
        driver.findElement(BUTTON_NEXT_LOCATOR).click();
    }

    public SignUpPage enterPersonalInfo(String name, String surname, String patronymic, String phone, String email) {

        try {
            fullPersonalInfo(name, surname, patronymic, phone, email);

        } catch (StaleElementReferenceException e) {
            fullPersonalInfo(name, surname, patronymic, phone, email);
        }

        return this;
    }

    private void fullPersonalInfo(String name, String surname, String patronymic, String phone, String email) {
        WebElement inputNameElement;
        WebElement inputSurnameElement;
        WebElement inputPatronymicElement;
        WebElement inputPhoneElement;
        WebElement inputEmailElement;
        inputNameElement = driver.findElement(INPUT_NAME_LOCATOR);
        inputSurnameElement = driver.findElement(INPUT_SURNAME_LOCATOR);
        inputPatronymicElement = driver.findElement(INPUT_PATRONYMIC_LOCATOR);
        inputPhoneElement = driver.findElement(INPUT_PHONE_LOCATOR);
        inputEmailElement = driver.findElement(INPUT_EMAIL_LOCATOR);

        inputNameElement.sendKeys(name);
        inputSurnameElement.sendKeys(surname);
        inputPatronymicElement.sendKeys(patronymic);
        inputPhoneElement.sendKeys(phone);
        inputEmailElement.sendKeys(email);
    }

}
