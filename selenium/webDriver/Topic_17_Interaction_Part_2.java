package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class Topic_17_Interaction_Part_2 {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_ClickAndHold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(16)).perform();
        action.release().perform();
        Thread.sleep(3000);

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),5);
        Assert.assertEquals(allNumberSelected.get(0).getText(),"1");
        Assert.assertEquals(allNumberSelected.get(4).getText(),"5");
        Assert.assertEquals(allNumberSelected.get(8).getText(),"9");
        Assert.assertEquals(allNumberSelected.get(12).getText(),"13");
        Assert.assertEquals(allNumberSelected.get(16).getText(),"17");

    }

    @Test
    public void TC_02_ClickAndHold_Randoom () {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        // Mo phong thao tac nhan va chon cac phan tu khac nhau
        action.keyDown(Keys.CONTROL)  //nhan phim ctrl
                .click(allNumber.get(0)) // chon phan tu trong danh sach
                .click(allNumber.get(2))
                .click(allNumber.get(5))
                .click(allNumber.get(10))
                .click(allNumber.get(13))
                .click(allNumber.get(16))
                .click(allNumber.get(19))
                .keyUp(Keys.CONTROL) // nha phim Ctrl
                .pause(Duration.ofSeconds(2))
                .perform(); //thuc thi

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        System.out.println(allNumberSelected.size());
        Assert.assertEquals(allNumberSelected.size(),7);
    }

    @Test
    public void TC_03_Double_Click () throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");
    }

    @Test
    public void TC_04_Right_Click () {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        WebElement quitMenu = driver.findElement(By.xpath("//span[text()='Quit']"));
        Assert.assertTrue(quitMenu.isDisplayed());
        action.moveToElement(quitMenu).perform();
        //span[text()='Quit']/parent::li
        String str = driver.findElement(By.xpath("//span[text()='Quit']/parent::li")).getDomAttribute("class");
        System.out.println(str);
        Assert.assertTrue(str.contains("context-menu-hover context-menu-visible"));
    }



    @AfterClass
    public void afterClass(){

        driver.quit();
    }
}
