package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class Topic_06_WebDrive_Browser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() throws MalformedURLException {
        //hàm truyền vào một url cho Browser
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //hàm tìm ra một element của trình duyệt. Tham số truyền vào là kiểu By
        driver.findElement(By.id("email"));

        //hàm tìm ra một list element của Browser
        driver.findElements(By.xpath("//input"));

        //các hàm có tiền tố bắt đầu là get --> lấy ra một thông tin nào đó của Browser
        //1. Lấy ra URL của page hiện tại
        driver.getCurrentUrl();
        // lấy ra URL của page hiện tại sau đó sử dụng luôn với mục đích nào đó
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        //2. Lấy ra title của page
        driver.getTitle();

        //3. Hàm lấy ra windows ID của tất cả các page || tab --> Sử dụng handle các case khi thao tác sẽ mở ra một tab hoặc một cửa sổ mới
        String windowID = driver.getWindowHandles().toString();
        System.out.println("Window ID is:" + windowID);

        //4. Hàm chỉ lấy ra window ID hiện tại
        driver.getWindowHandle();

        //5 Lấy ra source code HTML của page hiện tại
        driver.getPageSource();

        // Các hàm để handle Alert; Iframe; Window; Tab
        //1. Để chuyển sang tab/ iframe/ window/ alert
        driver.switchTo().alert().getText();

        //iframe: vào một frame
        driver.switchTo().frame('1');
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

        //hàm xử lý cho COOKIE --> Áp dụng case chỉ cần login một lần, lần sau gán cookie vào mà ko cần đăng nhập lại
        //lấy ra cookie
        Set<Cookie> cki = driver.manage().getCookies();
        //add cookie
       // driver.manage().addCookie(new Cookie("a",cki));

        //Điều chỉnh size của cửa sổ window
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();
        driver.manage().window().setSize(new Dimension(1366, 768));

        // Hàm NAVIGATE
        //Mở ra một URL
        //driver.navigate().to(new URL("https://live.techpanda.org/index.php/about-magento-demo-store/"));
        driver.navigate().to("https://live.techpanda.org/index.php/about-magento-demo-store/");

        //Quay lạị trang trước đó
        driver.navigate().back();

        //Chuyển tiếp sang page trước đó
        driver.navigate().forward();

        //tải lại trang
        driver.navigate().refresh();

    }

    @Test
    public void TC_02() {
        driver.get("https://live.techpanda.org/index.php/about-magento-demo-store/");
        Set<Cookie> cki = driver.manage().getCookies();
        //driver.manage().addCookie(new Cookie("cki",cki));
        System.out.println(cki);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

