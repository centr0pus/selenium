package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Topic_09_Element_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        Assert.assertTrue(driver.findElement(By.cssSelector("input#mail")).isDisplayed());
        driver.findElement(By.cssSelector("input#mail")).sendKeys("ACB@gmail.com");
        System.out.println("Email is Dislayed");

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isDisplayed());
        System.out.println("Under 18 is Dislayed");
        driver.findElement(By.cssSelector("input#under_18")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        System.out.println("Under 18 is Selected");

        Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());
        driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing FC");
        System.out.println("Education is Dislayed");

        Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
        System.out.println("User 5 is Not Dislayed");
    }

    @Test
    public void TC_02_isEnable_Disable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        Assert.assertTrue(driver.findElement(By.cssSelector("input#mail")).isEnabled());
        System.out.println("Email is Enabel");

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isEnabled());
        System.out.println("Under 18 is Enable");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
