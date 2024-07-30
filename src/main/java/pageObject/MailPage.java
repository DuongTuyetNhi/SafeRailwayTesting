package pageObject;

import base.DriverManagement;
import org.openqa.selenium.By;

import static base.DriverManagement.*;
import static config.Constant.timeout;

public class MailPage extends BasePage{
    private By cbxScrambleAddress = By.id("use-alias");
    private By txtEmail = By.id("email-widget");
    private By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
    private By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");
    private By btnMail = By.xpath("//*[@id='inbox-id']");
    private By txtMailName = By.xpath("//*[@id='inbox-id']/input");
    private By btnSet = By.xpath("//*[@id='inbox-id']/button[@class='save button small']");
    private By sltDomainName = By.xpath("//select[@id='gm-host-select']");
    private By txtEmailReset = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please reset your password')]//span");
    private By txtLinkReset = By.xpath("//*[@id='display_email']//a[contains(@href,'PasswordReset')]");

    public String getEmail(){
        getDriver().findElement(cbxScrambleAddress).click();
        String email = getDriver().findElement(txtEmail).getText();
        return email;
    }

    public void confirmAccount(){
        DriverManagement.waitForElementVisible(txtEmailConfirm, timeout);
        click(txtEmailConfirm);

        DriverManagement.waitForElementVisible(txtLinkConfirm, timeout);
        click(txtLinkConfirm);
    }

    public void loginToEmail(String mailName, String domainName){
        click(btnMail);
        enter(txtMailName, mailName);
        click(btnSet);
        enter(sltDomainName, domainName);
    }
    public void resetPassword(){
        DriverManagement.waitForElementVisible(txtEmailReset, timeout);
        click(txtEmailReset);
        DriverManagement.waitForElementVisible(txtLinkReset, timeout);
        click(txtLinkReset);
    }
}
