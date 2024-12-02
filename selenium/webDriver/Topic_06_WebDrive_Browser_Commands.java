package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebDrive_Browser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //hàm truyền vào một url cho Browser
        driver.get("https://automationfc.github.io/basic-form/");

        //hàm đóng lại trình duyệt
        driver.quit();

        //hàm đóng lại một tab hiện tại của trình duyệt/hoặc một cửa sổ hiện tại của trình duyệt
        driver.close();

        //hàm tìm ra một element của trình duyệt. Tham số truyền vào là kiểu By
        driver.findElement(By.cssSelector(""));

        //hàm tìm ra một list element của Browser
        driver.findElements(By.xpath(""));

//các hàm có tiền tố bắt đầu là get --> lấy ra một thông tin nào đó của Browser
        //1. Lấy ra URL của page hiện tại
        driver.getCurrentUrl();
        // lấy ra URL của page hiện tại sau đó gán vào biến để sử dụng nhiều lần nếu có
        String homepageURL = driver.getCurrentUrl();

        // lấy ra URL của page hiện tại sau đó sử dụng luôn với mục đích nào đó
        Assert.assertEquals(driver.getCurrentUrl(),"https:admin.google.com");

        //2. Lấy ra title của page
        driver.getTitle();

        //3. Hàm lấy ra windows ID của tất cả các page || tab --> Sử dụng handle các case khi thao tác sẽ mở ra một tab hoặc một cửa sổ mới
        driver.getWindowHandles();
        //4. Hàm chỉ lấy ra window ID hiện tại
        driver.getWindowHandle();

        //5 Lấy ra source code HTML của page hiện tại
        driver.getPageSource();

        // Các hàm để handle Alert; Iframe; Window; Tab
        //1. Để chuyển sang tab/ iframe/window/alert
        // alert
        driver.switchTo().alert().getText();
        //iframe: vào một frame
        driver.switchTo().frame();
        //iframe:từ frame con ra frame con
        driver.switchTo().parentFrame();
        //iframe:ra ngoài page hiện tại
        driver.switchTo().defaultContent();
        //window
        driver.switchTo().window("");
        //window: mở ra một tab mới
        driver.switchTo().newWindow(WindowType.TAB);
        //window: mở ra một window mới
        driver.switchTo().newWindow(WindowType.WINDOW);

// Hàm MANAGER:
        //set thời gian timeout cho Browser khi không tìm thấy element thì sau khoảng thời gian setup sẽ show thông báo lỗi
        // chỉ ảnh hưởng tới 2 hàm là: findElement() và findElements()
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //hàm lấy ra thời gian timeout đã setup. Chỉ có từ selenium version 4
        driver.manage().timeouts().getImplicitWaitTimeout();

        //hàm để set timeout cho page được load xong -> sau time đã set mà chưa load xong -> show log lỗi -> tuy nhiên ko cần sử dụng vì hàm implicitlyWait đã xử lý
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // hàm set timeout cho script js
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        //hàm xử lý cho COOKIE
        //lấy ra cookie
        driver.manage().getCookies();

    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
