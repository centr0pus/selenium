package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Relative_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_btnSearch() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        WebElement btnSeach = driver.findElement(By.cssSelector("input[id='small-searchterms']"));
        driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("small-searchterms")).below(By.cssSelector(".cart-label")));
    }

    @Test
    public void TC_02_txtFirsName() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        By radioMale2 = By.id("gender-male");
        WebElement radioMale = driver.findElement(By.id("gender-male"));//
        driver.findElement(RelativeLocator.with(By.id("FirstName")).below(radioMale).above(By.name("LastName")));
    }

    @Test
    public void TC_03_txtLastName() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.name("FirstName")).above(By.name("DateOfBirthDay")));

    }

    @Test
    public void TC_04_DateOfBirth() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        WebElement txtLastName = driver.findElement(By.id("LastName"));
        driver.findElement(RelativeLocator.with(By.tagName("select")).below(txtLastName).toLeftOf(By.name("DateOfBirthMonth")));

    }

    @Test
    public void TC_05_MonthOfBirth() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(RelativeLocator.with(By.tagName("select")).toRightOf(By.name("DateOfBirthDay")).toLeftOf(By.name("DateOfBirthYear")));
        //so sánh doi tuong can tim dung o vi tri nao so voi cac doi tuong con lại

    }

    @Test
    public void TC_06_YearOfBirth() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(RelativeLocator.with(By.tagName("select")).toRightOf(By.name("DateOfBirthMonth")));
        //so sánh doi tuong can tim dung o vi tri nao so voi cac doi tuong con lại

    }

    @Test
    public void TC_07_CompanyName() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        //WebElement companyName = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.className("title")).above(By.className("title")));
        driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.name("Email")).above(By.id("Newsletter")));
        //so sánh doi tuong can tim dung o vi tri nao so voi cac doi tuong con lại

    }

    @Test
    public void TC_08_Checkbox() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        //WebElement companyName = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.className("title")).above(By.className("title")));
        driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.name("Company")).above(By.id("Password")));
        //so sánh doi tuong can tim dung o vi tri nao so voi cac doi tuong con lại

    }
    @Test
    public void TC_09_Password() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        //WebElement companyName = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.className("title")).above(By.className("title")));
        driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("Newsletter")).above(By.id("ConfirmPassword")));
        //so sánh doi tuong can tim dung o vi tri nao so voi cac doi tuong con lại

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

//Cach tim locator toi uu co ban//
//DK 1: Duy nhat
//DK2: Gia tri co y nghia -> Di kem voi mot y do nao do
    //- neu co thay doi se de dang nhan ra
    //- gan lien voi y nghia voi element do

//DK3: co thuoc tinh nao la ID/Class/Name khong? -> Co thi se lay (Do Xpath hoac Css khi ket hop voi ID/Class/Name se chay nhanh hon so voi cac thuoc tinh khac

//DK4: Cac thuoc tin khong co cai nao la ID/Class/Name -> Can xet lai DK1+DK2
//DK5: Chi ap dung cho duong link, neu truyen dung duong link vi du: https://admin.baseid.vn//ddd -> Domain khac nhau -> Nen uu tien dung thuoc tinh text hoacj label

