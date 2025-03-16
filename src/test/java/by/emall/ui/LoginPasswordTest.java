package by.emall.ui;

import by.emall.ui.pages.HomePage;
import by.emall.ui.pages.LoginPasswordPage;
import by.emall.ui.pages.CookiesPage;
import by.emall.ui.singleton.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginPasswordTest {
    HomePage homePage;
    LoginPasswordPage loginPasswordPage;
    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage().openPage();
        new CookiesPage().clickAcceptCookiesButton();
        loginPasswordPage = new LoginPasswordPage();
    }
//    @ParameterizedTest
//    @CsvFileSource(resources = "/logInData.csv", numLinesToSkip = 1)
//    void testNegativeLogin(String phone, String password,String expectedResult) {
//        String actual=  homePage
//                .clickLoginButton()
//                .clickLoginByPasswordButton()
//                .enterCredentials(phone,password)
//                .clickLoginButton()
//                .getErrorLoginMessage();
//        Assertions.assertEquals(expectedResult,actual);
//    }
    @ParameterizedTest
    @CsvSource({
            "445853145,14041976Dfkbr@",
    })
    void testPositiveLogin(String phone, String password) {
       homePage
                .clickLoginButton()
                .clickLoginByPasswordButton()
                .enterCredentials(phone,password)
                .clickLoginButton();
        Assertions.assertEquals("https://emall.by/",Singleton.getUrl());
    }
    @AfterEach
    public void closeSite() {
        Singleton.quit();
    }
}
