package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
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
        driver = new ChromeDriver();
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



    @AfterClass
    public void afterClass (){
       // driver.quit();
    }

}
