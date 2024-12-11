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
        selectItemDropdow("span#speed-button","ul#speed-menu div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");

    //File drop_dow
        selectItemDropdow("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"ui.jQuery.js");

    //Number drop_dow
        selectItemDropdow("span#number-button", "ul#number-menu div", "14");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"14");

    //Salutation drop_dow
        selectItemDropdow("span#salutation-button", "ul#salutation-menu div", "Prof.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Prof.");

    }

    @Test
    public void TC_02_React_Semantic() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemDropdow("div#root","div[class ='visible menu transition']>div","Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class ='divider text']")).getText(),"Stevie Feliciano");

        selectItemDropdow("div#root","div[class ='visible menu transition']>div","Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class ='divider text']")).getText(),"Justen Kitsune");

    }

    @Test
    public void TC_03_VueJS () throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        //Cach 1: Viet tuan tu theo cac buoc
        driver.findElement(By.cssSelector("div[class ='btn-group']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class = 'dropdown-menu']>li")));
        List<WebElement> listItemVeu = driver.findElements(By.cssSelector("ul[class = 'dropdown-menu']>li"));
        for (WebElement itemChoose:listItemVeu){
            if(itemChoose.getText().trim().equals("First Option")){
                itemChoose.click();
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

        //Cach 2: Goi ham
        selectItemDropdow("div[class ='btn-group']","ul[class = 'dropdown-menu']>li","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

    }

    @Test
    public void TC_04_Editable_Semantic() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemDropdow("div.selection","div.transition>div","Belgium");

    }


    private void selectItemDropdow(String parentDropdow, String childListValue, String item) throws InterruptedException {
        //1. Tim dropdow & Click
        driver.findElement(By.cssSelector(parentDropdow)).click();
        Thread.sleep(1500);

        //2. Wait cho den khi cac item dc load het ra
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childListValue)));

        //3. Lay danh sach item da load ra
        List<WebElement> listItemSpeed = driver.findElements(By.cssSelector(childListValue));

        //4. Duyet qua cac gia tri de ktra
        for (WebElement itemChoose : listItemSpeed) {
            //Ktra dieu kien: neu text cua item lay ra bang voi ket qua mong doi
            if (itemChoose.getText().trim().equals(item)) {
                // thi click chon
                itemChoose.click();
                // sau do thoat khoi vong lap
                break;
            }
        }
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