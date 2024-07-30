package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Set;

import static config.ConfigReader.getProperty;

public class DriverManagement {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browser = new ThreadLocal<>();
    private static ThreadLocal<String> runMode = new ThreadLocal<>();

    public static synchronized void initDriver() throws Exception {
        String browser = getBrowser().toLowerCase();
        String runMode = getRunMode().toLowerCase();

        switch (runMode) {
            case "local":
                switch (browser) {
                    case "chrome":
                        driver.set(new ChromeDriver());
                        break;
                    case "firefox":
                        driver.set(new FirefoxDriver());
                        break;
                    default:
                        driver.set(new ChromeDriver());
                        break;
                }
                break;
            case "grid":
                String remoteUrl = getProperty("remoteUrl");
                switch (browser) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver.set(new RemoteWebDriver(new URL(remoteUrl), chromeOptions));
                        break;
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver.set(new RemoteWebDriver(new URL(remoteUrl), firefoxOptions));
                        break;
                    default:
                        ChromeOptions defaultOptions = new ChromeOptions();
                        driver.set(new RemoteWebDriver(new URL(remoteUrl), defaultOptions));
                        break;
                }
                break;
        }
    }

    public static void setBrowser(String browserName) {
        browser.set(browserName);
    }

    public static String getBrowser() {
        return browser.get();
    }

    public static void setRunMode(String runModeName) {
        runMode.set(runModeName);
    }

    public static String getRunMode() {
        return runMode.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void openRailwayPage(){
        int retryCount = 5;
        int retry = 0;
        while (retry < retryCount) {
            try {
                getDriver().get(getProperty("railway_url"));
                if (DriverManagement.getDriver().getTitle().contains("Safe Railway")) {
                    break;
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to load.");
            }
            retry++;
        }
    }

    public static void openMailPage(){
        getDriver().get(getProperty("email_url"));
    }

    public static void enter(By element, String information){
        getDriver().findElement(element).sendKeys(information);
    }

    public static void click(By element){
        getDriver().findElement(element).click();
    }

    public static String getText(By element){
        return getDriver().findElement(element).getText();
    }

    public static boolean isDisplayed(By element){
        return getDriver().findElement(element).isDisplayed();
    }
    public static void waitForElementVisible(By xpath, int timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public static void scrollToView(By xpath){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement Element = getDriver().findElement(xpath);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public static void switchToTab(String mailTab, String railwayTab){
        Set<String> allTabs = driver.get().getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(mailTab) && !tab.equals(railwayTab)) {
                driver.get().switchTo().window(tab);
                break;
            }
        }
    }
}
