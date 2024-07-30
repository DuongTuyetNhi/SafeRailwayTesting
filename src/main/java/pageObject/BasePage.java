package pageObject;

import enums.Tab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.getDriver;
import static base.DriverManagement.getText;

public class BasePage {
    private String menuTab = "//div[@id='menu']//li/a[span[text()='%s']]";

    protected String lnkHyperlink = "//*[@id='content']//a[contains(@href,'%s')]";
    protected By msgTitle = By.xpath("//div[@id='content']/h1");

//    private String lblTitle= "//div[@id='content']/h1[text()='%s']";
//    public boolean isHomePageDisplayed(){
//        String welcomeTitle = "Welcome to Safe Railway";
//        By welcome = By.xpath(String.format(lblTitle, welcomeTitle));
//        return getDriver().findElement(welcome).isDisplayed();
//    }
    protected WebElement getTabElement(Tab tab){
        By byTab = By.xpath(String.format(menuTab, tab.getValueTab()));
        return getDriver().findElement(byTab);
    }

    public void openTab(Tab tab){
        this.getTabElement(tab).click();
    }

    public void openLoginTab(){
        this.getTabElement(Tab.LOGIN).click();
    }

    public String getTitlePage(){
        return getText(msgTitle);
    }
    public boolean isLogoutTabPresent(){
        By tabLogout = By.xpath(String.format(menuTab, "Log out"));
        List<WebElement> elements = getDriver().findElements(tabLogout);
        return !elements.isEmpty() &&  elements.get(0).isDisplayed();
    }
}