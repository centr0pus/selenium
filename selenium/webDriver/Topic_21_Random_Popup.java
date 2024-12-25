package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_21_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JavaCode() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        By newLetterPopup = By.xpath("//div[@data-title='Newsletter-Books Anime Brief - NEW' and not(contains(@style,'display:none'))]");
        // neu hien thi -> thi dong
        if (driver.findElements(newLetterPopup).size()>0 && driver.findElements(newLetterPopup).get(0).isDisplayed()){
            System.out.println("_____________ GO TO IF_________________");
            driver.findElement(By.xpath("//div[@data-title='Newsletter-Books Anime Brief - NEW' and not(contains(@style,'display:none'))]//a[text()='No Thanks!']")).click();
            Thread.sleep(3000);
        }
        System.out.println("_____________ IGNORE IF_________________");
        driver.findElement(By.id("search-input")).sendKeys("agile");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Search Results for: ']")).isDisplayed());



        //khong hien thi -> thao tac tiep

    }

    @Test
    public void TC_02_() {
        driver.get("");

    }
// ham lay noi dung file
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
