package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_ID() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtSearch"));
        driver.findElement(By.id("txtFirstname"));
        driver.findElement(By.id("txtEmail"));
        driver.findElement(By.id("txtCEmail"));
        driver.findElement(By.id("txtPassword"));
        driver.findElement(By.id("txtCPassword"));
        driver.findElement(By.id("txtPhone"));
        driver.findElement(By.id("chkRight"));
    }

    @Test
    public void TC_02_Class() {
        //khong su dung cho cac class ma co khoang trang
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.className("inputsearch2"));//Textbox search
        //driver.findElement(By.className("fa fa-question-circle"));//icon huong dan
    }

    @Test
    public void TC_03_Name() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.name("txtFirstname"));
        driver.findElement(By.name("txtEmail"));
        driver.findElement(By.name("chkRight"));
    }

    @Test
    public void TC_04_Link() {
        //chi dung duoc voi duong link co text -> Lay text duoc hien thi tren UI
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.linkText("Hướng dẫn"));
        driver.findElement(By.linkText("thỏa thuận sử dụng")); //btn Dang nhap
        driver.findElement(By.linkText("chính sách")); //btn Dang nhap

    }

    @Test
    public void TC_05_Partial_Link() {
        //chi dung duoc voi duong link co text -> Lay text duoc hien thi tren UI
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.partialLinkText("sử dụng")); //link thoan thuan su dung
        driver.findElement(By.partialLinkText("sách hoàn trả"));

    }

    @Test
    public void TC_06_TagName() {
        //tim nhieu element giong nhau
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Dimension linkNubmer = driver.findElement(By.tagName("a")).getSize();
        System.out.println("Tong so luong la: "+ linkNubmer);
    }

    @Test
    public void TC_07_Css() {
        //1. CSS va ID
            //cau truc: co 3 cach viet
            //cach 1, viet dung chuan css: tagname[attribute='value']
            //cach 2, viet tat: tagname#value
            //cach 3, viet tat: #value
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.cssSelector("input[id='txtSearch']"));
        driver.findElement(By.cssSelector("input[id ='txtFirstname']"));//cach 1
        driver.findElement(By.cssSelector("input#txtFirstname"));//cach 2
        driver.findElement(By.cssSelector("#txtFirstname"));//cach 3

        //2. CSS voi Class
            //cau truc: co 3 cach viet
            //cach 1, viet dung chuan css: tagname[attribute='value']
            //cach 2, viet tat: tagname.value
            //cach 3, viet tat: .value
        // ngoai le: neu gia tri cua class có chua khoang trang thì thay khoang trang = dau cham
        driver.findElement(By.cssSelector("i[class='fa fa-question-circle']"));//cach 1
        driver.findElement(By.cssSelector("i.fa.fa-question-circle"));//cach 2
        driver.findElement(By.cssSelector(".fa.fa-question-circle"));//cach 3
        driver.findElement(By.cssSelector(".fa.fa-search.searchico"));

        //3. CSS voi Name
        driver.findElement(By.cssSelector("input[name='txtFirstname']"));
        driver.findElement(By.cssSelector("input[name='txtEmail']"));
        driver.findElement(By.cssSelector("input[name='txtPassword']"));

        //4. CSS voi Link
            // css khong dung dc voi text -> nen can dung thuoc tinh khac text, vs = href
        driver.findElement(By.cssSelector("a[href='https://alada.vn/gioi-thieu.html']"));
        driver.findElement(By.cssSelector("a[href='https://alada.vn/chinh-sach-hoan-tra-hoc-phi.html']"));

        //5. CSS với partial Link
            // css khong dung dc voi text -> nen can dung thuoc tinh khac text, vs = href
            //[attribute^='value'] lay mot doan dau
            //* lay mot doan giua
            //$ lay mot doan cuoi
        driver.findElement(By.cssSelector("a[href^='https://alada.vn/gioi-thieu.html']"));
        driver.findElement(By.cssSelector("a[href*='chinh-sach-hoan-tra-']"));
        driver.findElement(By.cssSelector("a[href$='hoan-tra-hoc-phi.html']"));

        //6. CSS voi tagname
            //cau truc =
        int i = driver.findElements(By.cssSelector("a")).size();
        System.out.println("Tong the a la: "+i);

    }

    @Test
    public void TC_08_XPath() {
        //cau truc: //tagname[@attribute='value']
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //1. XPath voi ID
        driver.findElement(By.xpath("//input[@id='txtSearch']"));
        driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        driver.findElement(By.xpath("//input[@id='chkRight']"));

        //2. XPath voi Class
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
       // driver.findElement(By.xpath("//button[@class='btndknfooter']"));

        //3. XPath voi Name
        driver.findElement(By.xpath("//input[@name='txtFirstname']"));
        driver.findElement(By.xpath("//input[@name='txtEmail']"));

        //4. XPath voi link
        //xpath co the lam viec voi text -> lay text tren html
        driver.findElement(By.xpath("//span[@href='danh-sach-khoa-hoc.html']"));
        driver.findElement(By.xpath("//a[text()='Câu hỏi thường gặp']"));

        //5. XPath voi partial link

        driver.findElement(By.xpath("//a[starts-with(@href,'https://alada.vn/cau-hoi-thuong-gap.html')]"));
        driver.findElement(By.xpath("//a[contains(@href,'alada.vn/cau-hoi-thuong-gap.html')]));
        //Xpath khong support voi end-with


        //6/ XPath voi tagname
        int i = driver.findElements(By.xpath("//input")).size();
        System.out.println("Tong the a la: "+i);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
