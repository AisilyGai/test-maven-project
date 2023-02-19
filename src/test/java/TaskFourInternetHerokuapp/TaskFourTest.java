package TaskFourInternetHerokuapp;

import groovy.transform.Final;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class TaskFourTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        //driver.manage().window().maximize();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //WebElement element = driver.findElement(By.xpath(""));

        try {
            driver.get("https://the-internet.herokuapp.com/drag_and_drop");
            driver.manage().window().maximize();
            Thread.sleep(5000);

            WebElement element = driver.findElement(By.xpath("//header[text()='A']"));
            WebElement element2 = driver.findElement(By.xpath("//header[text()='B']"));

            Actions actions = new Actions(driver);

            //actions.dragAndDropBy(element, 100, 100);
            actions.dragAndDrop(element, element2).build().perform();
            //actions.moveToElement(element).clickAndHold().moveToElement(element2).release().build().perform();
            //actions.clickAndHold(element).moveToElement(element2).release(element2).build().perform();


        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        } finally {
            Thread.sleep(5000);
            driver.quit();
        }

    }
}
