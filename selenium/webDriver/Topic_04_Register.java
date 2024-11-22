package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        // Arrange
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Register_01_Empty_Data() {
        //Action 1
        driver.get("https://alada.vn/tai-khoan/dang-ky.html"); //1. Mở page
        //Action 2
        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click(); //2. Nhấn btn Đăng ký
        //Assert  - verify info  -- Tim doi tuong tren UI v mong muon no bang voi gia tri nao
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");//ky vong chuoi ma selenium tim thay bang voi chuoi
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("BinhTT");
        driver.findElement(By.id("txtEmail")).sendKeys("abc@1..122.com");// Nhập email không đúng dịnh dạng
        driver.findElement(By.id("txtCEmail")).sendKeys("abc@1..122.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123456") ;
        driver.findElement(By.name("txtCPassword")).sendKeys("123456") ;
        driver.findElement(By.name("txtPhone")).sendKeys("0986986745");
        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại email hợp lệ");

       // WebElement txtEmail = driver.findElement(By.id("txtCEmail-error"));
       // System.out.println(txtEmail.getText()); // in ra màn hình chuỗi get về được
    }

    @Test
    public void Register_03_Invalid_Confirm_Email() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("BinhTT");
        driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.vn");// Nhập cf email không đúng
        driver.findElement(By.name("txtPassword")).sendKeys("123456") ;
        driver.findElement(By.name("txtCPassword")).sendKeys("123456") ;
        driver.findElement(By.name("txtPhone")).sendKeys("0986986745");
        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_04_Invalid_Password() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("BinhTT");
        driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123") ;// Nhập Pass ko đủ 6 ký tự
        driver.findElement(By.name("txtCPassword")).sendKeys("123") ;
        driver.findElement(By.name("txtPhone")).sendKeys("0986986745");
        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }
    @Test
    public void Register_05_Invalid_Confirm_Password() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("BinhTT");
        driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123456") ;
        driver.findElement(By.name("txtCPassword")).sendKeys("456123") ; // CF mật khẩu ko chính xác
        driver.findElement(By.name("txtPhone")).sendKeys("0986986745");
        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");

    }
    @Test
    public void Register_06_Invalid_Phone_Number() {
        //Case 1: Nhỏ hơn 10 ký tự số  -> Số điện thoại phải từ 10-11 số.
            //Action
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");
            driver.findElement(By.id("txtFirstname")).sendKeys("BinhTT");
            driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
            driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
            driver.findElement(By.name("txtPassword")).sendKeys("123456") ;
            driver.findElement(By.name("txtCPassword")).sendKeys("123456") ;
            driver.findElement(By.name("txtPhone")).sendKeys("0123");// Sdt nhỏ hơn 10
            driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
            // Assert
            Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Case 2: Lớn hơn 11 ký tự số -> Số điện thoại phải từ 10-11 số.
            //Action
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");
            driver.findElement(By.id("txtFirstname")).sendKeys("BinhTT");
            driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
            driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
            driver.findElement(By.name("txtPassword")).sendKeys("123456") ;
            driver.findElement(By.name("txtCPassword")).sendKeys("123456") ;
            driver.findElement(By.name("txtPhone")).sendKeys("0123456789010"); // Sdt lơn hơn 11 ký tự
            driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
            // Assert
            Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Case 3: Không bắt đầu bằng số 0 -> Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08
            //Action
            driver.findElement(By.name("txtPhone")).clear();// xóa giá trị cũ để nhập giá trị mới// các trường khác lấy thông tin từ TC bên trên
            driver.findElement(By.name("txtPhone")).sendKeys("123654");
            driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
            // Assert
            Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

        //Cass 4: Nhập số điện thoại có chứa ký tự chũ  -> Vui lòng nhập con số
            //Action
            driver.findElement(By.name("txtPhone")).clear();
            driver.findElement(By.name("txtPhone")).sendKeys("012AR");
            driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();// Nhấn đăng ký sau khi đã nhập đủ giá trị
            // Assert
            Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập con số");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

//***********NOTE*******************

//ham assert de verify thong tin
//verify thong tin trong auto voi selenium co the dung cac thuc vien: juni, testng,
//AAA pattern: setup - action - assert; Page Object patern; Factory pattern; Singeleton parttern; SOLID; OOP; KISS; DRY; YAGNI
//TDD Test Driven Development -> Phát triển theo hướng kiểm thử
//BDD Behavior Driven Development -> Hướng phát triển theo hành vi  => TC sẽ viết theo hướng hành vi của EndUser