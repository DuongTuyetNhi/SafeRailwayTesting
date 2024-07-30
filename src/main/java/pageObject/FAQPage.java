package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.click;
import static base.DriverManagement.scrollToView;

public class FAQPage extends BasePage{
    public void clickCreateAnAccountLink(){
        By lnkCreateAnAccount = By.xpath(String.format(lnkHyperlink, "Register"));
        scrollToView(lnkCreateAnAccount);
        click(lnkCreateAnAccount);
    }

    public void clickBookTicketLink(){
        By lnkBookTicket = By.xpath(String.format(lnkHyperlink, "BookTicketPage"));
        scrollToView(lnkBookTicket);
        click(lnkBookTicket);
    }
}
