package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    public void TC_01_() {
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_02_() {
        driver.get("https://www.facebook.com/");

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

