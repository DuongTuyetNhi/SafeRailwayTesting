import base.DriverManagement;
import base.StaticProvider;
import enums.Tab;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.*;

public class HomePageTest extends BaseTest{
    @Test(description = "User can navigate to Home page")
    public void navigateHomeTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.HOME);
        String actualTitle = homePage.getTitlePage();
        String expectedTitle = "Welcome to Safe Railway";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "User can navigate to FAQ page")
    public void navigateFAQTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.FAQ);
        String actualTitle = faqPage.getTitlePage();
        String expectedTitle = "Frequently Asked Questions";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "User can navigate to Contact page")
    public void navigateContactTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.CONTACT);
        String actualTitle = contactPage.getTitlePage();
        String expectedTitle = "Contact Information";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "User can navigate to Timetable page")
    public void navigateTimeTableTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.TIMETABLE);
        String actualTitle = timetablePage.getTitlePage();
        String expectedTitle = "Train Timetable";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "User can navigate to Ticket price page")
    public void navigateTicketPriceTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.TICKET_PRICE);
        String actualTitle = ticketPricePage.getTitlePage();
        String expectedTitle = "Train ticket price list";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "User can navigate to Register page")
    public void navigateRegisterTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.REGISTER);

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

    @Test(description = "User can navigate to Login page")
    public void navigateLoginTest(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.LOGIN);
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "The system is not navigate to login page as expected.");
    }

    @Test(description = "User can navigate to Book ticket page")
    public void navigateBookTicketTest(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.BOOK_TICKET);

        SoftAssert softAssert = new SoftAssert();

        String actualTitle = bookTicketPage.getTitlePage();
        String expectedTitle = "Book ticket";
        softAssert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");

        softAssert.assertTrue(bookTicketPage.isBookTicketFormDisplayed(), "The system is not navigate to book ticket page as expected.");
    }

    @Test(description = "User can navigate to My ticket page")
    public void navigateMyTicketTest(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.MY_TICKET);
        String actualTitle = myTicketPage.getTitlePage();
        String expectedTitle = "Manage Tickets";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "User can navigate to Change password page")
    public void navigateChangePasswordTest(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.CHANGE_PASSWORD);
        Assert.assertTrue(changePasswordPage.isChangePasswordFormDisplayed(), "The system is not navigate to change password page as expected.");
    }

    @Test(description = "User is redirected to Home page after logging out", dataProvider = "userDataProvider", dataProviderClass = StaticProvider.class)
    public void logOutTest(User validUser) {
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);
        homePage.openTab(Tab.LOGOUT);

        SoftAssert softAssert = new SoftAssert();
        String actualMsg = homePage.getWelcomeMsg();
        String expectedMsg = "Welcome guest!";
        softAssert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
        softAssert.assertFalse(homePage.isLogoutTabPresent(), "Logout tab is exist");
        softAssert.assertAll();
    }
}
