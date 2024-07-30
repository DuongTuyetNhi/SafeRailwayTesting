import base.DriverManagement;
import enums.Tab;
import models.User;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.MailPage;
import pageObject.RegisterPage;

import static base.DriverManagement.getDriver;

public class RegisterTest extends BaseTest {
    String blankUsername = "";
    String specialCharacterMail = "specialmail#*@gmail.com";
    String pid = "12345678";
    String newEmail = "helloselenium@gmail.com";
    String validMail = "testselenium@gmail.com";
    String blankPassword = "";
    String password65characters = "00000000000000000000000000000000000000000000000000000000000000000";
    String emptyPassword = "";
    User blankUser = new User(blankUsername, password, pid);
    User specialUser = new User(specialCharacterMail, password, pid);
    User lengthUser = new User(validMail, password65characters, pid);
    User emptyPasswordUser = new User(validMail, emptyPassword, pid);
    User oldAccountUser = new User(username, password, pid);

    @Test(description = "User can register an account with valid info")
    public void RegisterAccount(){
        DriverManagement.openMailPage();
        String mail = mailPage.getEmail();
        User newAccountUser = new User(mail, password, pid);
        String MailWindow = getDriver().getWindowHandle();
        getDriver().switchTo().newWindow(WindowType.TAB);

        DriverManagement.openRailwayPage();
        homePage.clickCreateAnAccountLink();

        String RailwayWindow = getDriver().getWindowHandle();

        registerPage.fillRegisterForm(newAccountUser);
        registerPage.clickBtnRegister();
        Assert.assertTrue(registerPage.isMessageDisplayed());

        getDriver().switchTo().window(MailWindow);
        getDriver().navigate().refresh();

        mailPage.confirmAccount();

        DriverManagement.switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(registerPage.isConfirmMessageDisplayed());
    }

    @Test(description = "User can navigate to Login page from Register page")
    public void navigateToLoginPage(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.clickLoginHyperlink();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "The system is not navigate to login page as expected.");
    }

    @Test(description = "User can navigate to Login page from Register page")
    public void navigateToConfirmationPage(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.clickHereHyperlink();
        Assert.assertTrue(registerPage.isConfirmationFormDisplayed(), "The system is not navigate to login page as expected.");
    }


    @Test(description = "Error message displays when registering with blank email textbox")
    public void RegisterWithBlankEmail(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.fillRegisterForm(blankUser);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message displays when registering with an email containing special characters")
    public void RegisterWithEmailContainingSpecialCharacters(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.fillRegisterForm(specialUser);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message displays when registering an account with password more than 64 characters")
    public void RegisterWithPasswordLength(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.fillRegisterForm(lengthUser);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message displays when registering an account with empty password")
    public void RegisterWithEmptyPassword(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.fillRegisterForm(emptyPasswordUser);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "User cannot create account while password and PID fields are empty")
    public void RegisterWithBlankFields(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        User newUser = new User(newEmail, blankPassword, "");
        registerPage.fillRegisterForm(newUser);
        registerPage.clickBtnRegister();

        SoftAssert softAssert = new SoftAssert();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        softAssert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");

        String passwordActualMsg = registerPage.getValidationPasswordError();
        String passwordExpectedMsg = "Invalid password length.";
        softAssert.assertEquals(passwordActualMsg, passwordExpectedMsg, "The password error message is not the same as expected.");

        String pidActualMsg = registerPage.getValidationPIDError();
        String pidExpectedMsg = "Invalid ID length.";
        softAssert.assertEquals(pidActualMsg, pidExpectedMsg, "The PID error message is not the same as expected.");

        softAssert.assertAll();
    }

    @Test(description = "Error message displays when registering an account with an email that already exists")
    public void RegisterWithUsedEmail(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.fillRegisterForm(oldAccountUser);
        registerPage.clickBtnRegister();

        String actualMsg = registerPage.getErrorMsg();
        String expectedMsg = "This email address is already in use.";

        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }
}
