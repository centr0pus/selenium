package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_Frame_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_04_Frame_FormSite() throws InterruptedException {
        Select select;

        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.id("imageTemplateContainer")).click();
        Thread.sleep(3000);

        driver.switchTo().frame(driver.findElement(By.id("frame-one85593366")));

        new Select(driver.findElement(By.xpath("//label[text()='Year ']/following-sibling::select"))).selectByVisibleText("Junior");

        new Select(driver.findElement(By.xpath("//label[text()='Residence ']/following-sibling::select"))).selectByVisibleText("Off Campus");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")));

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).isSelected());
        Thread.sleep(5000);




    }
    @AfterClass
    public void afterClass (){
        // driver.quit();
    }

}

