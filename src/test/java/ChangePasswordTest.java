import base.DriverManagement;
import enums.Tab;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest{
    String newPassword = "12345678";
    String[] str = username.split("@");
    String mailName = str[0];
    String domainName = str[1];

    @Test(description = "User can change password")
    public void ChangePassword(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.CHANGE_PASSWORD);
        changePasswordPage.submitChangePasswordForm(password, newPassword, newPassword);
        String actualMsg = changePasswordPage.getSuccessMsg();
        String expectedMsg = "Your password has been updated!";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message is display when user enter invalid current password")
    public void ChangePasswordWithWrongInvalidPassword(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.CHANGE_PASSWORD);
        changePasswordPage.submitChangePasswordForm("password", newPassword, newPassword);
        String actualMsg = changePasswordPage.getErrorMsg();
        String expectedMsg = "An error occurred when attempting to change the password. Maybe your current password is incorrect.";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "Error message is display when user enter info with confirm password does not same")
    public void ChangePasswordWithPasswordInconsistent(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.CHANGE_PASSWORD);
        changePasswordPage.submitChangePasswordForm(password, newPassword, "12345");
        String actualMsg = changePasswordPage.getErrorMsg();
        String expectedMsg = "Password change failed. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

}
