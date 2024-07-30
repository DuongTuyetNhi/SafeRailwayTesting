import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import enums.Tab;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TimetableTest extends BaseTest{
    @Test(description = "User can check price when not logged in yet")
    public void CheckPriceTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.DA_NANG, Locations.SAI_GON, "TicketPricePage");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ticketPricePage.doesTitleExist());

        String actualMsg = ticketPricePage.getTicketInfor();
        String expectedMsg = "Ticket price from Đà Nẵng to Sài Gòn";
        softAssert.assertEquals(actualMsg, expectedMsg, "The ticket table displays incorrectly.");

        String actualPriceOfHS = ticketPricePage.getPriceBySeatType("HS");
        String actualPriceOfSS = ticketPricePage.getPriceBySeatType("SS");
        String actualPriceOfSSC = ticketPricePage.getPriceBySeatType("SSC");
        String actualPriceOfHB = ticketPricePage.getPriceBySeatType("HB");
        String actualPriceOfSB = ticketPricePage.getPriceBySeatType("SB");
        String actualPriceOfSBC = ticketPricePage.getPriceBySeatType("SBC");

        String expectedPriceOfHS = "310000";
        String expectedPriceOfSS = "335000";
        String expectedPriceOfSSC = "360000";
        String expectedPriceOfHB = "410000";
        String expectedPriceOfSB = "460000";
        String expectedPriceOfSBC = "510000";

        softAssert.assertEquals(actualPriceOfHS, expectedPriceOfHS,"The price of HS is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSS, expectedPriceOfSS,"The price of SS is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSSC, expectedPriceOfSSC,"The price of SSC is not the same as expected.");
        softAssert.assertEquals(actualPriceOfHB, expectedPriceOfHB,"The price of HB is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSB, expectedPriceOfSB,"The price of SB is not the same as expected.");
        softAssert.assertEquals(actualPriceOfSBC, expectedPriceOfSBC,"The price of SBC is not the same as expected.");

        softAssert.assertAll();
    }

    @Test(description = "User can check price when not logged in yet")
    public void TimetableRedirectToLogin(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.SAI_GON, Locations.PHAN_THIET, "BookTicket");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isLoginFormDisplayed(), "The system is not navigate to login page as expected.");
        loginPage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.SAI_GON, Locations.PHAN_THIET, "TicketPrice");
        ticketPricePage.selectSeatType(SeatType.HS);
        softAssert.assertTrue(loginPage.isLoginFormDisplayed(), "The system is not navigate to login page as expected.");
        softAssert.assertAll();
    }

    @Test(description = "User can open Book ticket page by clicking on Book ticket link in Ticket price page")
    public void BookTicketFromTimetable(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.SAI_GON, Locations.PHAN_THIET, "BookTicket");
        SoftAssert softAssert = new SoftAssert();
        String actualTitle = bookTicketPage.getTitlePage();
        String expectedTitle = "Book ticket";
        softAssert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
        softAssert.assertTrue(bookTicketPage.isBookTicketFormDisplayed(), "The system is not navigate to book ticket page as expected.");

        loginPage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.SAI_GON, Locations.PHAN_THIET, "TicketPrice");
        ticketPricePage.selectSeatType(SeatType.HS);
        String actualTitle2 = bookTicketPage.getTitlePage();
        String expectedTitle2 = "Book ticket";
        softAssert.assertEquals(actualTitle2, expectedTitle2,"The title of the page is not as expected.");

        softAssert.assertTrue(bookTicketPage.isBookTicketFormDisplayed(), "The system is not navigate to book ticket page as expected.");
        softAssert.assertAll();
    }
}
