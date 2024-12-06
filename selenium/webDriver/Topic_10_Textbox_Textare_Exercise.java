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
    public void TC_02_OrangePage() throws InterruptedException {

        String first_name = "Hoang";
        String middle_Name = "Thi";
        String last_name = "Huong";
        String user_name = first_name + last_name + new Random().nextInt(100);
        String password = "123456aA@";


        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Thread.sleep(3000);

        //driver.findElement(By.xpath("//ul[@class='oxd-main-menu']//li[@class='oxd-main-menu-item-wrapper'][position()=2]")).click();
        //driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        driver.findElement(By.xpath("//span[text()='PIM']/ancestor::li")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(first_name);
        driver.findElement(By.cssSelector("input[name='middleName']")).sendKeys(middle_Name);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(last_name);

        //luu thong tin truong emp_id de verify
        String empID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomAttribute("value");

        driver.findElement(By.cssSelector("span.oxd-switch-input")).click();
        //driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//input")).click();
        //p[text()='Create Login Details']/parent::div//input
        Thread.sleep(3000);

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(user_name);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(10000);
        //driver.manage().timeouts().pageLoadTimeout(3000);

        //driver.findElement(By.xpath("//input[@name='firstName']")).getA
        //driver.findElement(By.cssSelector("input[name='firstName']")).getDomAttribute("value");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomAttribute("value"),first_name);
      //  Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomAttribute("value"),first_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='middleName']")).getDomAttribute("value"),middle_Name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomAttribute("value"),last_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='middleName']")).getDomAttribute("value"),middle_Name);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div")).getDomAttribute("value"),empID);

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
