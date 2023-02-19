package TaskThreeDemoqa;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TaskTreeTest extends BaseTest {

    @Test
    public void createTicket() throws InterruptedException {
        GoToUrl("https://demoqa.com/automation-practice-form");
        ClickByXPath("(//*[@class=' css-1hwfws3'])[1]", null);

        //WebElement element = (new WebDriverWait(Driver, Duration.ofSeconds(15)))
        //.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='react-select-3-option-1']")));

        ClickByXPath("//div[@id='react-select-3-option-1']", null);
        ClickByXPath("(//*[@class=' css-1hwfws3'])[2]", null);
        ClickByXPath("//div[@id='react-select-4-option-0']", null);

        SendKeysByXPath("//input[@id='firstName']", "firstName", false);
        SendKeysByXPath("//input[@id='lastName']", "lastName", false);
        SendKeysByXPath("//input[@id='userEmail']", "userEmail@test.ru", false);
        ClickByXPath("//label[text()='Male']", null);
        SendKeysByXPath("//input[@id='userNumber']", "79999999999", false);
        ClickByXPath("//input[@id='dateOfBirthInput']", null);
        ClickByXPath("//div[@class='react-datepicker__week']//div[text()='14']", null);
        SendKeysByXPath("//input[@id='subjectsInput']", "Phy", false);
        ClickByXPath("//div[@id='react-select-2-option-0']", null);
        //ClickByXPath("//*[@id='hobbies-checkbox-3']", null);
        SendKeysByXPath("//textarea[@id='currentAddress']", "text", false);

        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        ClickByXPath("//button[@id='submit']", null);

        Assert.assertEquals("Thanks for submitting the form", GetElemByXPath("//*[@id='example-modal-sizes-title-lg']").getText());
        Assert.assertEquals("firstName lastName", GetElemByXPath("//td[text()='firstName lastName']").getText());
        Assert.assertEquals("userEmail@test.ru", GetElemByXPath("//td[text()='userEmail@test.ru']").getText());
        Assert.assertEquals("Male", GetElemByXPath("//td[text()='Male']").getText());
        Assert.assertEquals("7999999999", GetElemByXPath("//td[text()='7999999999']").getText());
        Assert.assertEquals("Physics", GetElemByXPath("//td[text()='Physics']").getText());
        Assert.assertEquals("Physics", GetElemByXPath("//td[text()='Physics']").getText());

    }


    @After
    public void tearDown(){

        CloseDriver();
    }
}
