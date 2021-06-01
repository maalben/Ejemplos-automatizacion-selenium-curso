package automationtesting.alerts;

import static automationtesting.utils.Utilidades.clickElement;
import static automationtesting.utils.Utilidades.waitOwn;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAlerts {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    public void startPage(){
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Alerts.html");

    }

    @Test
    public void testAlertOK(){

        startPage();
        clickElement(driver, "//a[@href='#OKTab']", "xpath");
        clickElement(driver, "btn-danger", "className");
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        String expectedMessageAlert = "I am an alert box!";
        assertEquals(expectedMessageAlert, textAlert);
        alert.accept();
        String textButton = driver.findElement(By.className("btn-danger")).getText();
        String expectedMessageButton = "click the button to display an alert box:";
        assertEquals(expectedMessageButton, textButton);
    }

    @Test
    public void testAlertOKAndCancel(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        clickElement(driver, "//a[@href='#CancelTab']", "xpath");
        clickElement(driver, "btn-primary", "className");
        wait.until(alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        String expectedMessageAlert = "Press a Button !";
        assertEquals(expectedMessageAlert, textAlert);
        alert.dismiss();
        String textLabelCancel = driver.findElement(By.id("demo")).getText().trim();
        String expectedMessageLabelCancel = "You Pressed Cancel";
        assertEquals(expectedMessageLabelCancel, textLabelCancel);
        clickElement(driver, "btn-primary", "className");
        wait.until(alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();
        String textLabelOK = driver.findElement(By.id("demo")).getText().trim();
        String expectedMessageLabelOK = "You pressed Ok";
        assertEquals(expectedMessageLabelOK, textLabelOK);
    }

    @Test
    public void testAlertWithTextBox(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        startPage();
        clickElement(driver, "//a[@href='#Textbox']", "xpath");
        clickElement(driver, "btn-info", "className");
        wait.until(alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Juana");
        alert.accept();
        String textLabelTextBox = driver.findElement(By.id("demo1")).getText().trim();
        String expectedMessageLabelCancel = "Hello Juana How are you today";
        assertEquals(expectedMessageLabelCancel, textLabelTextBox);
    }


    @After
    public void close(){
        driver.quit();
    }

}
