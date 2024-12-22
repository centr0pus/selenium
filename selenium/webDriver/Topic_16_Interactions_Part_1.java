package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Interactions_Part_1 {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        //move chuot den element Tooltips
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Tooltips']"))).perform();
        //ktra tooltip display
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        //verify noi dung tooltip
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"That's what this widget is");

       // action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
       // Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        // Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");


    }

    @Test
    public void TC_02_Hove_Fahasa() {
        driver.get("https://www.fahasa.com/");
        //hove vao icon menu
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        //hove vao menu VPP - Dung cu hs
        action.moveToElement(driver.findElement(By.cssSelector("a[title='VPP - Dụng Cụ Học Sinh']"))).perform();

        //hove va click chon:
        action.click(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Bộ Dụng Cụ Học Tập']"))).perform();
        //driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Bộ Dụng Cụ Học Tập']")).click();

        //vefify breadcrumb dc hien thi
        By breadcrumb = By.xpath("//div[@id='ves-breadcrumbs']//strong[text()='Bộ Dụng Cụ Học Tập']");

        Assert.assertTrue(driver.findElement(breadcrumb).isDisplayed());
        //verify mau cua breadcrumb
        Assert.assertEquals(Color.fromString(driver.findElement(breadcrumb).getCssValue("color")).asHex().toUpperCase(),"#F7941E");



    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
