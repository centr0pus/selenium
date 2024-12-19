package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class Topic_16_Action_Part_1 {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_02_() {
        driver.get("https://www.facebook.com/");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
