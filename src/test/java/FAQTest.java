import base.DriverManagement;
import enums.Tab;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FAQTest extends BaseTest{
    @Test(description = "User can navigate to Register page when clicking the hyperlink")
    public void RedirectFollowHyperlink(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.FAQ);
        faqPage.clickCreateAnAccountLink();

        SoftAssert softAssert = new SoftAssert();
        String actualTitle = homePage.getTitlePage();
        String expectedTitle = "Create account";
        softAssert.assertEquals(actualTitle, expectedTitle,"The system is not navigate to register page as expected.");
        softAssert.assertTrue(registerPage.isRegisterFormDisplayed());

        registerPage.openTab(Tab.HOME);
        homePage.clickCreateAnAccountLink();
        String actualTitle1 = homePage.getTitlePage();
        String expectedTitle1 = "Create account";
        softAssert.assertEquals(actualTitle1, expectedTitle1,"The system is not navigate to register page as expected.");
        softAssert.assertTrue(registerPage.isRegisterFormDisplayed());

        softAssert.assertAll();
    }

    @Test(description = "System navigates to Login page when not logged in yet")
    public void RedirectFollowHyperlinkWithoutLogin(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.FAQ);
        faqPage.clickBookTicketLink();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "The system is not navigate to login page as expected.");
    }

    @Test(description = "User can navigates to Book Ticket page when clicking the hyperlink")
    public void RedirectToBookTicketPage(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.FAQ);
        faqPage.clickBookTicketLink();
        SoftAssert softAssert = new SoftAssert();
        String actualTitle = bookTicketPage.getTitlePage();
        String expectedTitle = "Book ticket";
        softAssert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
        softAssert.assertTrue(bookTicketPage.isBookTicketFormDisplayed(), "The system is not navigate to book ticket page as expected.");
        softAssert.assertAll();
    }
}
