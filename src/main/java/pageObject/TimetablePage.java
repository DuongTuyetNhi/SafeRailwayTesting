package pageObject;

import base.DriverManagement;
import enums.Locations;
import org.openqa.selenium.By;

import static base.DriverManagement.click;

public class TimetablePage extends BasePage{

    private String lnkFunction = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(@href, '%s')]";

    public void selectFunction(Locations departFrom, Locations arriveAt, String function){
        By linkFunction = By.xpath(String.format(lnkFunction, departFrom.getValueLocation(), arriveAt.getValueLocation(), function));
        DriverManagement.scrollToView(linkFunction);
        click(linkFunction);
    }
}
