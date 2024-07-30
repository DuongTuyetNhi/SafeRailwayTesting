package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class ChangePasswordPage extends BasePage{
    private String message = "//p[@class='message %s']";
    private By formChangePassword = By.xpath("//form/fieldset/legend[text()='Change Password Form']");
    private By txtCurrentPassword = By.id("currentPassword");
    private By txtNewPassword = By.id("newPassword");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By btnChangePassword = By.xpath("//input[@value='Change Password']");

    public boolean isChangePasswordFormDisplayed(){
        return isDisplayed(formChangePassword);
    }

    public void submitChangePasswordForm(String currentPassword, String newPassword, String confirmPassword){
        enter(txtCurrentPassword, currentPassword);
        enter(txtNewPassword, newPassword);
        enter(txtConfirmPassword, confirmPassword);
        scrollToView(btnChangePassword);
        click(btnChangePassword);
    }

    public String getSuccessMsg(){
        By msgSuccess = By.xpath(String.format(message, "success"));
        return getText(msgSuccess);
    }

    public String getErrorMsg(){
        By msgError = By.xpath(String.format(message, "error"));
        return getText(msgError);
    }
}
