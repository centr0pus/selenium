package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_DragAndDrop {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_DragAndDrop_HTML4 (){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        By smalll = By.cssSelector("div#draggable");
        By big = By.cssSelector("div#droptarget");
        //Cach 1
        actions.clickAndHold(driver.findElement(smalll)).moveToElement(driver.findElement(big)).release().perform();
        Assert.assertEquals(Color.fromString(driver.findElement(big).getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
        Assert.assertEquals(driver.findElement(big).getText(),"You did great!");
        //Cach 2
        actions.dragAndDrop(driver.findElement(smalll),driver.findElement(big)).perform();
        Assert.assertEquals(Color.fromString(driver.findElement(big).getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
        Assert.assertEquals(driver.findElement(big).getText(),"You did great!");

    }

    @Test
    public void TC_02_DragAndDrop_HTML5 () throws InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        By columA = By.cssSelector("div#column-a");
        By columB = By.cssSelector("div#column-b");
        //Cach 1
        //actions.clickAndHold(driver.findElement(columA)).moveToElement(driver.findElement(columB)).release().perform();
        //Thread.sleep(3000);
        //Assert.assertEquals(driver.findElement(columA).getText(),"B");
        //Assert.assertEquals(driver.findElement(columB).getText(),"A");

        //Cach 2
        actions.dragAndDrop(driver.findElement(columA),driver.findElement(columB)).perform();
        //Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(columA).getText(),"B");
        Assert.assertEquals(driver.findElement(columB).getText(),"A");


    }

    @Test
    public void TC_03_IFrame_Framre(){
        driver.get("https://toidicodedao.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")).isDisplayed());

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));

        String numberFollower = driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div")).getText();

        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(),numberFollower);
        //driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div")).getText();
    }

    @Test
    public void TC_04_Frame_FormSite() throws InterruptedException {
        Select select;

        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.id("imageTemplateContainer")).click();
        Thread.sleep(3000);

        driver.switchTo().frame(driver.findElement(By.id("frame-one85593366")));

        new Select(driver.findElement(By.xpath("//label[text()='Year ']/following-sibling::select"))).selectByVisibleText("Junior");

        new Select(driver.findElement(By.xpath("//label[text()='Residence ']/following-sibling::select"))).selectByVisibleText("Off Campus");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")));

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).isSelected());
        Thread.sleep(5000);




    }

    @AfterClass
    public void afterClass (){
       // driver.quit();
    }

}
