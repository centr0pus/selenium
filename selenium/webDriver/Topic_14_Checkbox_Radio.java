package webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //driver.manage().window().setSize(new Dimension(1366,786));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scrollBy(0,250)", "");

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
        //driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input")).isSelected());

        if(!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input")).click();
        }

    }

    @Test
    public void TC_02_Angular() {
        driver.get("https://material.angular.io/components/radio/examples");

        By summer = By.xpath("//label[text()='Summer']/preceding-sibling::div/input");
        if(!driver.findElement(summer).isSelected()){
            driver.findElement(summer).click();
        }
        Assert.assertTrue(driver.findElement(summer).isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");

        if(!driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());

        if(!driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());


        driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());

        driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
    }

    @Test
    public void TC_04_Automationfc() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        driver.findElement(By.xpath("//label[contains(string(),'Please check all that apply')]/parent::li//input"));

        List<WebElement> listItem = driver.findElements(By.xpath("//label[contains(string(),'Please check all that apply')]/parent::li//input"));

        for(WebElement itemClick:listItem){
            if (!itemClick.isSelected()) {
                itemClick.click();
            }
            Assert.assertTrue(itemClick.isSelected());
        }
        for(WebElement itemClick:listItem){
            if (itemClick.isSelected()) {
                itemClick.click();
            }
            Assert.assertFalse(itemClick.isSelected());
        }
        for(WebElement itemClick:listItem){
            if (itemClick.getText().trim().equals("Heart Attack")) {
                itemClick.click();
            }
            Assert.assertTrue(itemClick.isSelected());
        }
    }

    @Test
    public void TC_05_Automationfc() {

        driver.get("https://automationfc.github.io/multiple-fields/");
       // driver.findElement(By.xpath("//label[contains(string(),'Please check all that apply')]/parent::li//input"));

        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@class='form-single-column']//input[@type='checkbox']"));

        for(WebElement checkbox:allCheckboxes){
            if (!checkbox.isSelected() && checkbox.getDomAttribute("value").equals("Fainting Spells")) {
                checkbox.click();
            }
        }

        for(WebElement checkbox:allCheckboxes){
            if (checkbox.getDomAttribute("value").equals("Fainting Spells")) {
                Assert.assertTrue(checkbox.isSelected());
            }

        }
    }

    @AfterClass
    public void afterClass(){
       driver.quit();
    }
}
