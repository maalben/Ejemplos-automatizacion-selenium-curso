package automationtesting.frames;

import automationtesting.utils.Utilidades;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestFrames {

    public static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    public void startPage() {
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Frames.html");
    }

    @Test
    public void ingresarAUnFrame(){
        startPage();
        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='singleframe']")));
        //driver.switchTo().frame(0);
        driver.switchTo().frame("singleframe");
        Utilidades.typeInField(driver.findElement(By.xpath("//input[@type='text']")), "Esto es una prueba");
        driver.switchTo().defaultContent();
        String expectedMessage = "Automation Demo Site";
        String labelMessage = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(expectedMessage, labelMessage);
    }

    @Test
    public void ingresarADosFrames(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        Utilidades.clickElement(driver, "Iframe with in an Iframe", "linkText");

        WebElement primerFrame = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
        wait.until(ExpectedConditions.visibilityOf(primerFrame));
        driver.switchTo().frame(primerFrame);

        WebElement segundoFrame = driver.findElement(By.tagName("iframe"));
        wait.until(ExpectedConditions.visibilityOf(segundoFrame));
        driver.switchTo().frame(segundoFrame);

        Utilidades.typeInField(driver.findElement(By.xpath("//input[@type='text']")), "Esto es una prueba");
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
        String expectedMessage = "Automation Demo Site";
        String labelMessage = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(expectedMessage, labelMessage);
    }

    @After
    public void close() {
        driver.quit();
    }

}
