package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Dropdow_Custom {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }
    //Viết hàm để handel dropdow custom
    public  void Custom_DropDow_Custom(String parentDropdow, String listChildItem, String itemSelect){

    }

    @Test
    public void TC_01_Jquery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

    //Speed drop_dow: viet thong thuong
        //1. Tim dropdow & Click
        driver.findElement(By.id("speed-button")).click();
        Thread.sleep(1500);

        //2. Wait cho den khi cac item dc load het ra
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div")));

        //3. Lay danh sach item da load ra
        List<WebElement> listItemSpeed = driver.findElements(By.cssSelector("ul#speed-menu div"));

        //4. Duyet qua cac gia tri de ktra
        for (WebElement itemChoose:listItemSpeed){
            //Ktra dieu kien: neu text cua item lay ra bang voi ket qua mong doi
            if (itemChoose.getText().equals("Fast")){
                // thi click chon
                itemChoose.click();
                // sau do thoat khoi vong lap
                break;
            }
        }
    //Speed drop_dow: su dung loi goi ham
        selectItemDropdow("speed-button","ul#speed-menu div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");

    //File drop_dow
        selectItemDropdow("files-button", "ul#files-menu div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"ui.jQuery.js");

    //Number drop_dow
        selectItemDropdow("number-button", "ul#number-menu div", "14");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"14");

    //Salutation drop_dow
        selectItemDropdow("salutation-button", "ul#salutation-menu div", "Prof.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Prof.");

    }

    private void selectItemDropdow(String parentDropdow, String childListValue, String item) throws InterruptedException {
        //1. Tim dropdow & Click
        driver.findElement(By.id(parentDropdow)).click();
        Thread.sleep(1500);

        //2. Wait cho den khi cac item dc load het ra
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childListValue)));

        //3. Lay danh sach item da load ra
        List<WebElement> listItemSpeed = driver.findElements(By.cssSelector(childListValue));

        //4. Duyet qua cac gia tri de ktra
        for (WebElement itemChoose : listItemSpeed) {
            //Ktra dieu kien: neu text cua item lay ra bang voi ket qua mong doi
            if (itemChoose.getText().equals(item)) {
                // thi click chon
                itemChoose.click();
                // sau do thoat khoi vong lap
                break;
            }
        }
    }

    @Test
    public void TC_02_React_Semantic() {
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_03_VueJS(){

    }

    @Test
    public void TC_04_Edit_Table(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
//chưa học nhưng cần phải sử dụng
//Wait explicit
//List <webElememt>
//Vòng lặp for
//Điều kiện if
//Lệnh break
//viết hàm (reuseable function)
//parameter