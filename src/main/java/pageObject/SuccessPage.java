package pageObject;

import models.Ticket;
import org.openqa.selenium.By;

import static base.DriverManagement.getDriver;
import static base.DriverManagement.getText;

public class SuccessPage extends BasePage{
    private String rowTicketInfo = "//table//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]";

    public String getSuccessfulMsg(){
        By msgSuccessful = By.xpath("//*[@id='content']/h1");
        return getText(msgSuccessful);
    }

    public boolean isCorrectInforTicket(Ticket ticket){
        By inforTicket = By.xpath(String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount()));
        return getDriver().findElement(inforTicket).isDisplayed();
    }
}
