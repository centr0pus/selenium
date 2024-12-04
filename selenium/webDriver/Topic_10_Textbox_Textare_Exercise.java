package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Random;

public class Topic_10_Textbox_Textare_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {

        String first_name = "Trump";
        String last_name = "Donald";
        String full_name = first_name + " " + last_name;
        String email_add = "DonalTrump" + new Random().nextInt(999) + "@gmail.net";
        String password = "123456789";

        String through_review = "Gia ca tot\nHang chinh hang \nGiao hang dung thoi gian";
        String summary_review = "Dien thoai hot nhat";
        String nickName = first_name + " " + last_name;


        driver.get("http://live.techpanda.org");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        driver.findElement(By.id("firstname")).sendKeys(first_name);//truyen bien da khai bao
        driver.findElement(By.id("lastname")).sendKeys(last_name);
        driver.findElement(By.id("email_address")).sendKeys(email_add);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        //Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
        Assert.assertTrue(contactInfo.contains(full_name));
        Assert.assertTrue(contactInfo.contains(email_add));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id ='Quality 1_5']")).click();

        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(through_review);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(summary_review);
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(nickName);

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Your review has been accepted for moderation.");

    }

    @Test
    public void TC_02() {

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.xpath("//ul[@class='oxd-main-menu']//li[@class='oxd-main-menu-item-wrapper'][position()=2]")).click();
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
