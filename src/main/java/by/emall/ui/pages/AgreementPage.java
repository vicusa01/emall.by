package by.emall.ui.pages;

import by.emall.ui.singleton.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgreementPage extends SignUpPage {
    private WebDriver driver;

    public AgreementPage() {
        this.driver = Singleton.getDriver();
    }

    private final By BUTTON_NEXT_LOCATOR = By.xpath("(//span[text()='Далее'])[2]");
    private final By BUTTON_ACCEPT_ALL_LOCATOR = By.xpath("(//i[@class='icon-check-16 icon_icon__HiJyg checkbox_checkbox__icon__E8Iw1'])[9]");
    private final By BUTTON_ACCEPT_AGREEMENT_LOCATOR = By.xpath("//span[text()='Согласен']");

    public SignUpPage acceptAgreement() {
        driver.findElement(BUTTON_NEXT_LOCATOR).click();
        driver.findElement(BUTTON_ACCEPT_ALL_LOCATOR).click();
        driver.findElement(BUTTON_ACCEPT_AGREEMENT_LOCATOR).click();
        return new SignUpPage();
    }

}
