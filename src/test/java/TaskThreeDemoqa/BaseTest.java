package TaskThreeDemoqa;

import jdk.jfr.Timespan;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.restassured.filter.log.RequestLoggingFilter.with;
import static jdk.jfr.Timespan.*;

abstract public class BaseTest{
    protected WebDriver Driver;

    private WebDriverWait _driverWait;
    protected BaseTest(){
        Driver = new ChromeDriver();
        _driverWait = new WebDriverWait(Driver, Duration.ofSeconds(10));

        WebDriver.Options driverOptions = Driver.manage();
        driverOptions.window().maximize();
        driverOptions.timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driverOptions.timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    protected void ScrollToElement(String xPath) {
        var js = (JavascriptExecutor) Driver;
        WebElement element = Driver.findElement(By.xpath(xPath));
        if (element != null && !element.isDisplayed()) {
            js.executeScript("arguments[0].scrollIntoView();", element);
        }
    }

    protected WebElement GetElemByXPath(String xPath) {
        ScrollToElement(xPath);
        return Driver.findElement(By.xpath(xPath));
    }

    protected void SendKeysByXPath(String xPatch, String text, Boolean needClear) {
        WebElement elem = GetElemByXPath(xPatch);
        if (needClear) {
            elem.sendKeys(Keys.CONTROL + "a");
            elem.sendKeys(Keys.DELETE);
        }

        elem.sendKeys(text);
    }
    protected void ClickByXPath(String xPatch, Integer waitMilliseconds) throws InterruptedException {
        ScrollToElement(xPatch);
        GetElemByXPath(xPatch).click();

        if (waitMilliseconds != null)
            Thread.sleep(waitMilliseconds);
    }
    protected void Sleep(String xPatch){
        WebElement element = (new WebDriverWait(Driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPatch)));
    }
    protected void GoToUrl(String url)
    {
        Driver.navigate().to(url);
    }

    protected void CloseDriver(){
        Driver.close();
        Driver.quit();
    }
    @After
    public void tearDown(){

        CloseDriver();
    }
}
