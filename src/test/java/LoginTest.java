import base.DriverManagement;
import enums.Tab;
import models.User;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MailPage;
import pageObject.RegisterPage;

import static base.DriverManagement.getDriver;

public class LoginTest extends BaseTest {
    String blankUsername = "";
    String invalidPassword = "11111111";
    String pid = "12345678";
    String specialCharacterMail = "specialmail#*@gmail.com";
//    String pid = "12345678";
//    String newEmail = "helloselenium@gmail.com";
//    String validMail = "testselenium@gmail.com";
//    String blankPassword = "";
//    String password65characters = "00000000000000000000000000000000000000000000000000000000000000000";
//    String emptyPassword = "";
    User specialUser = new User(specialCharacterMail, password);
    User validUser = new User(username, password);
    User blankUser = new User(blankUsername, password);
    User invalidUser = new User(username, invalidPassword);

    @Test(description = "User can log into Railway with valid account")
    public void LoginWithValidAccount(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        String actualMsg = homePage.getWelcomeMsg();
        String expected = "Welcome " + username;
        Assert.assertEquals(actualMsg, expected, "The welcome message is not the same as expected.");
    }

    @Test(description = "User can navigate to Login page from Register page")
    public void navigateToRegisterPage(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.LOGIN);
        loginPage.clickRegisterHyperlink();
        Assert.assertTrue(registerPage.isRegisterFormDisplayed(), "The system is not navigate to login page as expected.");
    }

    @Test(description = "User can navigate to Forgot password page")
    public void navigateToForgotPasswordPage(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.LOGIN);
        loginPage.clickForgotPasswordHyperlink();
        Assert.assertTrue(loginPage.isChangePasswordFormDisplayed(), "The system is not navigate to login page as expected.");
    }

    @Test(description = "Error message displays when logging in with an account that has not been registered")
    public void LoginWithInactiveAccount(){
        DriverManagement.openMailPage();
        String email = mailPage.getEmail();
        User newUser = new User(email, password, pid);

        getDriver().switchTo().newWindow(WindowType.TAB);

        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);
        registerPage.fillRegisterForm(newUser);
        registerPage.clickBtnRegister();

        registerPage.openLoginTab();
        loginPage.submitLoginForm(newUser);

        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "Error message displays when logging in with an invalid username")
    public void LoginWithInvalidUsername(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(specialUser);
        String expectedMsg = "Username does not contain special characters";
        String actualMsg = loginPage.getErrorMsg();

        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "Error message displays when logging in with incorrect password")
    public void LoginWithInvalidPassword(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(invalidUser);
        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "System shows message when user enters wrong password many times")
    public void LoginInSeveralTimes(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();

        loginPage.loginInSeveralTime(invalidUser, 4);

        String actualMsg = loginPage.getErrorMsg();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }

    @Test(description = "The system navigate to Home page when the user login successful")
    public void NavigateToHomePageAfterLogin(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);

        String actualMsg = homePage.getWelcomeMsg();
        String expected = "Welcome " + username;
        Assert.assertEquals(actualMsg, expected, "The welcome message is not the same as expected.");
    }
}
