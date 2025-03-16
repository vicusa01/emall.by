package by.emall.ui;

import by.emall.pages.AgreementPage;
import by.emall.pages.HomePage;
import by.emall.pages.LoginSmsPage;
import by.emall.pages.SignUpPage;
import by.emall.pages.base.singleton.Cookies;
import by.emall.pages.base.singleton.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpTest {

    HomePage homePage;
    LoginSmsPage loginPage;
    SignUpPage signUpPage;
    AgreementPage agreementPage;
    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage().openPage();
        new Cookies().clickAcceptCookiesButton();
        loginPage = new LoginSmsPage();
        signUpPage = new SignUpPage();
        agreementPage = new AgreementPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/signUpData.csv", numLinesToSkip = 1)
    @DisplayName("Test registration form with invalid data")
    public void testInvalidRegistrationFields(String name, String surname, String patronymic, String phone, String email, String password, String repeatPassword,String expectedError) {
      homePage.clickLoginButton().clickSignUpButton().enterPersonalInfo(name,surname,patronymic,phone,email).enterPasswordCredentials(password,repeatPassword).checkPersonalDataCheckbox().acceptAgreement().clickNextButton();
        WebElement nameError = Singleton.getDriver().findElement(By.xpath("//div[contains(text(),'"+expectedError+"')]"));
        assertTrue(nameError.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        Singleton.quit();
    }
}
