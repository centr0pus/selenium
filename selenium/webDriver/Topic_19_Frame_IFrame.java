package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
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
    public void TC_01_CodeDao() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div.fb-page.fb_iframe_widget")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page.fb_iframe_widget iframe")));

    }

    @Test
    public void TC_02_() {
        driver.get("https://www.facebook.com/");

    }
    @AfterClass
    public void afterClass (){
        // driver.quit();
    }

}

