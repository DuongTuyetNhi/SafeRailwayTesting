package pageObject;

import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static base.DriverManagement.*;

public class MyTicketPage extends BasePage{
    private String rowTicketInfo = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s'" +
            " and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' " +
            "and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]";

    private By txtDepartDate = By.xpath("//table//input[@name='FilterDpDate']");
    private By btnApplyFilter = By.xpath("//form//input[@value='Apply filter']");
    private By rowOfMyTable = By.xpath("//table[@class='MyTable']//tbody/tr");
    private By msgError = By.xpath("//*[@id=\"content\"]//div[@class='error message']");
    public void cancelTicket(Ticket ticket) {
        By ticketLocator = By.xpath(String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount()));
        click(ticketLocator);
    }

    public void deleteTicket(Ticket ticket) {
        By ticketLocator = By.xpath(String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount()));
        click(ticketLocator);
    }

    public void confirmCancel() {
        getDriver().switchTo().alert().accept();
    }

    public boolean isTicketDisappeared(Ticket ticket) {
        String ticketLocator = String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ticketLocator)));
    }

    public void filterByDepartDate(String date){
        enter(txtDepartDate, date);
    }

    public void clickApplyFilterBtn(){
        click(btnApplyFilter);
    }

//    public boolean isFilterCorrect(){
//        List<WebElement> row = getDriver().findElements(rowOfMyTable);
//        int rowTotal = row.size();
//        for (int i=1; i<=rowTotal; i++){
//            WebElement check = getDriver().findElement(By.xpath("//table[@class='MyTable']//tbody/tr[\" + i + \"]/td[7]"));
//            scrollToView((By) check);
//
//
//        }
//        return
//    }
    public String getErrorMsg(){
        return getText(msgError);
    }

}
