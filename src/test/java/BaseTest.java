import base.DriverManagement;
import models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObject.*;

import static base.DriverManagement.getDriver;

public class BaseTest {
    @BeforeMethod
    @Parameters({"browser", "runMode"})
    public synchronized void beforeMethod(@Optional("chrome") String browser, @Optional("local") String runMode) throws Throwable {
        System.out.println("Pre-condition");
        DriverManagement.setBrowser(browser);
        DriverManagement.setRunMode(runMode);
        DriverManagement.initDriver();
    }

    @AfterMethod
    public synchronized void afterMethod() {
        System.out.println("Post-condition");
        getDriver().quit();
    }

    HomePage homePage = new HomePage();
    FAQPage faqPage = new FAQPage();
    ContactPage contactPage = new ContactPage();
    TimetablePage timetablePage = new TimetablePage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    LoginPage loginPage = new LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    RegisterPage registerPage = new RegisterPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    SuccessPage successPage = new SuccessPage();
    MyTicketPage myTicketPage = new MyTicketPage();
    ChangePasswordPage changePasswordPage = new ChangePasswordPage();
    MailPage mailPage = new MailPage();
    String username = "nhiagest@grr.la";
    String password = "12345678";
    User user = new User(username, password);
}
