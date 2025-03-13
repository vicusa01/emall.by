package by.emall.ui;

import by.emall.pages.HomePage;
import by.emall.pages.LoginSmsPage;
import by.emall.pages.base.singleton.Cookies;
import by.emall.pages.base.singleton.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginSmsTest {
    HomePage homePage;
    LoginSmsPage loginPage;
    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage().openPage();
        new Cookies().clickAcceptCookiesButton();
        loginPage = new LoginSmsPage();
    }
    @Test
    void testLoginWithIncorrectPhoneNumber() {
      String actual=  homePage.clickLoginButton().enterPhoneNumber("445673456").clickGetSmsCodeButton().getErrorMessageOfUnexistPhoneViaLogin();
      Assertions.assertEquals("Пожалуйста, убедитесь, что правильно ввели телефон",actual);
    }
    @Test
    void testLoginCorrectPhoneNumber() {
        String actual=  homePage.clickLoginButton().enterPhoneNumber("445853145").clickGetSmsCodeButton().getTitleSmsInputForm();
        Assertions.assertEquals("Введите код",actual);
    }
    @AfterEach
    public void closeSite() {
        Singleton.quit();
    }
}
