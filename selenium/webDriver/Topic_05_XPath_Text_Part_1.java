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
    }

    @Test
    public void TC_05_Text_And() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        int elementTXT =  driver.findElements(By.xpath("//input[@class='text form-control' and @id ='txtFirstname']")).size();
        System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }
    @Test
    public void TC_06_Text_Or() {
        // cover được trường hợp text()='...'
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        int elementTXT =  driver.findElements(By.xpath("//input[@id='txtEmail' or  @type ='email']")).size();
        System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }

    @Test
    public void TC_08_Text_Not() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        int elementTXT =  driver.findElements(By.xpath("//input[@type ='email' and not(@id='txtCEmail')]")).size();
        System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }

    @Test
    public void TC_09_Text_Inside() {
        driver.get("https://automationfc.github.io/basic-form/");
        //int elementTXT =  driver.findElements(By.xpath("//")).size();
       // System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }

    @Test
    public void TC_10_Text_Outside() {
        driver.get("https://automationfc.github.io/basic-form/");
        int elementTXT =  driver.findElements(By.xpath("(//div[@class='figcaption']/a)[1]")).size();
        System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }

    @Test
    public void TC_11_Text_Position() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        int elementTXT =  driver.findElements(By.xpath("//ol/li[position()=20]")).size();
        System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }

    @Test
    public void TC_12_Text_Last() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        int elementTXT =  driver.findElements(By.xpath("//ol/li[last()-1]")).size();
        System.out.println("Tổng số element tìm thấy là: "+elementTXT );
    }

// inside parent:
// outside parent: (//div//div[@class='figcaption']/a) ; //tbody//td[1]
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
