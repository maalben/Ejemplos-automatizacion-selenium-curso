package automationtesting.Waits;

import automationtesting.utils.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class TestWaits {

    public static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    public void startPage() {
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Register.html");
    }

    @Test
    public void testImplicitWait(){
        startPage();
        Utilidades.waitOwn(100);
        Utilidades.typeInField(driver.findElement(By.xpath("//input[@ng-model='FirstName']")), "Anacleto");
    }

    @Test
    public void testExplicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        wait.until(visibilityOfElementLocated(By.xpath("//input[@ng-model='FirstName']")));
        Utilidades.typeInField(driver.findElement(By.xpath("//input[@ng-model='FirstName']")), "Anacleto");
    }

    @Test
    public void testFluentWait(){
        startPage();
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(Exception.class);

        WebElement foo = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//input[@ng-model='FirstName']"));
            }
        });
        Utilidades.typeInField(foo, "Anacleto");


    /*    WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.tagName("h2"));
                String getTextOnPage = element.getText();
                if(getTextOnPage.equals("Register")){
                    System.out.println(getTextOnPage);
                    return element;
                }else{
                    System.out.println("FluentWait Failed");
                    return null;
                }
            }
        });*/


//        WebElement textName = (WebElement) wait.until((Function) driver.findElement(By.xpath("//input[@ng-model" +
//                "='FirstName']")));
//        Utilidades.typeInField(textName, "Anacleto");
    }


    @After
    public void close() {
        driver.quit();
    }
}
