import base.DriverManagement;
import base.StaticProvider;
import enums.Tab;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.LoginPage;

public class LogoutTest extends BaseTest {
    @Test(description = "User can logout successfully")
    public void LogOut() {
        HomePage homePage = new HomePage();
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        LoginPage loginPage = new LoginPage();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.LOGOUT);

        SoftAssert softAssert = new SoftAssert();
        String actualMsg = homePage.getWelcomeMsg();
        String expectedMsg = "Welcome guest!";
        softAssert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
        softAssert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab is exist");
        softAssert.assertAll();
    }
}
