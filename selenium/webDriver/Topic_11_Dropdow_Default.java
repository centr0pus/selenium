package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_11_Dropdow_Default {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-data-dir=C:/Users/ADMIN/AppData/Local/Google/Chrome/User Data/");
        chromeOptions.addArguments("--profile-directory=Profile 1");
        driver = new ChromeDriver(chromeOptions);

      //  EdgeOptions edgeOptions = new EdgeOptions();
      //  edgeOptions.addArguments("--user-data-dir=C:/Users/ADMIN/AppData/Local/Google/Chrome/User Data/");
      //  edgeOptions.addArguments("--profile-directory=Profile 5");
      //  driver = new EdgeDriver(edgeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Video() throws InterruptedException {
        driver.get("https://egov.danang.gov.vn/reg");
        Select select;

        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));

        //chay bang index --> Có khả năng thay đổi vị trí và khó phát hiện lỗi ở đâu  --> không thích nghi với sự thay đổi
            //select.selectByIndex(1);
            //Thread.sleep(3000);

        //tương tự như index
            //select.selectByValue("1");
            //Thread.sleep(3000);

        //dễ debug khi testcase bị fail --> Nên trên UI hiển thị như nào --> thì mình sẽ chọn kiểu như thế
        select.selectByContainsVisibleText("thành phố Hải Phòng");
        Thread.sleep(3000);

        //hàm lấy ra giá trị đã chọn và vefify
        select.getFirstSelectedOption().getText();
        Assert.assertEquals("select.getFirstSelectedOption().getText()","thành phố Hải Phòng");

        //ktra một dropdow là sigle hay multip
        select.isMultiple();
        Assert.assertFalse(select.isMultiple());

        //lấy ra tất cả các option của dropdow
        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_quanhuyen")));
        List <WebElement> districtElement = select.getOptions();
        List <String> districtText = new ArrayList<>();
        for (WebElement district:districtElement){
            districtText.add(district.getText());
        }
        Assert.assertTrue(districtText.contains("Ngô Quyền"));
        Assert.assertTrue(districtText.contains("Hồng Bàng"));
        Assert.assertTrue(districtText.contains("Hải An"));
        Assert.assertTrue(districtText.contains("Kiến An"));
    }


    @Test
    public void TC_03_Ecommerce() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");
        Select select;
        String firstName = "Hoang";
        String lastName = "Huong";
        String emailAdrress = firstName + lastName + new Random().nextInt(10) + "@hotmail.com";
        String companyName = "Automation FC";
        String dob = "23";
        String mob = "December";
        String yob ="1980";

        // Step 1: Input data
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        select.selectByVisibleText(dob);
        //Dem so gia tri cua Ngay
        List<WebElement> dateOfBirth = select.getOptions();
        int countDate = dateOfBirth.size();
        System.out.println("Danh sach ngay la " + countDate);

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        select.selectByVisibleText(mob);
        //Dem so gia tri cua Thang
        List<WebElement> monthOfBirth = select.getOptions();
        int countMonth = monthOfBirth.size();
        System.out.println("Danh sach ngay la " + countMonth);

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
        select.selectByVisibleText(yob);
        //Dem so gia tri cua Nam
        List<WebElement> yearOfBirth = select.getOptions();
        int countYear = yearOfBirth.size();
        System.out.println("Danh sach ngay la " + countYear);

        driver.findElement(By.id("Email")).sendKeys(emailAdrress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys("123456aA@");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456aA@");

        //Step 2: Click Register
        driver.findElement(By.id("register-button")).click();
        Thread.sleep(2000);

        //Step 3: Verify Notication
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        //Step 4: My Account
        driver.findElement(By.xpath("//a[text()='My account']")).click();
        Thread.sleep(2000);

        //Step 5: Verify info after Register
        Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.id("Company")).getDomProperty("value"),companyName);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),dob);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),mob);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),yob);

        Assert.assertEquals(driver.findElement(By.id("Email")).getDomProperty("value"),emailAdrress);

    }

    @Test
    public void TC_04_Rode(){
        driver.get("https://www.rode.com/wheretobuy");
        String country = "Vietnam";
        String search ="HO CHI MINH";

        Select select;
        //1.Field Country
        select = new Select(driver.findElement(By.id("country")));
        select.selectByContainsVisibleText(country);
        Assert.assertTrue(new Select(driver.findElement(By.id("country"))).isMultiple());

        //2. Field Search
        driver.findElement(By.id("map_search_query")).sendKeys(search);

        //3. Click Search Btn
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        //4. Verify have 16 result
        driver.findElement(By.xpath("//h3[text()='Dealers']/following-sibling::div/div"));



        List<WebElement> dealers = driver.findElement(By.xpath("//h3[text()='Dealers']/following-sibling::div/div"));
        Assert.assertEquals(dealers.size(),"16");
        for (WebElement element:dealers){
            System.out.println("Danh sach la " + element.getText());
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
