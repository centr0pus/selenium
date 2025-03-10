package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_Browser_exersice {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Page_URL() {
        driver.get("https://live.techpanda.org/");
        //click vao my account tai footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //So sanh gia tri tim dc voi gỉa tri mong muon
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        //lay url của page hien tai va gan vao bien  -> chỉ sử dụng khi nó được sử dụng ở nhiều nơi >1 lần -> nếu khai báo sẽ tốn bộ nhớ
        //String loginPageURL = driver.getCurrentUrl();
        //so sanh url lay dc voi url mong muon
        //Assert.assertEquals(loginPageURL,"https://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_02_Page_Title() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate_Back_Forward() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();// back lai trang truoc do
        driver.navigate().forward();//chuyen tiep trang truoc do
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");



    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));




    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

//khi viết code xong và muốn chạy một test class hoặc một testcase --> bắt buộc tất cả các class trong project không còn lỗi
//viết code lỗi đâu phải sửa luôn
//class mà chứa testcase thì gọi là Test class
//bắt đầu ở đâu; kết thúc ở đó --> không thừa không thiếu các ký tự đóng (mở) ngoặc; dấu nháy,...
//hàm được gọi ra đúng vị trí


