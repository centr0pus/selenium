package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_IFrame_Framre(){
        driver.get("https://toidicodedao.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")).isDisplayed());

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        String numberFollower = driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div")).getText();

        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(),numberFollower);
        //driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div")).getText();
    }


    @Test
    public void TC_02_Frame_FormSite() throws InterruptedException {
        Select select;

        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Accept All']")).click();

        driver.findElement(By.id("imageTemplateContainer")).click();
        Thread.sleep(3000);

        driver.switchTo().frame(driver.findElement(By.id("frame-one85593366")));

        new Select(driver.findElement(By.xpath("//label[text()='Year ']/following-sibling::select"))).selectByVisibleText("Junior");

        new Select(driver.findElement(By.xpath("//label[text()='Residence ']/following-sibling::select"))).selectByVisibleText("Off Campus");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")));

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).isSelected());
        Thread.sleep(5000);

        driver.switchTo().defaultContent() //chuyen ra page mac dinh
                .findElement(By.cssSelector("nav.header--desktop-floater a[title='Log in']")).click(); //nhan vao btn Login

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.id("message-error")).getText(),"Username and password are both required.");
    }

    @Test
    public void  TC_03_NetBanking() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("[name='login_page']")));

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("CE1212");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='input-box']/label[text()='Password/IPIN']")).isDisplayed());

        //div[@class='input-box']/label[text()='Password/IPIN']
    }

    @AfterClass
    public void afterClass (){
        // driver.quit();
    }

}

