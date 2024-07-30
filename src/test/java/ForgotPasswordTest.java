import base.DriverManagement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base.DriverManagement.getDriver;
import static base.DriverManagement.switchToTab;

public class ForgotPasswordTest extends BaseTest {
    String[] str = username.split("@");
    String mailName = str[0];
    String domainName = str[1];
    String inactiveMail = "olalaa@gmail.com";


    @Test(description = "User can reset new password successfully")
    public void ResetPassword(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.clickForgotPassword();
        String RailwayWindow = getDriver().getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        String MailWindow = getDriver().getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        DriverManagement.switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(loginPage.isTokenDisplayed());
        loginPage.fillResetPasswordForm(password, password);
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "Password changed! Click here to login.";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message displays when entering an account that wasn't registered")
    public void ResetPasswordWithUnRegisteredAccount(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.clickForgotPassword();
        forgotPasswordPage.enterEmailAddress(inactiveMail);
        forgotPasswordPage.clickSendInstructionsBtn();

        String actualMsg = forgotPasswordPage.getErrorMsg();
        String expectedMsg = "This email address doesn't exist.";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message displays when entering an new password and confirm password doesn't match")
    public void ResetPasswordDoesNotMatch(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.clickForgotPassword();
        String RailwayWindow = getDriver().getWindowHandle();

        forgotPasswordPage.enterEmailAddress(username);
        forgotPasswordPage.clickSendInstructionsBtn();

        getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        String MailWindow = getDriver().getWindowHandle();
        mailPage.loginToEmail(mailName, domainName);
        mailPage.resetPassword();

        switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(loginPage.isTokenDisplayed());
        loginPage.fillResetPasswordForm(password, "11111111");
        loginPage.clickResetPasswordBtn();

        String actualMsg = loginPage.getResetMsg();
        String expectedMsg = "Could not reset password. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The reset error message is not the same as expected.");

        String actualConfirmMsg = loginPage.getConfirmPasswordMsg();
        String expectedConfirmMsg = "The password confirmation did not match the new password.";
        Assert.assertEquals(actualConfirmMsg, expectedConfirmMsg,"The confirm password error message is not the same as expected.");
    }
}
