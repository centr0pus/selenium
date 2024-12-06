package webDriver;

import org.bouncycastle.crypto.agreement.srp.SRP6Client;
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
import java.util.concurrent.TimeUnit;

public class Topic_10_Textbox_Textare_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {

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

        String first_name = "Dong";
        String middle_Name = "Minh";
        String last_name = "Thuy";
        String user_name = first_name + last_name + new Random().nextInt(100);
        String empID1 = "T"+ new Random().nextInt(999);
        String password = "123456aA@";
        String passpost ="444-555-666-777";
        String comment = "Ung dung tot\nGiao dien than thien";

        //1. Login
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Thread.sleep(3000);

        //2. Click menu PIM
        driver.findElement(By.xpath("//span[text()='PIM']/ancestor::li")).click();
        Thread.sleep(3000);

        //3. Click Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(3000);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        //4. Enter data EMP
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(first_name);
        driver.findElement(By.cssSelector("input[name='middleName']")).sendKeys(middle_Name);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(last_name);

            //clear data Employee ID default
       // driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).click();
       // driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).clear();
        driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(empID1);

        //Get value: Employee ID
        String empID2 = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println(empID2);

        //Show detail info
        driver.findElement(By.cssSelector("span.oxd-switch-input")).click();
            //driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//input")).click();
            //p[text()='Create Login Details']/parent::div//input
        Thread.sleep(3000);

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(user_name);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        //5. Click SAVE Employee
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(10000);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //6. Verify Info after Save
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),first_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='middleName']")).getDomProperty("value"),middle_Name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),last_name);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"),empID2);

        //7. Click menu Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(3000);

        //8. Click ADD: Assigned Immigration Records
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();
        Thread.sleep(3000);

        //9. Enter data Immigration
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).sendKeys(passpost);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).sendKeys(comment);

        //10. Click SAVE Immigration
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(3000);

        //11. Click EDIT icon
           // driver.findElement(By.xpath("//div[text()='1234-1234-1234-1234']/parent::div/following-sibling::div[@class='oxd-table-cell oxd-padding-cell'][last()]//i[@class='oxd-icon bi-pencil-fill']")).click();
        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(3000);

        //12. Verify info Immigration
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).getDomProperty("value"),passpost);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).getDomProperty("value"),comment);

        //13. Logout
        driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(3000);

        //14. Login with neww account
        driver.findElement(By.name("username")).sendKeys(user_name);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        //15. Verify info Account

            //Click My Info
        driver.findElement(By.xpath("//span[text()='My Info']/parent::a[@class='oxd-main-menu-item']")).click();
        Thread.sleep(3000);

        //Detail info
        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='firstName']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='middleName']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='lastName']")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("_value"),first_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='middleName']")).getDomProperty("value"),middle_Name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),last_name);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"),empID2);

            //Immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).isEnabled());

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).getDomProperty("value"),passpost);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).getDomProperty("value"),comment);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
