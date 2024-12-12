package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        Thread.sleep(2000);

        By btnLogin = By.cssSelector("button.fhs-btn-login");

        //Verify btn Login when default
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        Assert.assertFalse(driver.findElement(btnLogin).isEnabled());

        //Verify btn Login khong cho phep click
            //new WebDriverWait(driver, Duration.ofSeconds(5000)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.fhs-btn-login")));
        //new WebDriverWait(driver, Duration.ofSeconds(5000)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(btnLogin)));
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(btnLogin))));

        //Verrify color
        String btnLogoinColor = Color.fromString(driver.findElement(btnLogin).getCssValue("background-color")).asHex().toUpperCase();
        System.out.printf("Mau mac dinh cua btn la" +btnLogoinColor);
        Assert.assertEquals(btnLogoinColor,"#000000");

        Assert.assertEquals(Color.fromString(driver.findElement(btnLogin).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

        //Input data --> Verify btn Login
        driver.findElement(By.id("login_username")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("1234567890");

        Assert.assertTrue(driver.findElement(btnLogin).isEnabled());
        new WebDriverWait(driver, Duration.ofSeconds(5000)).until(ExpectedConditions.elementToBeClickable(btnLogin));
        Assert.assertEquals(Color.fromString(driver.findElement(btnLogin).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

        //Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        //Assert.assertFalse(new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(btnLogin))));



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
