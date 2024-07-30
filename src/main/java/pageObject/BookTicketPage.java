package pageObject;

import models.Ticket;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class BookTicketPage extends BasePage{
    private String sltValue = "//*[@id='content']//select[@name='%s']";

    private By formBookTicket = By.xpath("//form/fieldset/legend[text()='Book ticket form']");
    private By btnBookTicket = By.xpath("//form//input[@type = 'submit']");
    private By msgError = By.xpath("//*[@id='content']//p[@class='message error']");
    public boolean isBookTicketFormDisplayed(){
        return isDisplayed(formBookTicket);
    }
    public void selectInfor(String item, String information){
        By selectItem = By.xpath(String.format(sltValue, item));
        enter(selectItem, information);
    }

    public void bookTicket(Ticket ticket){
        selectInfor("DepartStation", ticket.getDepartFrom().getValueLocation());
        selectInfor("Date", ticket.getDepartDate());
        selectInfor("SeatType", ticket.getSeatType().getValueSeatType());
        selectInfor("TicketAmount", ticket.getAmount());
        selectInfor("ArriveStation", ticket.getArriveAt().getValueLocation());
    }

    public void clickBookTicketButton(){
        scrollToView(btnBookTicket);
        click(btnBookTicket);
    }

    public String getErrorMsg(){
        return getText(msgError);
    }

}
