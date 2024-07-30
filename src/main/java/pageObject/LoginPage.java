package pageObject;

import base.DriverManagement;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.*;

public class LoginPage extends BasePage{
    private By formLogin = By.xpath("//form[@class='LoginForm']");
    private By formForgotPassword = By.xpath("//form//legend[text()='Password Reset Instructions Form']");
    private By txtUsername = By.id("username");
    private By txtPassword = By.id("password");
    private By btnLogin = By.xpath("//input[@type='submit']");
    private By msgError = By.xpath("//*[@id='content']/p[@class='message error LoginForm']");
    private By lnkForgotPassword = By.xpath("//*[@id='content']//a[contains(@href,'ForgotPassword')]");
    private By txtNewPassword = By.id("newPassword");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By txtPasswordResetToken = By.id("resetToken");
    private By btnResetPassword = By.xpath("//form//input[@type='submit']");
    private By msgReset = By.xpath("//*[@id='content']/p[contains(@class,'message')]");
    private By msgConfirmPassword = By.xpath("//*[@id='content']//label[@class='validation-error' and @for='confirmPassword']");

    public boolean isLoginFormDisplayed(){
        return isDisplayed(formLogin);
    }

    public boolean isChangePasswordFormDisplayed(){
        return isDisplayed(formForgotPassword);
    }

    public void clickRegisterHyperlink(){
        By lnkRegister = By.xpath(String.format(lnkHyperlink, "Register"));
        click(lnkRegister);
    }

    public void clickForgotPasswordHyperlink(){
        By lnkForgotPassword = By.xpath(String.format(lnkHyperlink, "ForgotPassword"));
        click(lnkForgotPassword);
    }

    public void submitLoginForm(User user){
        enter(txtUsername, user.getUsername());
        enter(txtPassword, user.getPassword());
        DriverManagement.scrollToView(btnLogin);
        click(btnLogin);
    }

    public void loginInSeveralTime(User user, int times){
        for (int i = 1; i <= times; i++){
            submitLoginForm(user);
        }
    }

    public String getErrorMsg(){
        return getText(msgError);
    }

    public void clickForgotPassword(){
        click(lnkForgotPassword);
    }
    public void fillResetPasswordForm(String newPassword, String confirmPassword){
        enter(txtNewPassword, newPassword);
        enter(txtConfirmPassword, confirmPassword);
    }
    public void clickResetPasswordBtn(){
        click(btnResetPassword);
    }
    public boolean isTokenDisplayed(){
        List<WebElement> token = getDriver().findElements(txtPasswordResetToken);
        return !token.isEmpty();
    }
    public String getResetMsg(){
        return getText(msgReset);
    }
    public String getConfirmPasswordMsg(){
        return getText(msgConfirmPassword);
    }

}
