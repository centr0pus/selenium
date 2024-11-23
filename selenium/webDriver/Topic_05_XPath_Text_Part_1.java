package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_XPath_Text_Part_1 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Text_Bang() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']"));
        int elementText_1 = driver.findElements(By.xpath("//h5[text()='Michael Jackson']")).size();
        System.out.println("Tổng số element tìm thấy là: " + elementText_1);
        ;
    }

    @Test
    public void TC_02_Text_Contains() {
        // cover được trường hợp text()='...'
        driver.get("https://automationfc.github.io/basic-form/");
        int elementText_2 = driver.findElements(By.xpath("//h5[contains(text(),'Michael Jackson')]")).size();
        System.out.println("Tổng số element tìm thấy là: " + elementText_2);

        //driver.findElements(By.xpath("//div[contains(text(),'200')]"));

    }
    @Test
    public void TC_03_Text_Contains_String() {
        // cover được trường hợp text()='...'
        driver.get("https://automationfc.github.io/basic-form/");
        int elementText_3 = driver.findElements(By.xpath("//h5[contains(string(),'Michael Jackson')]")).size();
        System.out.println("Tổng số element tìm thấy là: " + elementText_3);

        //driver.findElements(By.xpath("//div[contains(text(),'200')]"));

    }

    @Test
    public void TC_04_Text_Concat() {
        // cover được trường hợp text()='...'
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElements(By.xpath("//span[text()=concat('Hello \"John\",',\" What's happened?\")]"));
       // System.out.println("Tổng số element tìm thấy là: " + elementText_4);

        //driver.findElements(By.xpath("//div[contains(text(),'200')]"));

    }

// inside parent:
// outside parent: (//div//div[@class='figcaption']/a) ; //tbody//td[1]
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
