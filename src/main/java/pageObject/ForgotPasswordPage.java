package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class ForgotPasswordPage extends BasePage{
    private By txtEmail = By.id("email");
    private By btnSend = By.xpath("//input[@value='Send Instructions']");
    private By msgError = By.xpath("//*[@id=\"content\"]//p[@class='message error']");

    public void enterEmailAddress(String email){
        enter(txtEmail, email);
    }
    public void clickSendInstructionsBtn(){
        click(btnSend);
    }

    public String getErrorMsg(){
        return getText(msgError);
    }

}
