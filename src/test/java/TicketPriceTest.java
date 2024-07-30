import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import enums.Tab;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TicketPriceTest extends BaseTest{
    @Test(description = "System navigates to Login page when not logged in yet")
    public void CheckPriceWithoutLogin(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.TICKET_PRICE);
        ticketPricePage.clickCheckPriceBtn(Locations.SAI_GON, Locations.PHAN_THIET);
        String actualTitle = ticketPricePage.getTitlePage();
        String expectedTitle = "Ticket Price";
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
    }

    @Test(description = "System navigates to Login page when not logged in yet")
    public void RedirectToLogin(){
        DriverManagement.openRailwayPage();
        homePage.openTab(Tab.TICKET_PRICE);
        ticketPricePage.clickCheckPriceBtn(Locations.SAI_GON, Locations.PHAN_THIET);
        ticketPricePage.selectSeatType(SeatType.SSC);
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "The system is not navigate to login page as expected.");
    }

    @Test(description = "User can open Book ticket page by clicking on Book ticket link in Ticket price page")
    public void BookTicketFromTicketPrice(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);
        homePage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.SAI_GON, Locations.PHAN_THIET, "TicketPrice");
        ticketPricePage.selectSeatType(SeatType.HB);
        SoftAssert softAssert = new SoftAssert();
        String actualTitle = bookTicketPage.getTitlePage();
        String expectedTitle = "Book ticket";
        softAssert.assertEquals(actualTitle, expectedTitle,"The title of the page is not as expected.");
        softAssert.assertTrue(bookTicketPage.isBookTicketFormDisplayed(), "The system is not navigate to book ticket page as expected.");
        softAssert.assertAll();
    }
}
