import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import enums.Tab;
import models.Ticket;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base.DateUtils.getDateAdd;

public class MyTicketTest extends BaseTest{
    String departDate = getDateAdd(25);
    Ticket ticket = new Ticket(departDate, Locations.SAI_GON, Locations.PHAN_THIET, SeatType.HB, "2");
    @Test(description = "User can cancel a ticket")
    public void CancelTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        homePage.openTab(Tab.BOOK_TICKET);
        bookTicketPage.bookTicket(ticket);
        bookTicketPage.clickBookTicketButton();

        successPage.openTab(Tab.MY_TICKET);
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        Assert.assertTrue(myTicketPage.isTicketDisappeared(ticket),"The ticket does not disappear.");
    }

    @Test(description = "User can delete a ticket")
    public void DeleteTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        successPage.openTab(Tab.MY_TICKET);
        myTicketPage.deleteTicket(ticket);
        myTicketPage.confirmCancel();
        Assert.assertTrue(myTicketPage.isTicketDisappeared(ticket),"The ticket does not disappear.");
    }

    @Test(description = "Users can filter tickets by depart date.")
    public void FilterTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        successPage.openTab(Tab.MY_TICKET);
        myTicketPage.filterByDepartDate("7/20/2024");
        myTicketPage.clickApplyFilterBtn();
    }

    @Test(description = "Error message displays when no ticket match the user's filter conditions")
    public void FilterTicketWithConditionDoesNotMatch(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(user);

        successPage.openTab(Tab.MY_TICKET);
        myTicketPage.filterByDepartDate("7/20/2025");
        myTicketPage.clickApplyFilterBtn();

        String actualMsg = myTicketPage.getErrorMsg();
        String expectedMsg = "Sorry, can't find any results that match your filters.\n Please change the filters and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "The error message is not the same as expected.");
    }
}
