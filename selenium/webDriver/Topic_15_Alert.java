package webDriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_15_Alert {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        //WebDriverWait implicitWait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(2000);
        Alert alertAccept = driver.switchTo().alert();
        Assert.assertEquals(alertAccept.getText(),"I am a JS Alert");
        alertAccept.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert();
        Assert.assertEquals(driver.switchTo().alert().getText(),"I am a JS Confirm");
        driver.switchTo().alert().dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");



    }

    @Test
    public void TC_03_PromtAlert() throws InterruptedException {
        String stringPromp = "Automation Testing";
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        //Vua cho cho alert hien thi sau do switch luon vao alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(3000)).until(ExpectedConditions.alertIsPresent());
        //Alert alert = driver.switchTo().alert();
        Thread.sleep(2000);
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys(stringPromp);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: "+stringPromp);

    }
    
    @Test
    public void TC_04_Authentication_Alert_C1(){
        String userName = "admin";
        String password = "admin";
        String url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
        driver.get(url);
        Assert.assertEquals( driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");

        driver.get("http://" + userName + ":" + password + "@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals( driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");

    }

@Test
    public void TC_04_Authentication_Alert_C2(){
        String userName = "admin";
        String password = "admin";
        driver.get("https://the-internet.herokuapp.com/");
        String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomAttribute("href");
        String url = "https://the-internet.herokuapp.com" + basicAuthenUrl;

        String[] urlArray = url.split("//");
        url = urlArray[0] + userName + ":" + password + "@" + urlArray[1];
        System.out.println( url);
        driver.get(url);
        Assert.assertEquals( driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");

    }

    @Test
    public void TC_04_Authentication_Alert_C3(){
        String userName = "admin";
        String password = "admin";

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
       devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
       String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
       headers.put("Authorization", basicAuthen);

        // Set to Header
       // devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));



    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
