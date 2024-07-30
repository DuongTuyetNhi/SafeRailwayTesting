import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import enums.Tab;
import models.Ticket;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.*;

import static base.DateUtils.getDateAdd;

public class BookTicketTest extends BaseTest {

    @Test(description = "User can book ticket")
    public void BookTicket(){
        Ticket ticket1 = new Ticket(getDateAdd(12), Locations.NHA_TRANG, Locations.HUE, SeatType.SBC, "2");

        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        homePage.openTab(Tab.BOOK_TICKET);
        bookTicketPage.bookTicket(ticket1);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
        Assert.assertTrue(successPage.isCorrectInforTicket(ticket1));
    }

    @Test(description = "Error message displays when booking more than 10 tickets")
    public void BookManyTicket(){
        Ticket ticket2 = new Ticket(getDateAdd(25), Locations.NHA_TRANG, Locations.SAI_GON, SeatType.SSC, "10");
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        homePage.openTab(Tab.BOOK_TICKET);
        bookTicketPage.bookTicket(ticket2);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = bookTicketPage.getErrorMsg();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Message display is not same");
    }

    @Test(description = "Error message displays when selecting a ticket where 'Depart from' and 'Arrive at' fields do match")
    public void BookTicketSameLocation(){
        Ticket ticket3 = new Ticket(getDateAdd(12), Locations.DA_NANG, Locations.DA_NANG, SeatType.SBC, "2");

        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        homePage.openTab(Tab.BOOK_TICKET);
        bookTicketPage.bookTicket(ticket3);
        bookTicketPage.clickBookTicketButton();

        String actualMsg = bookTicketPage.getErrorMsg();
        String expectedMsg = "Depart and Arrive stations can not be the same.";
        Assert.assertEquals(actualMsg, expectedMsg, "The actual message is not the same as expected.");
    }

    @Test(description = "User can book ticket from Timetable")
    public void BookTicketFromTimeTable(){
        Ticket ticket4 = new Ticket(getDateAdd(10), Locations.QUANG_NGAI, Locations.HUE, SeatType.HS, "2");

        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        homePage.openTab(Tab.TIMETABLE);
        timetablePage.selectFunction(Locations.QUANG_NGAI, Locations.HUE, "BookTicketPage");

        String dateNext10 = getDateAdd(10);
        bookTicketPage.selectInfor("Date",dateNext10);
        bookTicketPage.selectInfor("TicketAmount", "2");
        bookTicketPage.clickBookTicketButton();

        SoftAssert softAssert = new SoftAssert();
        String actualMsg = successPage.getSuccessfulMsg();
        String expectedMsg = "Ticket booked successfully!";
        softAssert.assertEquals(actualMsg,expectedMsg,"Fail");
        softAssert.assertTrue(successPage.isCorrectInforTicket(ticket4));
        softAssert.assertAll();
    }
}
